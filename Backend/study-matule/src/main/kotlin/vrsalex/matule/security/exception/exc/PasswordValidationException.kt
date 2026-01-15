package vrsalex.matule.security.exception.exc

import vrsalex.matule.security.exception.MySecurityException


internal class PasswordValidationException(message: String): MySecurityException(message, 400)