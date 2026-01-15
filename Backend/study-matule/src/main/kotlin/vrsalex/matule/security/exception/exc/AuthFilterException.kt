package vrsalex.matule.security.exception.exc

import vrsalex.matule.security.exception.MySecurityException


internal class AuthFilterException(message: String): MySecurityException(message, 401)