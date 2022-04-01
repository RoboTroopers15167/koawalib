package com.asiankoala.koawalib.util

data class LoggerConfig(
    var isLogging: Boolean = true,
    var isPrinting: Boolean = false,
    var isLoggingTelemetry: Boolean = false,
    var isDebugging: Boolean = false,
    var maxErrorCount: Int = 10
)