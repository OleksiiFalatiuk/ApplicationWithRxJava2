package com.example.testapplicationwithrxjava2.utils

import android.util.Log
import com.example.testapplicationwithrxjava2.BuildConfig

fun debugDo(isWork: Boolean = true, foo: () -> Unit) {
    if (isWork && BuildConfig.DEBUG) {
        foo()
    }
}

private fun logCreator(txt: String, part: Int) {
    if (BuildConfig.DEBUG) {
        val tag = "!!!"
        val cti = runCatching { Throwable().stackTrace[part] }.getOrNull()
        val str = "${cti?.methodName} / (${cti?.fileName}:${cti?.lineNumber}) : ${txt}\n."
        try {
            Log.e(tag, str)
        } catch (xxx: Exception) {
            System.out.println("${tag}: ${str}")
        }
    }
}


fun logInfo(txt: String, part: Int = 3) {
    logCreator(txt, part)
}

fun Throwable.logErrorStackTrace(part: Int = 3) {
    logCreator(this.stackTraceToString(), part)
}

fun Throwable.logError(part: Int = 3, errorPart: Int = 0) {
    val error = runCatching { this.stackTrace[errorPart] }.getOrNull()
    logCreator(
        "ERROR: ${this.javaClass.canonicalName}: ${this.message} \n ${error}",
        part
    )
}