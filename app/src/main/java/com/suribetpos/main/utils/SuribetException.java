package com.suribetpos.main.utils;

public class SuribetException extends Exception {

    private static final long serialVersionUID = 4664456874499611218L;
    private String errorCode = "Unknown_Exception";

    public static boolean APIException(int ErrCode) {
        boolean HttpResponce = true;
        switch (ErrCode) {
            case 200:
                HttpResponce = true;
                break;
            case 401:
                HttpResponce = false;
                break;
            case 402:
                HttpResponce = false;
                break;
            case 408:
                HttpResponce = false;
                break;
            case 500:
                HttpResponce = true;
                break;
        }
        return HttpResponce;
    }

    public static String PrinterCurrentStatus(String Printer_status) {
        String CurrentStatus = null;
        if (Printer_status.equals("01")) {
            CurrentStatus = "Head Open";
        } else if (Printer_status.equals("02")) {
            CurrentStatus = "Paper Jam";
        } else if (Printer_status.equals("03")) {
            CurrentStatus = "Paper Jam and head opened";
        } else if (Printer_status.equals("04")) {
            CurrentStatus = "Out of paper";
        } else if (Printer_status.equals("05")) {
            CurrentStatus = "Out of paper and head opened";
        } else if (Printer_status.equals("08")) {
            CurrentStatus = "Out of ribbon";
        } else if (Printer_status.equals("09")) {
            CurrentStatus = "Out of ribbon and head opened";
        } else if (Printer_status.equals("0A")) {
            CurrentStatus = "Out of ribbon and paper jam";
        } else if (Printer_status.equals("0B")) {
            CurrentStatus = "Out of ribbon, paper jam and head opened";
        } else if (Printer_status.equals("0C")) {
            CurrentStatus = "Out of ribbon and out of paper";
        } else if (Printer_status.equals("0D")) {
            CurrentStatus = "Out of ribbon, out of paper and head opened";
        } else if (Printer_status.equals("10")) {
            CurrentStatus = "Pause";
        } else if (Printer_status.equals("20")) {
            CurrentStatus = "Printing";
        } else if (Printer_status.equals("80")) {
            CurrentStatus = "Other error";
        } else if (Printer_status.equals("-1")) {
            CurrentStatus = "please Check the printer ";
        } else if (Printer_status.equals("")) {
            CurrentStatus = "please wait while print previous slip";
        }
        return CurrentStatus;
    }

    public static String TransferSlipValues(int Size) {
        String Values = "";
        if (Size == 1) {
            Values = "167" + "-" + "4" + "-" + "33.3";
        } else if (Size == 2 || Size == 3) {
            Values = "149" + "-" + "17" + "-" + "40.9";
        } else if (Size == 4 || Size == 5) {
            Values = "131" + "-" + "4" + "-" + "42.2";
        } else if (Size == 6 || Size == 7) {
            Values = "113" + "-" + "6" + "-" + "47.3";
        } else if (Size == 8 || Size == 9) {
            Values = "95" + "-" + "3" + "-" + "51";
        } else if (Size == 10 || Size == 11) {
            Values = "77" + "-" + "11" + "-" + "57.3";
        } else if (Size == 12 || Size == 13 || Size == 14) {
            Values = "59" + "-" + "8" + "-" + "61.3";
        } else if (Size == 15 || Size == 16 || Size == 17) {
            Values = "41" + "-" + "15" + "-" + "67.6";
        } else if (Size == 18 || Size == 19 || Size == 20) {
            Values = "23" + "-" + "18" + "-" + "72.7";
        } else if (Size == 21 || Size == 22) {
            Values = "5" + "-" + "15" + "-" + "76.4";
        } /*else if (Size == 20 || Size == 21) {
            Values = "131" + "-" + "38.2";
        } else if (Size == 22 || Size == 23) {
            Values = "131" + "-" + "38.2";
        } else if (Size == 24 || Size == 25) {
            Values = "131" + "-" + "38.2";
        }*/
        return Values;
    }
}
