package com.themovieguide.domain.model

import com.themovieguide.domain.utils.EMPTY

data class MetaMessage(
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean,
) {
    constructor() : this(
        statusCode = 0,
        statusMessage = EMPTY,
        success = false,
    )
}
