package vrsalex.matule.security.exception

abstract class MySecurityException(message: String, val errorCode: Int = 401): RuntimeException(message)