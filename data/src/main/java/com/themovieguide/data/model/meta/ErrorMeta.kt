package com.themovieguide.data.model.meta

data class ErrorMeta(
    val status_code: Int,
    val status_message: String,
    val success: Boolean,
)
