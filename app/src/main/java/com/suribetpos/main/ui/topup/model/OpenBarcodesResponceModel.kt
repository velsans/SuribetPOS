package com.suribetpos.main.ui.topup.model

/**
 * Created by DEV 27 on 03/02/2017.
 */
data class OpenBarcodesResponceModel(
    var ErrorMessage: String? = null,
    var Openbarcodes: List<OpenBarcodesModel>? = null,
)