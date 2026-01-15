package vrsalex.matule.application.exception.exc

import vrsalex.matule.application.exception.MyApplicationException

internal class PhoneVerificationRequiredException(message: String): MyApplicationException(message, 403)