package com.suribetpos.main.ui.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.suribetpos.R;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.data.fcm.CrashAnalytics;

import java.text.DecimalFormat;

public class DenominationActivity extends Activity {


    TableLayout mTlayout;
    LinearLayout linear;
    TableRow tr;
    ImageView btnClose;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_denominationcurrency);
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG);
        }

        linear = findViewById(R.id.linearDenomLayout);
        mTlayout = findViewById(R.id.TableLayout1);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
            return;
        });

        if (Common.objDenominationDisplay == null) {
            new GetDenominationValue().execute();
        } else {
            GetDenominationValue gt = new GetDenominationValue();
            gt.DisplayDenominationWithEvent();
        }

    }

    class GetDenominationValue extends AsyncTask<String, String, String> {
        private ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(DenominationActivity.this);
            progressDialog.setMessage(CommonMessage(R.string.PleaseWait));
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        DecimalFormat df = new DecimalFormat("0.00##");

        public void DisplayDenominationWithEvent() {
            int TotalNumberOfDenom = 0;
            try {
                TotalNumberOfDenom = Common.ClientDenomination.size(); //Common.objDenominationDisplay.length;
            } catch (Exception ex) {
                CrashAnalytics.CrashReport(ex);
            }
            int NumberOfColumns = 3;
            int NumberOfRows = 0;
            NumberOfRows = TotalNumberOfDenom / NumberOfColumns;
            int ReminderRow = TotalNumberOfDenom % NumberOfColumns;
            if (ReminderRow > 0) {
                NumberOfRows += 1;
            }
            int ItemsCount = -1;
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;

            Button[] dynamic_button = new Button[TotalNumberOfDenom];
            for (int rowsCount = 0; rowsCount < NumberOfRows; rowsCount++) {
                TableRow tr = new TableRow(DenominationActivity.this);
                for (int columnsCount = 0; columnsCount < NumberOfColumns; columnsCount++) {
                    ItemsCount = ItemsCount + 1;
                    if (ItemsCount < TotalNumberOfDenom) {
                        dynamic_button[ItemsCount] = new Button(DenominationActivity.this);
                        dynamic_button[ItemsCount].setId(ItemsCount);
                        dynamic_button[ItemsCount].setTag(Common.ClientDenomination.get(ItemsCount).getDenominationID()
                                + "@" + Common.ClientDenomination.get(ItemsCount).getCurrencyID());
                        dynamic_button[ItemsCount].setText(String.valueOf(Common.ClientDenomination.get(ItemsCount).getDenomination()));
                        dynamic_button[ItemsCount].setBackgroundResource(R.drawable.drawlable);
                        dynamic_button[ItemsCount].setHeight(15);
                        dynamic_button[ItemsCount].setWidth(15);
                        tr.addView(dynamic_button[ItemsCount]);

                        dynamic_button[ItemsCount].setOnClickListener(v -> {

                            if (v instanceof Button) {
                                Intent intent = new Intent();
                                String[] SplitString = v.getTag().toString().split("@");
                                int DenominationId = Integer.valueOf(SplitString[0]);
                                int CurrencyId = Integer.valueOf(SplitString[1]);
                                Double Denomination = Double.valueOf((String) ((Button) v).getText());

                                intent.putExtra("DenominationValue", df.format(Denomination));
                                intent.putExtra("DenominationId", String.valueOf(DenominationId));
                                intent.putExtra("CurrencyId", String.valueOf(CurrencyId));
                                setResult(RESULT_OK, intent);
                                finish();
                                return;
                            }
                        });

                    } else {
                        break;
                    }
                }
                mTlayout.addView(tr);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            runOnUiThread(() -> DisplayDenominationWithEvent());
        }
    }

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }
}
