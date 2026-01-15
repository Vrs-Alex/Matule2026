package vrsalex.matule.application.exception

abstract class MyApplicationException(message: String, val errorCode: Int = 400): RuntimeException(message)