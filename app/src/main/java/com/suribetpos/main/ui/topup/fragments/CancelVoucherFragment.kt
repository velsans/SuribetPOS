package com.suribetpos.main.ui.topup.fragments

import android.bluetooth.BluetoothAdapter
import android.content.ComponentName
import android.content.DialogInterface
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tscdll.TSCActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.GsonBuilder
import com.google.zxing.integration.android.IntentIntegrator
import com.suribetpos.R
import com.suribetpos.main.ui.topup.adapter.CancelVoucherClickListener
import com.suribetpos.main.ui.topup.adapter.OpenBarcodesAdapter
import com.suribetpos.main.ui.topup.adapter.OpenBarcodesClickListener
import com.suribetpos.main.ui.topup.adapter.VerifyCancelVoucherAdapter
import com.suribetpos.main.ui.topup.model.*
import com.suribetpos.main.ui.topup.repo.CancelRepository
import com.suribetpos.main.ui.topup.ui.CaptureActivityPortrait
import com.suribetpos.main.ui.topup.viewmodel.CancelVoucherViewModel
import com.suribetpos.main.ui.topup.viewmodel.CancelVoucherViewModelFactory
import com.suribetpos.main.ui.view.SplashScreenActivity
import com.suribetpos.main.utils.*
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.cancel_voucher_fragment.*
import kotlinx.android.synthetic.main.cancelled_voucher_fragment.*
import kotlinx.android.synthetic.main.create_voucher_fragment.*
import kotlinx.android.synthetic.main.topup_create_print.*
import recieptservice.com.recieptservice.PrinterInterface


class CancelVoucherFragment : BottomSheetDialogFragment(), CancelVoucherClickListener,
    OpenBarcodesClickListener {
    private var IsPrintBtnClickFlag: Boolean = true
    var printerstatus: String = ""
    var tsc = TSCActivity()
    private var TSCConnInt: Int = -1
    private var REQUEST_ENABLE_BT: Int = 2
    var alert = AlertDialogManager()
    val intent = Intent()

    companion object {
        fun newInstance() = CancelVoucherFragment()
        val refresh_handler: Handler = Handler()
        var refresh_runnable: Runnable? = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        CancelledVoucherFragment.cancelledList()
        refresh_handler.removeCallbacks(refresh_runnable)
    }

    private lateinit var cancel_viewModel: CancelVoucherViewModel

    //val searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    val verifycancelVoucherList: MutableList<VerifyCanceVocuherResponceModel> =
        ArrayList()
    //val openBarcodesList: MutableList<OpenBarcodesModel> =        ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cancel_voucher_fragment, container, false)
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && str.isNotEmpty())
            return false
        return true
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val cancel_repository = CancelRepository()
        val cancelViewModelFactory = CancelVoucherViewModelFactory(cancel_repository)
        cancel_viewModel =
            ViewModelProvider(this, cancelViewModelFactory).get(CancelVoucherViewModel::class.java)
        val openBarcodesResponceModel = OpenBarcodesRequestModel(
            Common.UserId,
            Common.TillId,
            ""
        )

        refresh_runnable = object : Runnable {
            override fun run() {
                cancel_viewModel.getOpenBarcodes(openBarcodesResponceModel)
                refresh_handler.postDelayed(this, 10 * 1000)
            }
        }
        refresh_handler.postDelayed(refresh_runnable, 0 * 1000);

        cancel_viewModel.cancelVoucher.observe(viewLifecycleOwner, { cancelVoucher ->
            progressBar_cancel.visibility = View.GONE
            IsPrintBtnClickFlag = true
            if (cancelVoucher.isSuccessful) {
                //var cancelledVoucherList:String = cancelVoucher.body()?.m_Item1 as String
                if (cancelVoucher.body()?.m_Item1.isNullOrEmpty()) {
                    val cancelledVoucherList =
                        cancelVoucher.body()?.m_Item2 as List<CancelVoucherPrintoutModel>
                    if (SplashScreenActivity.isPrinterFlag) {
                        intent.setClassName(
                            "recieptservice.com.recieptservice",
                            "recieptservice.com.recieptservice.service.PrinterService"
                        )

                        activity?.bindService(intent, object : ServiceConnection {
                            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                                val mAidl: PrinterInterface =
                                    PrinterInterface.Stub.asInterface(service)
                                try {
                                    // printout design here
                                    for (i in cancelledVoucherList.indices) {
                                        var denomination_values: String? =
                                            cancelledVoucherList[i].CurrencyAmount
                                        if (denomination_values != null) {
                                            if (denomination_values.substring(denomination_values.length - 1) == "0") {
                                                denomination_values =
                                                    denomination_values.substring(
                                                        0,
                                                        denomination_values.length - 3
                                                    )
                                            }
                                        }
                                        topupcancelled_Username.text =
                                            cancelledVoucherList[i].UserName
                                        topupcancelled_TillCode.text =
                                            cancelledVoucherList[i].TillCode
                                        topupcancelled_LocationName.text =
                                            cancelledVoucherList[i].LocationName
                                        topupcancelled_TransectionDate.text =
                                            cancelledVoucherList[i].PrintingDateTime
                                        topupcancelled_CurrencyCode.text =
                                            cancelledVoucherList[i].CurrencyCode
                                        topupcancelled_CurrencyValues.text = denomination_values

                                        mAidl.setAlignment(0)
                                        mAidl.printBitmap(cancelled_Layout(380, 250))
                                        mAidl.nextLine(4)
                                    }


                                } catch (e: java.lang.Exception) {
                                    e.printStackTrace()
                                }
                            }

                            override fun onServiceDisconnected(name: ComponentName) {
                                Toast.makeText(
                                    requireContext(),
                                    name.toString(),
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }, AppCompatActivity.BIND_AUTO_CREATE)
                        // close bottom sheet fragments
                        dismiss()
                    } else {
                        cancel_printSlip(cancelledVoucherList)
                        // close bottom sheet fragments
                        dismiss()
                    }
                } else {
                    Toasty.error(
                        requireActivity(),
                        cancelVoucher.body()?.m_Item1.toString(),
                        Toasty.LENGTH_LONG,
                        true
                    ).show()
                }
            }
        })

        cancel_viewModel.verifycancelVoucher.observe(viewLifecycleOwner, { verifycancelVoucher ->
            progressBar_cancel.visibility = View.GONE
            if (verifycancelVoucher.isSuccessful) {
                val verifycancelVoucherModel =
                    verifycancelVoucher.body() as VerifyCanceVocuherResponceModel
                verifycancelVoucherList.clear()
                verifycancelVoucherList.add(verifycancelVoucherModel)
                if (verifycancelVoucherList.isNotEmpty()) {
                    if (verifycancelVoucherList[0].ErrorMessage.isNullOrEmpty()) {
                        cancelVoucher_RV.also {
                            it.layoutManager =
                                LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            it.setHasFixedSize(true)
                            it.adapter = verifycancelVoucherList?.let { it1 ->
                                VerifyCancelVoucherAdapter(
                                    it1, this
                                )
                            }
                        }
                        cancelVoucher_RV.visibility = View.VISIBLE
                        novalue_cancevoucher.visibility = View.GONE
                    } else {
                        Toasty.error(
                            requireContext(),
                            verifycancelVoucherList.get(0).ErrorMessage.toString(),
                            Toasty.LENGTH_SHORT,
                            true
                        ).show();
                        cancelVoucher_RV.visibility = View.GONE
                        novalue_cancevoucher.visibility = View.VISIBLE
                    }
                } else {
                    Toasty.error(
                        requireActivity(),
                        "please enter correct barcode",
                        Toasty.LENGTH_LONG,
                        true
                    ).show()
                    cancelVoucher_RV.visibility = View.GONE
                    novalue_cancevoucher.visibility = View.VISIBLE
                }
            }
        })

        cancel_viewModel.openbarcode.observe(viewLifecycleOwner, { openbarcode ->
            progressBar_cancel.visibility = View.GONE
            if (openbarcode.isSuccessful) {
                val verifycancelVoucherModel =
                    openbarcode.body()?.Openbarcodes as ArrayList<OpenBarcodesModel>

                //openBarcodesList.clear()
                if (verifycancelVoucherModel.isNotEmpty()) {
                    openbarcodes_RV.also {
                        it.layoutManager =
                            GridLayoutManager(
                                requireContext(), 2, GridLayoutManager.VERTICAL,
                                false
                            )
                        //it.setHasFixedSize(true)
                        it.adapter = verifycancelVoucherModel?.let { it1 ->
                            OpenBarcodesAdapter(
                                it1, this
                            )
                        }
                    }
                    openbarcodes_RV.visibility = View.VISIBLE
                    novalue_openbarcodes.visibility = View.GONE
                } else {
                    openbarcodes_RV.visibility = View.GONE
                    novalue_openbarcodes.visibility = View.VISIBLE
                }
            }
        })

    }

    fun cancelled_Layout(width: Int, height: Int): Bitmap? {
        return try {
            Cancelled_framelayout.setDrawingCacheEnabled(true)
            Cancelled_framelayout.buildDrawingCache()
            val b: Bitmap = Cancelled_framelayout.getDrawingCache()
            Bitmap.createScaledBitmap(b, width, height, false)
        } catch (ex: java.lang.Exception) {
            //CrashAnalytics.CrashReport(ex);
            val Error = ex.message
            null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verifyVoucherIMG.setOnClickListener {
            if (!verifycancelVoucherBarcodesEDT.text.isNullOrBlank() || !verifycancelVoucherBarcodesEDT.text.isNullOrEmpty()) {
                verifyBarcode(verifycancelVoucherBarcodesEDT.text.toString())
            } else {
                Toast.makeText(requireContext(), "please enter voucher barcode", Toast.LENGTH_LONG)
                    .show()
            }
        }

        verifyVoucherScanIMG.setOnClickListener {
            /*  val integrator = IntentIntegrator(activity)
              IntentIntegrator.forSupportFragment(this@CancelVoucherFragment)
                  .initiateScan()*/
            val integrator = IntentIntegrator(activity)
            IntentIntegrator.forSupportFragment(this@CancelVoucherFragment)
                .setPrompt("Scan a barcode")
                .setCameraId(0) // Use a specific camera of the device
                .setOrientationLocked(true)
                .setBeepEnabled(true)
                .setCaptureActivity(CaptureActivityPortrait::class.java)
                .initiateScan()
        }

        cancel_vouchersTXT.setOnClickListener {
            if (verifycancelVoucherList.isNotEmpty()) {
                val cancelSaleVoucher = CancelSaleVoucher(1, verifycancelVoucherList[0].VoucherID)
                val cancelSaleVoucherList: ArrayList<CancelSaleVoucher> = ArrayList()
                cancelSaleVoucherList.add(cancelSaleVoucher)
                val cancelVoucherRequestModel = CancelRequestModel(
                    Common.TillId,
                    Common.UserId,
                    Common.MobileMacAddress,
                    "",
                    "",
                    cancelSaleVoucherList,
                )
                val stringInput = GsonBuilder().create().toJson(cancelVoucherRequestModel)
                if (SplashScreenActivity.isPrinterFlag) {
                    progressBar_cancel.visibility = View.VISIBLE
                    cancel_viewModel.getCancelVoucher(cancelVoucherRequestModel)
                } else {
                    printSlip(cancelVoucherRequestModel)
                }

            } else {
                Toast.makeText(
                    requireContext(),
                    "please scan / enter voucher barcode",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Scanned: " + result.contents, Toast.LENGTH_LONG)
                    .show()
                verifycancelVoucherBarcodesEDT.setText(result.contents.toString())
                verifyBarcode(result.contents.toString())
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun verifyBarcode(barcodes: String?) {
        progressBar_cancel.visibility = View.VISIBLE
        val verifyCancelVoucherRequestModel = VerifyCancelVoucherRequestModel(
            Common.TillId,
            Common.UserId,
            barcodes
        )
        val stringInput = GsonBuilder().create().toJson(verifyCancelVoucherRequestModel)
        cancel_viewModel.getVerifyVoucher(verifyCancelVoucherRequestModel)
    }

    fun cancel_printSlip(cancelledVoucherList: List<CancelVoucherPrintoutModel>) {
        try {
            for (i in cancelledVoucherList.indices) {
                var denomination_velues: String? = cancelledVoucherList[i].CurrencyAmount
                if (denomination_velues != null) {
                    if (denomination_velues.substring(denomination_velues.length - 1) == "0") {
                        denomination_velues =
                            denomination_velues.substring(0, denomination_velues.length - 3)
                    }
                }
                //mDBInternalHelper.UpdateTruckPlateNumber(GwwMainActivity.this);
                /*ArrayList<String> allTables=mDBExternalHelper.AllTableNames();
        Log.e("GWWMainActivity","AllTables main oncreate :  "+allTables.get(2));
        for(String tableName:allTables){
            //Log.e("GWWMainActivity","AllTables main oncreate :  "+tableName);
        }*/
                val topupprintDetails = """
            <xpml><page quantity='0' pitch='38.2 mm'></xpml>SIZE 69.9 mm, 38.2 mm
            GAP 0 mm, 0 mm
            DIRECTION 1,0
            REFERENCE 0,0
            OFFSET 0 mm
            SET PEEL OFF
            SET CUTTER OFF
            SET PARTIAL_CUTTER OFF
            <xpml></page></xpml><xpml><page quantity='1' pitch='38.2 mm'></xpml>SET TEAR ON
            CLS
            BOX 9,7,550,297,3
            BAR 9,237, 539, 3
            BAR 71,238, 3, 58
            CODEPAGE 1252
            TEXT 59,283,"0",180,12,12,"TV"
            TEXT 541,283,"0",180,12,12,"CANCELLED"
            BOX 9,7,211,223,3
            BAR 9,174, 200, 3
            TEXT 163,215,"0",180,12,12,"REFUND"
            TEXT 147,152,"0",180,14,14,"${cancelledVoucherList[i].CurrencyCode}"
            TEXT 541,214,"0",180,12,12,"${cancelledVoucherList[i].UserName}"
            TEXT 541,163,"0",180,12,12,"${cancelledVoucherList[i].TillCode}"
            TEXT 541,62,"0",180,12,12,"${cancelledVoucherList[i].PrintingDateTime}"
            TEXT 541,113,"0",180,12,12,"${cancelledVoucherList[i].LocationName}"
            TEXT 160,89,"0",180,14,14,"$denomination_velues /-"
            PRINT 1,1
            <xpml></page></xpml><xpml><end/></xpml>
        """.trimIndent()

/*
                val topUpVouchercancelStr = """
     <xpml><page quantity='0' pitch='38.2 mm'></xpml>SIZE 72 mm, 38.2 mm
     GAP 0 mm, 0 mm
     DIRECTION 1,0
     REFERENCE 0,0
     OFFSET 0 mm
     SET PEEL OFF
     SET CUTTER OFF
     SET PARTIAL_CUTTER OFF
     <xpml></page></xpml><xpml><page quantity='1' pitch='38.2 mm'></xpml>SET TEAR ON
     CLS
     BOX 8,7,568,297,3
     BAR 8,237, 559, 3
     BAR 89,238, 3, 58
     CODEPAGE 1252
     TEXT 68,282,"0",180,12,12,"TV"
     TEXT 560,282,"0",180,12,12,"CANCELLED"
     BOX 8,7,229,223,3
     BAR 8,174, 220, 3
     TEXT 181,214,"0",180,12,12,"REFUND"
     TEXT 155,152,"0",180,14,14,"$cancelledVoucherList[i].CurrencyCode"
     TEXT 560,213,"0",180,12,12,"${cancelledVoucherList[i].UserName}"
     TEXT 560,162,"0",180,12,12,"${cancelledVoucherList[i].TillCode}"
     TEXT 560,61,"0",180,12,12,"${cancelledVoucherList[i].PrintingDateTime}"
     TEXT 560,112,"0",180,12,12,"${cancelledVoucherList[i].LocationName}"
     TEXT 168,89,"0",180,14,14,"$denomination_velues /-"
     PRINT 1,1
     <xpml></page></xpml><xpml><end/></xpml>
     """.trimIndent()*/
                tsc.clearbuffer()
                tsc.sendcommand(topupprintDetails)
            }
            TSCConnInt = -1
            tsc.closeport()
        } catch (ex: Exception) {
            TSCConnInt = -1
            tsc.closeport()
            ex.printStackTrace()
        }
    }

    // printout for topup
    private fun printSlip(cancelVoucherRequestModel: CancelRequestModel) {
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
                        progressBar_cancel.visibility = View.VISIBLE
                        cancel_viewModel.getCancelVoucher(cancelVoucherRequestModel)
                    } else {
                        TSCConnInt = -1
                        tsc.closeport()
                        alertDialogBox(
                            CommonMessage(R.string.TSC_Conn),
                            SuribetException.PrinterCurrentStatus(printerstatus),
                            false
                        )
                    }
                } else {
                    alertDialogBox(
                        CommonMessage(R.string.PrinterHead), CommonMessage(R.string.PrinterHDmsg),
                        false
                    )
                }
            }
        } else {
            alertDialogBox(
                CommonMessage(R.string.PrinterHead), CommonMessage(R.string.Printing),
                false
            )

        }
    }

    fun alertDialogBox(Title: String?, Message: String?, YesNo: Boolean) {
        if (Common.AlertDialogVisibleFlag === true) {
            alert.showAlertDialog(activity, Title, Message, YesNo)
        } else {
            //do something here... if already showing
        }
    }

    fun CommonMessage(Common_Msg: Int): String? {
        return activity?.getResources()?.getString(Common_Msg)
    }

    override fun verifyVoucherRecyclerClick(
        view: View,
        salesvouchers: List<VerifyCanceVocuherResponceModel>,
        position: Int
    ) {
        //Toast.makeText(requireContext(), "Cancel Voucher Clicked", Toast.LENGTH_SHORT).show()
        verifycancelVoucherBarcodesEDT.setText("")
        cancelVoucher_RV.visibility = View.GONE
        novalue_cancevoucher.visibility = View.VISIBLE
        verifycancelVoucherList.clear()
    }

    override fun openBarcodesRecyclerClick(
        view: View,
        openBarCodes: List<OpenBarcodesModel>,
        position: Int
    ) {
        try {
            verifyBarcode(openBarCodes[position].Barcode)
        } catch (e: java.lang.Exception) {
            Toast.makeText(
                requireContext(),
                e.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}