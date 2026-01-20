package vrsalex.matule.application.exception.exc

import vrsalex.matule.application.exception.MyApplicationException

internal class ProductNotExistException(message: String): MyApplicationException(message, 406)