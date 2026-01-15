package vrsalex.matule.security.exception.exc

import vrsalex.matule.security.exception.MySecurityException


internal class JwtTokenException(message: String): MySecurityException(message, 401)