package com.suribetpos.main.data.api

class ServiceURL {
    /*URL's*/
    companion object {
        const val LOCAL_URL = "http://demo.ysecit.in:8014/SuribetMobilePOSAPI/"
        //const val LIVE_URL = "https://dxa.suribet.bet/SuribetMobileApi/"//"https://dxa.suribet.sr/SuribetMobileApi/"
        const val LIVE_URL = "https://dxa.suribet.bet/SuribetMobileTest_Api/"//"https://dxa.suribet.sr/SuribetMobileApi/"
        const val Language_URL = "http://dx.suribet.bet/LanguageResourceAPI/api/"//"http://dx.suribet.sr/LanguageResourceAPI/api/"
        const val securityControllorName = "security"
        const val TopUpController = "TopUp"
        const val ResetPassControllor = "ChangePassword"
        const val LanguageMethod = "Language"
        const val ApplicationUpdateMethod = "Language"
        const val CashoutControllName = "CashOut"
        const val CardValidation = "Customer"

        /*Final Url*/
        @JvmField
        val BASE_URL = Live_server()

        @JvmField
        var Local_live: String? = null

        @JvmField
        var Local_live_Flag: Boolean? = null


        private fun Local_server(): String {
            Local_live = "Local"
            Local_live_Flag = false
            return LOCAL_URL
        }

        private fun Live_server(): String {
            Local_live = "Live"
            Local_live_Flag = true
            return LIVE_URL
        }
    }
}