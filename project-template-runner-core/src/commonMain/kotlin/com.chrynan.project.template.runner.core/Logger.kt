package com.chrynan.project.template.runner.core

interface Logger {

    fun echoMessage(message: String)

    fun echoWarning(message: String, throwable: Throwable? = null)

    fun echoError(message: String, throwable: Throwable? = null)
}
