package com.example.testapplicationwithrxjava2.models

import java.io.Serializable

data class CryptoMain(
    val id: String,
    val logo_url: String,
    val name: String,
    val price: String,
    val rank: String
): Serializable