package com.suribetpos.main.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.suribetpos.main.data.fcm.CrashAnalytics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import timber.log.Timber;

public class CreatePDF {

    public static void createPdfinDevice(Activity context, Bitmap bitmap, String directory_path, String VoucherID) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;
        int convertHighet = (int) hight, convertWidth = (int) width;
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        // write the document content

        File file = new File(directory_path);
        if (!file.exists()) {
            Timber.v("File Created : %s", String.valueOf(file.mkdirs()));
        }
        String dest = directory_path + "VoucherId-" + VoucherID + ".pdf";
        File filePath;
        filePath = new File(dest);
        try {
            document.writeTo(new FileOutputStream(filePath));
        } catch (IOException ex) {
            ex.printStackTrace();
            //Toast.makeText(getActivity(), "Something wrong: " + ex.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
        Toast.makeText(context, "PDF Saved Sucessfully" + filePath, Toast.LENGTH_SHORT).show();
    }

    public static Bitmap CreateBarcodeImage(String BarcodeString) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Bitmap bitmap = null;
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(BarcodeString, BarcodeFormat.CODE_128, 600, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
        } catch (WriterException ex) {
            CrashAnalytics.CrashReport(ex);
            ex.printStackTrace();
        }
        return bitmap;
    }
}

