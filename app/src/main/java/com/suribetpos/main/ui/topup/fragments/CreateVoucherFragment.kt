package com.suribetpos.main.ui.topup.fragments

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tscdll.TSCActivity
import com.google.gson.GsonBuilder
import com.journeyapps.barcodescanner.CaptureActivity
import com.suribetpos.R
import com.suribetpos.main.data.fcm.CrashAnalytics
import com.suribetpos.main.data.model.etopup.CardValidationModel
import com.suribetpos.main.data.model.topup.Salesvouchers
import com.suribetpos.main.ui.denomination.DenominationBottomFragment
import com.suribetpos.main.ui.denomination.DenominationRequestModel
import com.suribetpos.main.ui.topup.adapter.CreateVoucherAdapter
import com.suribetpos.main.ui.topup.adapter.CreateVoucherClickListener
import com.suribetpos.main.ui.topup.model.CreateVoucherModel
import com.suribetpos.main.ui.topup.model.TopupPrintoutDetailsModel
import com.suribetpos.main.ui.topup.repo.CreateVoucherRepository
import com.suribetpos.main.ui.topup.viewmodel.CreateVoucherViewModel
import com.suribetpos.main.ui.topup.viewmodel.CreateVoucherViewModelFactory
import com.suribetpos.main.ui.view.SplashScreenActivity
import com.suribetpos.main.utils.*
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.create_voucher_fragment.*
import kotlinx.android.synthetic.main.superbet_card_validation.*
import kotlinx.android.synthetic.main.topup_create_print.*
import kotlinx.android.synthetic.main.topup_payment_layout.*
import recieptservice.com.recieptservice.PrinterInterface
import java.text.DecimalFormat


class CreateVoucherFragment : Fragment(), CreateVoucherClickListener,
    DenominationBottomFragment.OnInputSelected {
    var salesVoucherUpdate = ArrayList<Salesvouchers>()

    //var salesDenomID = listOf<Int>()
    var salesDenomination = listOf<Double>()
    var salesDenomTotal = listOf<Double>()
    private var IsPrintBtnClickFlag: Boolean = true
    var printerstatus: String = ""
    var tsc = TSCActivity()
    private var TSCConnInt: Int = -1
    var REQUEST_ENABLE_BT: Int = 2
    var alert = AlertDialogManager()
    val intent = Intent()
    private var saveClickCounter = 0

    companion object {
        fun newInstance() = CreateVoucherFragment()
        const val printlimit: Int = 9
        var salesDenomVouchers = ArrayList<Int>()
        var printoutTime: Long = 3000
        var df = DecimalFormat("#.##")
        val SCANNIN_GREQUEST_CODE = 1
    }

    private lateinit var viewModel: CreateVoucherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.create_voucher_fragment, container, false)
        return views
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val createVoucherRepository = CreateVoucherRepository()
        val createVoucherViewModelFactory = CreateVoucherViewModelFactory(createVoucherRepository)
        viewModel = ViewModelProvider(
            this,
            createVoucherViewModelFactory
        ).get(CreateVoucherViewModel::class.java)

        viewModel.salesvouchers.observe(viewLifecycleOwner, { salesvouchers ->
            /*  salesDenomID = salesvouchers.map { salesvouchers ->
                  salesvouchers.DenominationId
              }.distinct()*/
            salesDenomination = salesvouchers.map { salesvouchers ->
                salesvouchers.Denomination.toDouble()
            }.distinct()
            val editSalesVoucher = salesvouchers

            voucher_recyclerView.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                it.setHasFixedSize(true)
                it.adapter = editSalesVoucher?.let { it1 ->
                    CreateVoucherAdapter(
                        it1 as ArrayList<Salesvouchers>,
                        this
                    )
                }
            }
            if (salesvouchers.isNotEmpty()) {
                novalue_createvoucher.visibility = View.GONE
            } else {
                novalue_createvoucher.visibility = View.VISIBLE
            }
        })

        viewModel.createVoucher.observe(viewLifecycleOwner, { createVoucher ->
            if (!IsPrintBtnClickFlag) {
                IsPrintBtnClickFlag = true
                progressBar.visibility = View.GONE
                if (createVoucher.isSuccessful) {
                    val listOfResponse =
                        createVoucher.body()?.m_Item1 as List<TopupPrintoutDetailsModel>
                    Log.d("Response", "countrylist size : ${listOfResponse?.size}")
                    if (listOfResponse.isNotEmpty()) {
                        if (SplashScreenActivity.isPrinterFlag) {
                            intent.setClassName(
                                "recieptservice.com.recieptservice",
                                "recieptservice.com.recieptservice.service.PrinterService"
                            )

                            activity?.bindService(intent, object : ServiceConnection {
                                @RequiresApi(Build.VERSION_CODES.O)
                                override fun onServiceConnected(
                                    name: ComponentName,
                                    service: IBinder
                                ) {
                                    val mAidl: PrinterInterface = PrinterInterface.Stub.asInterface(
                                        service
                                    )
                                    try {
                                        createVoucher_logo.setImageBitmap(Common.VoucherLogo)
                                        createVoucher_bottomRules.text =
                                            Common.LanguageMap["ETSF_" + Common.ClientId]

                                        val handler = Handler()
                                        var count = 0
                                        var totalCount = listOfResponse.size

                                        val runnable: Runnable = object : Runnable {
                                            override fun run() {
                                                // need to do tasks on the UI thread
                                                if (count++ < totalCount) {
                                                    var i = count - 1
                                                    Log.v("printslip-count", i.toString())
                                                    createVoucher_website.text =
                                                        listOfResponse[i].LocationAddress
                                                    createVoucher_clientName.text =
                                                        listOfResponse[i].LocationName
                                                    createVoucher_mobile.text =
                                                        listOfResponse[i].LocationPhone
                                                    createVoucher_id.text =
                                                        listOfResponse[i].VoucherId
                                                    createVoucher_code.text =
                                                        listOfResponse[i].TillCode.toString()
                                                    createVoucher_cashier.text =
                                                        listOfResponse[i].UserName
                                                    createVoucher_Amount.text =
                                                        listOfResponse[i].CurrencyWithCode
                                                    createVoucher_pin.text =
                                                        "PIN # " + listOfResponse[i].PIN

                                                    /* val TermsCondition: List<String> =
                                                        Common.VoucherTerms.split("by")
                                                    var bottom_box: String? = ""
                                                    for (i in TermsCondition.indices) {
                                                        var bottom_boxs = TermsCondition[i].toString()
                                                        if (i == 0) {
                                                            bottom_box = bottom_boxs
                                                        }
                                                        if (i == 1) {
                                                            bottom_box += "\n by $bottom_boxs"
                                                        }
                                                    }*/
                                                    createVoucher_bottomRules.text =
                                                        Common.VoucherTerms
                                                    createVoucher_customerServiceNo.text =
                                                        listOfResponse[i].CustomerCare
                                                    createVoucher_date.text =
                                                        listOfResponse[i].PrintingDateTime


                                                    // your code to run after 3 secondl
                                                    mAidl.setAlignment(0)
                                                    mAidl.printBitmap(topLayout(380, 250))
                                                    mAidl.printBitmap(
                                                        BarcodeGeneration.getBitmap(
                                                            activity!!,
                                                            listOfResponse[i].Barcode,
                                                            400,
                                                            80
                                                        )
                                                    )
                                                    mAidl.setAlignment(1)//center
                                                    mAidl.setTextSize(20F)
                                                    mAidl.setTextBold(true)
                                                    mAidl.printText(listOfResponse[i].Barcode)
                                                    mAidl.nextLine(1)

                                                    //buClickValue = buClickValue.replace(".", "")
                                                    mAidl.setAlignment(0)//left
                                                    mAidl.setTextBold(false)
                                                    mAidl.printText(Common.VoucherTerms)
                                                    mAidl.nextLine(1)

                                                    mAidl.setAlignment(0)//left
                                                    mAidl.setTextBold(true)
                                                    mAidl.printText(commonMessage(R.string.online_support_number))
                                                    mAidl.nextLine(1)
                                                    mAidl.printText(listOfResponse[i].CustomerCare)
                                                    mAidl.nextLine(1)

                                                    mAidl.setAlignment(2)
                                                    mAidl.printText(listOfResponse[i].PrintingDateTime)
                                                    mAidl.nextLine(5)
                                                    handler.postDelayed(this, 5000)
                                                }
                                            }
                                        }
                                        // trigger first time
                                        handler.post(runnable)
                                        // clear all from list
                                        viewModel.addSalesVouchers(
                                            clearAll()
                                        )
                                        totalVouchers.text = "0"
                                        totalAmount.text = "0"
                                    } catch (e: java.lang.Exception) {
                                        e.printStackTrace()
                                    }
                                }

                                override fun onServiceDisconnected(name: ComponentName) {
                                    AlertDialogBox(
                                        commonMessage(R.string.TSC_Conn),
                                        name.toString(),
                                        false
                                    )
                                }
                            }, AppCompatActivity.BIND_AUTO_CREATE)
                        } else {
                            topup_printout(listOfResponse, Common.VoucherLogo)
                            // clear all from list
                            viewModel.addSalesVouchers(
                                clearAll()
                            )
                            totalVouchers.text = "0"
                            totalAmount.text = "0"
                        }

                    } else {
                        Toasty.error(
                            requireActivity(),
                            createVoucher.body()?.m_Item2.toString(),
                            Toasty.LENGTH_LONG,
                            true
                        ).show()
                    }
                } else {
                    Toasty.error(
                        requireActivity(),
                        createVoucher.errorBody().toString(),
                        Toasty.LENGTH_LONG,
                        true
                    ).show()
                }
            }
        })

        viewModel.cardavalidation.observe(viewLifecycleOwner, { cardavalidation ->
            if (cardavalidation != null) {
                if (cardavalidation.isSuccessful) {
                    val listOfResponse = cardavalidation.body() as List<CardValidationModel>
                    if (listOfResponse.isNotEmpty()) {
                        var accountID: String? = listOfResponse[0].AccountID
                        if (accountID != null) {
                            if (accountID.length > 2) {
                                cardVal_customerName.text = listOfResponse[0].CustomerName
                                cardVal_profile.setImageBitmap(
                                    StringToBitMap(
                                        listOfResponse[0].CustomerImage
                                    )
                                )
                                Common.CustomerCardAccountID = listOfResponse[0].AccountID
                                if (Common.BarcodeScan) {
                                    card_validationLAY.visibility = View.GONE
                                    val totalAmount = totalAmount.text
                                    withEditText(totalAmount.toString())
                                }
                            } else {
                                AlertDialogBox(
                                    commonMessage(R.string.card_validation),
                                    listOfResponse[0].Message,
                                    false
                                )
                                Common.CustomerCardAccountID = listOfResponse.get(0).AccountID
                            }
                        }
                    } else {
                        AlertDialogBox(
                            commonMessage(R.string.card_validation),
                            listOfResponse[0].Message,
                            false
                        )
                    }

                } else {
                    Log.e("Responce-else", cardavalidation.errorBody().toString())
                }
            }
        })
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, requestCode, data)
        when (requestCode) {
            SCANNIN_GREQUEST_CODE ->
                if (resultCode == Activity.RESULT_OK) {
                    //Log.e("requestCode-2", ">>>>>>" + data.getStringExtra("ScannedBarcode"));
                    val contents = data?.getStringExtra("SCAN_RESULT")
                    val format = data?.getStringExtra("SCAN_RESULT_FORMAT")
                    Log.e("requestCode-3", ">>>>>>$contents---$format")
                    Common.BarcodeScan = true
                    cardVal_cardnumber.setText(contents)
                    if (isNullOrEmpty(contents)) {
                        AlertDialogBox(
                            commonMessage(R.string.card_validation),
                            commonMessage(R.string.please_enter_card_number_barcode),
                            false
                        )
                    } else {
                        GetCardValidationAPI(contents)
                    }
                }
            else -> {
                print("x is neither 1 nor 2")
            }
        }
    }

    private fun GetCardValidationAPI(enteredValue: String?) {
        try {
            val parameters = CardValidationModel()
            parameters.CardId = 0
            parameters.Value = enteredValue
            parameters.ClientId = Common.ClientId
            parameters.AccountId = "0"
            parameters.ChipId = " "
            //progressbarlayout.setVisibility(View.VISIBLE)
            viewModel.pushCardValidation(parameters)

        } catch (ex: Exception) {
            CrashAnalytics.CrashReport(ex)
            AlertDialogBox(commonMessage(R.string.card_validation), ex.message, false);
            //progressbarlayout.setVisibility(View.GONE);
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTopupdetails_IMG.setOnClickListener {
            if (saveClickCounter++ == 0) {
                val dialog = DenominationBottomFragment()
                dialog.setTargetFragment(this, 1)
                fragmentManager?.let {
                    // dialog.dismiss()
                    dialog.show(it, "MyCustomDialog")
                }
                Handler().postDelayed({
                    saveClickCounter = 0
                }, 1000)
            }
        }

        clear_all_vouchers.setOnClickListener {
            viewModel.addSalesVouchers(
                clearAll()
            )
            salesDenomVouchers.clear()
            totalVouchers.text = "0"
            totalAmount.text = "0"
        }

        payVouchers.setOnClickListener {
            if (salesVoucherUpdate.isNotEmpty()) {
                val quantityCount = salesDenomVouchers.sum()
                if (quantityCount > printlimit) {
                    Toasty.error(
                        requireContext(),
                        "Recharge voucher limit is ${CreateVoucherFragment.printlimit}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (Common.ClientId == Common.CardClientIDValidation) {
                        card_validationLAY.visibility = View.VISIBLE
                        ClearCardValidation()
                    } else {
                        card_validationLAY.visibility = View.GONE
                        val totalAmount = totalAmount.text
                        withEditText(totalAmount.toString())
                    }
                }
            } else {
                AlertDialogBox(commonMessage(R.string.TopUp), "Please add voucher", false)
            }
        }
        cardVal_Cancel.setOnClickListener {
            ClearCardValidation()
        }
        cardVal_SaveAccount.setOnClickListener {
            if (Common.CustomerCardAccountID.length > 2) {
                val totalAmount = totalAmount.text
                withEditText(totalAmount.toString())
            } else {
                AlertDialogBox(
                    commonMessage(R.string.card_validation),
                    commonMessage(R.string.please_enter_card_number_barcode),
                    false
                )
            }
        }
        cardVal_search.setOnClickListener {
            if (isNullOrEmpty(cardVal_cardnumber.text.toString())) {
                AlertDialogBox(
                    commonMessage(R.string.card_validation),
                    commonMessage(R.string.please_enter_card_number_barcode),
                    false
                )
            } else {
                Common.BarcodeScan = false
                GetCardValidationAPI(cardVal_cardnumber.getText().toString())
            }

        }
        cardVal_close.setOnClickListener {
            ClearCardValidation()
            card_validationLAY.visibility = View.GONE
        }

        cardVal_scanner.setOnClickListener {
            try {
                val intent = Intent(activity, CaptureActivity::class.java)
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE)
            } catch (anfe: ActivityNotFoundException) {
                Log.e("onCreate", "Scanner Not Found", anfe);
            }
        }
    }

    fun ClearCardValidation() {
        cardVal_customerName.text = ""
        cardVal_cardnumber.setText("")
        Common.CustomerCardAccountID = "0"
        cardVal_profile.setImageBitmap(null)
    }

    override fun CreateVoucherRecyclerClick(
        view: View,
        salesvouchers: ArrayList<Salesvouchers>,
        position: Int
    ) {
        when (view.id) {
            R.id.remove_voucher -> viewModel.addSalesVouchers(
                deleteSalesVoucher(salesvouchers, position)
            )
        }
    }

    override fun TotalCountAmount(view: View, salesvouchers: ArrayList<Salesvouchers>) {
        salesDenomTotal = salesvouchers.map { salesvouchers ->
            salesvouchers.Total.toDouble()
        }
        salesDenomVouchers = salesvouchers.map { salesvouchers ->
            salesvouchers.Quantity
        } as ArrayList<Int>
        val sum = df.format(salesDenomTotal.sum())
        val count = salesDenomVouchers.sum()
        totalVouchers.text = count.toString()
        totalAmount.text = sum.toString()
    }

    override fun errorMessage(view: View, message: String) {
        //AlertDialogBox(commonMessage(R.string.TopUp), message, false)
        Toasty.error(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }


    override fun sendInput(clientdenom: DenominationRequestModel) {
        //Toast.makeText(requireContext(), "Create Voucher Clicked", Toast.LENGTH_SHORT).show()
        val quantityCount = salesDenomVouchers.sum()
        if (quantityCount >= printlimit) {
            Toasty.error(
                requireContext(),
                "Recharge voucher limit is ${CreateVoucherFragment.printlimit}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if (salesDenomination.isEmpty()) {
                viewModel.addSalesVouchers(addSalesVoucher(clientdenom))
            } else {
                if (salesDenomination.contains(clientdenom.Denomination)) {
                    viewModel.addSalesVouchers(updateSalesVoucher(clientdenom, salesVoucherUpdate))
                } else {
                    viewModel.addSalesVouchers(addSalesVoucher(clientdenom))
                }
            }
        }
    }

    fun addSalesVoucher(
        denominationModel: DenominationRequestModel
    ): ArrayList<Salesvouchers> {
        // val total: String = String.format("%d", (1 * denominationModel.Denomination))
        val total: String = df.format(1 * denominationModel.Denomination).toString()
        val salesVoucher = listOf(
            Salesvouchers(
                1,
                denominationModel.CurrencyID,
                denominationModel.DenominationID,
                denominationModel.Denomination.toString(),
                1,
                total
            )
        )
        salesVoucherUpdate.addAll(salesVoucher)
        return salesVoucherUpdate
    }

    private fun updateSalesVoucher(
        denominationModel: DenominationRequestModel,
        updatesalesVoucher: ArrayList<Salesvouchers>
    ): ArrayList<Salesvouchers> {
        for (index in 0 until updatesalesVoucher.size) {
            if (updatesalesVoucher[index].Denomination.toDouble() == denominationModel.Denomination.toDouble()) {
                val quantity = updatesalesVoucher[index].Quantity + 1
                //val total: String = String.format("%d",(quantity * updatesalesVoucher[index].Denomination.toInt()))
                val total: String =
                    df.format(quantity * updatesalesVoucher[index].Denomination.toDouble())
                        .toString()
                updatesalesVoucher[index] = Salesvouchers(
                    10, denominationModel.CurrencyID,
                    denominationModel.DenominationID,
                    denominationModel.Denomination.toString(),
                    quantity,
                    total
                )
            }
        }
        return updatesalesVoucher
    }

    private fun deleteSalesVoucher(
        salesVoucher_Frags: ArrayList<Salesvouchers>,
        position: Int
    ): ArrayList<Salesvouchers> {
        try {
            if (salesVoucher_Frags.size == 1) {
                totalVouchers.text = "0"
                totalAmount.text = "0"
            }
            salesVoucher_Frags.removeAt(position)

        } catch (e: Exception) {
        }
        return salesVoucher_Frags
    }

    private fun clearAll(): ArrayList<Salesvouchers> {
        try {
            salesVoucherUpdate.clear()
        } catch (e: Exception) {
        }
        return salesVoucherUpdate
    }

    // printout for topup
    private fun printSlip(createVoucherpojo: CreateVoucherModel) {
        visible_progressbar()
        if (IsPrintBtnClickFlag) {
            if (!BlutoothCommonClass.isBluetoothEnabled()) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            } else {
                val devAddress = BlueTooth.getAddress("BTSPP")
                if (devAddress != null) {
                    printerstatus = tsc.printerstatus()
                    if (TSCConnInt == 1) {
                        tsc.status()
                        printerstatus = tsc.printerstatus()
                    } else {
                        val btConnResult = tsc.openport(devAddress)
                        TSCConnInt = btConnResult.toInt()
                        printerstatus = tsc.printerstatus()
                    }
                    if (printerstatus == "00") {
                        IsPrintBtnClickFlag = false
                        viewModel.pushCreateVoucher(createVoucherpojo)
                    } else {
                        printerport_close()
                        AlertDialogBox(
                            commonMessage(R.string.TSC_Conn),
                            SuribetException.PrinterCurrentStatus(printerstatus),
                            false
                        )
                    }
                } else {
                    printerport_close()
                    AlertDialogBox(
                        commonMessage(R.string.PrinterHead), commonMessage(R.string.PrinterHDmsg),
                        false
                    )
                }
            }
        } else {
            printerport_close()
            AlertDialogBox(
                commonMessage(R.string.PrinterHead), commonMessage(R.string.Printing),
                false
            )
        }
    }

    private fun printerport_close() {
        TSCConnInt = -1
        tsc.closeport()
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
        }
    }

    private fun visible_progressbar() {
        if (progressBar.visibility == View.GONE) {
            progressBar.visibility = View.VISIBLE
        }
    }

    fun AlertDialogBox(Title: String?, Message: String?, YesNo: Boolean) {
        if (Common.AlertDialogVisibleFlag) {
            alert.showAlertDialog(activity, Title, Message, YesNo)
        } else {
            //do something here... if already showing
        }
    }

    fun commonMessage(Common_Msg: Int): String? {
        return activity?.resources?.getString(Common_Msg)
    }

    fun logo_print(): Bitmap {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.topupvoucher)
        Toast.makeText(activity, "Image converted to Bitmap", Toast.LENGTH_SHORT).show()
        return bitmap
    }

    private fun validationSalesVouchersPayment(
        ReturnAmount: Double,
        PaidAmount: String,
        TotalAmount: Double
    ): Boolean {
        try {
            if (PaidAmount.isNotEmpty()) {
                if (PaidAmount.toDouble() == 0.00) {
                    Toasty.error(
                        requireContext(),
                        "Paid amount should not be empty and 0!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                }
            } else {
                Toasty.error(
                    requireContext(),
                    "Paid amount should not be empty and 0!",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
            if (ReturnAmount < 0.00) {
                Toasty.error(
                    requireContext(),
                    "Paid amount should be greater or equal to Total amount!",
                    Toast.LENGTH_SHORT
                ).show()
                receivedAmtEDT.setText("")
                receivedAmtEDT.requestFocus()
                return false
            }
            if (TotalAmount > PaidAmount.toDouble()) {
                Toasty.error(
                    requireContext(),
                    "Paid amount should not be less then Total amount!",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        } catch (ex: java.lang.Exception) {
            return false
        }
        return true
    }

    fun withEditText(totalAmts: String) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val inflaterf = this.layoutInflater
        val dialogView: View = inflaterf.inflate(R.layout.topup_payment_layout, null)
        val totalTxT = dialogView.findViewById<TextView>(R.id.totalAmtTXT)
        val receivedEDT = dialogView.findViewById<EditText>(R.id.receivedAmtEDT)
        val returnTXT = dialogView.findViewById<TextView>(R.id.returnAmtTXT)
        val payBTN = dialogView.findViewById<Button>(R.id.payBTN)
        val cancelBTN = dialogView.findViewById<Button>(R.id.cancelBTN)
        totalTxT.text = totalAmts
        val totalAmtDou = totalAmts.toDouble()

        val df = DecimalFormat("0.00##")
        returnTXT.text = df.format(0 - totalAmtDou!!)
        returnTXT.setTextColor(resources.getColor(R.color.TabColor))
        dialogBuilder.setView(dialogView)

        val alertDialog: AlertDialog = dialogBuilder.create()
        alertDialog.show()

        receivedEDT.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var paidAmount = 0.0
                var returnAmount: Double? = 0.00
                paidAmount = try {
                    if (receivedEDT.text.toString() == "" || receivedEDT.text.toString() != null
                    ) {
                        java.lang.Double.valueOf(receivedEDT.text.toString())
                    } else {
                        0.0
                    }
                } catch (e: java.lang.Exception) {
                    0.0
                }
                returnAmount = paidAmount - totalAmtDou
                val df = DecimalFormat("0.00##")
                returnTXT.text = df.format(returnAmount!!)
                when {
                    returnAmount > 0.00 -> returnTXT.setTextColor(resources.getColor(R.color.CancelbetColor))
                    returnAmount < 0.00 -> returnTXT.setTextColor(resources.getColor(R.color.TabColor))
                    else -> returnTXT.setTextColor(resources.getColor(R.color.color_black))
                }
            }
        })

        payBTN.setOnClickListener {
            val ReturnAmtDou = returnTXT.text.toString().toDouble()
            val ReceivedAmtDou = receivedEDT.text.toString()
            if (validationSalesVouchersPayment(ReturnAmtDou, ReceivedAmtDou, totalAmtDou)) {
                alertDialog.dismiss()
                if (card_validationLAY.visibility == View.VISIBLE) {
                    card_validationLAY.visibility = View.GONE
                }
                var customerCardAccID:String = if (Common.ClientId == Common.CardClientIDValidation) {
                    Common.CustomerCardAccountID
                } else {
                    "0"
                }
                val createVoucherpojo = CreateVoucherModel(
                    Common.UserId,
                    Common.TillId,
                    salesVoucherUpdate,
                    0.00,
                    0.00,
                    "",
                    Common.MobileMacAddress,
                    customerCardAccID
                )
                val stringInput = GsonBuilder().create().toJson(createVoucherpojo)
                if (SplashScreenActivity.isPrinterFlag) {
                    IsPrintBtnClickFlag = false
                    viewModel.pushCreateVoucher(createVoucherpojo)
                } else {
                    printSlip(createVoucherpojo)
                }
            }
        }

        cancelBTN.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    fun topLayout(width: Int, height: Int): Bitmap? {
        return try {
            top_layput.setDrawingCacheEnabled(true)
            top_layput.buildDrawingCache()
            val b: Bitmap = top_layput.getDrawingCache()
            Bitmap.createScaledBitmap(b, width, height, false)
        } catch (ex: java.lang.Exception) {
            //CrashAnalytics.CrashReport(ex);
            val Error = ex.message
            null
        }
    }

    fun toplogoLayout(width: Int, height: Int): Bitmap? {
        return try {
            logos_layput.setDrawingCacheEnabled(true)
            logos_layput.buildDrawingCache()
            val b: Bitmap = top_layput.getDrawingCache()
            Bitmap.createScaledBitmap(b, width, height, false)
        } catch (ex: java.lang.Exception) {
            //CrashAnalytics.CrashReport(ex);
            val Error = ex.message
            null
        }
    }

    fun bottomLayout(width: Int, height: Int): Bitmap? {
        return try {
            footer_layput.setDrawingCacheEnabled(true)
            footer_layput.buildDrawingCache()
            val bb: Bitmap = footer_layput.getDrawingCache()
            Bitmap.createScaledBitmap(bb, width, height, false)
        } catch (ex: java.lang.Exception) {
            //CrashAnalytics.CrashReport(ex);
            val Error = ex.message
            null
        }
    }

    fun topup_printout(
        topupPrintoutDetailsModel: List<TopupPrintoutDetailsModel>,
        bitmap: Bitmap,
    ) {
        try {
            for (index in topupPrintoutDetailsModel.indices) {
                print("index $index")
                tsc.clearbuffer()
                tsc.sendcommand(
                    """
                    SIZE 69.9 mm, 7.6 mm
                    GAP 0 mm, 0 mm
                    DIRECTION 1,0
                    REFERENCE 0,0
                    OFFSET 0 mm
                    SET PEEL OFF
                    SET CUTTER OFF
                    SET PARTIAL_CUTTER OFF
                    SET TEAR ON
                    CLS
                    CODEPAGE 1252
                    TEXT 416,52,"ROMAN.TTF",180,1,12,"WWW.SURIBET.SR"
                    BAR 0,4, 558, 2
                    PRINT 1,1
                    """.trimIndent()
                )
                // print logo
                // createVoucher_logo.setImageBitmap(bitmap)
                val decodedImageCom = getResizedBitmap(bitmap, 300)
                // val drawable: BitmapDrawable = createVoucher_logo.getDrawable() as BitmapDrawable
                // val bitmapfafaf: Bitmap = drawable.getBitmap()
                // val logolayoutBitmao = toplogoLayout(380, 100)
                tsc.clearbuffer()
                tsc.sendcommand(
                    """
                           SIZE 69.9 mm, 20 mm
                           GAP 0 mm, 0 mm
                           DIRECTION 0,0
                           REFERENCE 0,0
                           OFFSET 0 mm
                           SET PEEL OFF
                           SET CUTTER OFF
                           SET PARTIAL_CUTTER OFF
                           SET TEAR ON
                           CLS

                           """.trimIndent()
                )
                //tsc.sendbitmap(10, 10, decodedImageCom)
                tsc.sendbitmap_resize(10, 10, decodedImageCom, 400, 100)
                tsc.printlabel(1, 1)
                // topup Details
                val locAddress: String = topupPrintoutDetailsModel[index].LocationAddress.toString()
                val locationname: String =
                    topupPrintoutDetailsModel[index].LocationName.toString()
                val slipid: String = topupPrintoutDetailsModel[index].VoucherId.toString()
                val tillId: String = topupPrintoutDetailsModel[index].TillCode.toString()
                val phonenumber: String = topupPrintoutDetailsModel[index].LocationPhone.toString()
                val cashier: String = topupPrintoutDetailsModel[index].UserName.toString()
                var sRDMoney: String = topupPrintoutDetailsModel[index].CurrencyWithCode.toString()
                sRDMoney = "$sRDMoney"
                var pin_code = "PIN # "
                pin_code += topupPrintoutDetailsModel[index].PIN.toString()
                val barcodecontent = topupPrintoutDetailsModel[index].Barcode.toString()
                val termsCondition: List<String> =
                    Common.VoucherTerms.split("by")
                var bottom_box: String? = ""
                var bottom_box1: String? = ""
                if (termsCondition.size == 2) {
                    bottom_box = termsCondition[0]
                    bottom_box1 = "by " + termsCondition[1]
                }
                val customercare: String =
                    "CUSTOMER SERVICE NUMBER " + topupPrintoutDetailsModel[index].CustomerCare
                val date_time = topupPrintoutDetailsModel[index].PrintingDateTime.toString()

                val topupVoucherNew = """
                    SIZE 69.9 mm, 63.6 mm
                    GAP 0 mm, 0 mm
                    DIRECTION 1,0
                    REFERENCE 0,0
                    OFFSET 0 mm
                    SET PEEL OFF
                    SET CUTTER OFF
                    SET PARTIAL_CUTTER OFF
                    SET TEAR ON
                    CLS
                    BOX 4,82,552,167,3
                    CODEPAGE 1252
                    TEXT 547,73,"0",180,8,8,"$customercare"
                    TEXT 218,40,"0",180,10,10,"$date_time"
                    TEXT 543,117,"0",180,7,7,"$bottom_box1"
                    TEXT 543,150,"0",180,7,7,"$bottom_box" 
                    BAR 0,493, 557, 3
                    TEXT 548,482,"0",180,7,7,"Vocuher #"
                    TEXT 437,482,"0",180,7,7,"$slipid"
                    BOX 12,397,545,450,3
                    TEXT 333,436,"0",180,10,10,"$sRDMoney"
                    TEXT 419,380,"0",180,10,10,"$pin_code"
                    BARCODE 496,327,"39",102,0,180,3,8,"$barcodecontent"
                    TEXT 329,220,"0",180,10,10,"$barcodecontent"
                    PRINT 1,1
                    <xpml></page></xpml><xpml><end/></xpml>
                """.trimIndent()


                /*val topUpVoucherStr = """
               SIZE 69.9 mm, 76.2 mm
               GAP 0 mm, 0 mm
               DIRECTION 1,0
               REFERENCE 0,0
               OFFSET 0 mm
               SET PEEL OFF
               SET CUTTER OFF
               SET PARTIAL_CUTTER OFF
               SET TEAR ON
               CLS
               BOX 5,81,553,165,3
               CODEPAGE 1252
               TEXT 548,72,"0",180,8,8,"$customercare"
               TEXT 219,39,"0",180,10,10,"$date_time"
               TEXT 544,116,"0",180,7,7,"$bottom_box1"
               TEXT 544,148,"0",180,7,7,"$bottom_box"
               TEXT 354,596,"0",180,9,9,"$locationname"
               TEXT 334,562,"0",180,8,8,"$phonenumber"
               BAR 1,529, 557, 3
               TEXT 549,518,"0",180,7,7,"Vocuher #"
               TEXT 438,518,"0",180,7,7,"$slipid"
               TEXT 254,518,"0",180,7,7,"Loc"
               TEXT 206,518,"0",180,7,7,"$locAddress"
               TEXT 549,487,"0",180,7,7,"Cashier #"
               TEXT 438,487,"0",180,7,7,"$cashier"
               BOX 13,395,546,448,3
               TEXT 334,434,"0",180,10,10,"$sRDMoney"
               TEXT 419,378,"0",180,10,10,"$pin_code"
               BARCODE 414,319,"39",77,0,180,1,3,"$barcodecontent"
               TEXT 385,237,"0",180,10,10,"$barcodecontent"
               PRINT 1,1
               <xpml></page></xpml><xpml><end/></xpml>
               """.trimIndent()*/
                tsc.clearbuffer()
                tsc.sendcommand(topupVoucherNew)
                // Rules and conditions
                /*  var footerContnt: List<String> = Common.VoucherFooterText.split("@")
                salesVoucherUpdateFooter.clear()
                for (i in footerContnt.indices) {
                    val check = footerContnt[i].replace("\"", "");
                    val footer = """
                  SIZE 69.9 mm, 5 mm
                  DIRECTION 1,0
                  REFERENCE 0,0
                  OFFSET 0 mm
                  SET PEEL OFF
                  SET CUTTER OFF
                  SET PARTIAL_CUTTER OFF
                  SET TEAR ON
                  CLS
                  CODEPAGE 1252
                  TEXT 556,33,"ROMAN.TTF",180,1,7,"$check"
                  PRINT 1,1
                  """.trimIndent()
                    salesVoucherUpdateFooter.add(footer)
                }
                *//* for (i in salesVoucherUpdateFooter.indices) {
                 tsc.clearbuffer()
                 tsc.sendcommand(salesVoucherUpdateFooter[i])
             }*//*

            tsc.clearbuffer()
            val bottom = """
                <xpml><page quantity='0' pitch='25.4 mm'></xpml>SIZE 69.9 mm, 25.4 mm
                GAP 0 mm, 0 mm
                DIRECTION 1,0
                REFERENCE 0,0
                OFFSET 0 mm
                SET PEEL OFF
                SET CUTTER OFF
                SET PARTIAL_CUTTER OFF
                <xpml></page></xpml><xpml><page quantity='1' pitch='25.4 mm'></xpml>SET TEAR ON
                CLS
                BOX 5,107,553,191,3
                CODEPAGE 125
                TEXT 544,142,"0",180,7,7,"$bottom_box1"
                TEXT 544,174,"0",180,7,7,"$bottom_box"
                TEXT 548,98,"0",180,8,8,"$customercare"
                TEXT 219,65,"0",180,10,10,"$date_time"
                PRINT 1,1
                <xpml></page></xpml><xpml><end/></xpml>
                """.trimIndent()
            tsc.sendcommand(bottom)*/
            }
            //IsPrintBtnClickFlag = true
            Handler().postDelayed({ printerport_close() }, 5000)
        } catch (ex: Exception) {
            //IsPrintBtnClickFlag = true
            ex.printStackTrace()
            printerport_close()
        }
    }

    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap? {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    fun isNullOrEmpty(str: String?): Boolean {
        return str == null || str.isEmpty() || str == "null"
    }

    fun StringToBitMap(encodedString: String?): Bitmap? {
        return try {
            Common.Clientlogobyte =
                Base64.decode(encodedString, Base64.NO_PADDING)
            BitmapFactory.decodeByteArray(
                Common.Clientlogobyte,
                0,
                Common.Clientlogobyte.size
            )
        } catch (ex: java.lang.Exception) {
            CrashAnalytics.CrashReport(ex)
            val Error = ex.message
            null
        }
    }
}
