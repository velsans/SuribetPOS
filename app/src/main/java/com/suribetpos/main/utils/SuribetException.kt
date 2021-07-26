package com.suribetpos.main.utils

class SuribetException {
    private val errorCode = "Unknown_Exception"

    companion object {
        private const val serialVersionUID = 4664456874499611218L
        fun PrinterCurrentStatus(Printer_status: String): String? {
            var CurrentStatus: String? = null
            if (Printer_status == "01") {
                CurrentStatus = "Head Open"
            } else if (Printer_status == "02") {
                CurrentStatus = "Paper Jam"
            } else if (Printer_status == "03") {
                CurrentStatus = "Paper Jam and head opened"
            } else if (Printer_status == "04") {
                CurrentStatus = "Out of paper"
            } else if (Printer_status == "05") {
                CurrentStatus = "Out of paper and head opened"
            } else if (Printer_status == "08") {
                CurrentStatus = "Out of ribbon"
            } else if (Printer_status == "09") {
                CurrentStatus = "Out of ribbon and head opened"
            } else if (Printer_status == "0A") {
                CurrentStatus = "Out of ribbon and paper jam"
            } else if (Printer_status == "0B") {
                CurrentStatus = "Out of ribbon, paper jam and head opened"
            } else if (Printer_status == "0C") {
                CurrentStatus = "Out of ribbon and out of paper"
            } else if (Printer_status == "0D") {
                CurrentStatus = "Out of ribbon, out of paper and head opened"
            } else if (Printer_status == "10") {
                CurrentStatus = "Pause"
            } else if (Printer_status == "20") {
                CurrentStatus = "Printing"
            } else if (Printer_status == "80") {
                CurrentStatus = "Other error"
            } else if (Printer_status == "-1") {
                CurrentStatus = "please Check the printer "
            } else if (Printer_status == "") {
                CurrentStatus = "please wait while print previous slip"
            }
            return CurrentStatus
        }

        @JvmStatic
        fun APIException(ErrCode: Int): Boolean {
            var HttpResponce = true
            when (ErrCode) {
                200 -> HttpResponce = true
                401 -> HttpResponce = false
                402 -> HttpResponce = false
                408 -> HttpResponce = false
                500 -> HttpResponce = true
            }
            return HttpResponce
        }
    }
}