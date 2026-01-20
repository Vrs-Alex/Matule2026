package vrsalex.matule.application.exception.exc

import vrsalex.matule.application.exception.MyApplicationException

internal class CartNotExistItemException(message: String): MyApplicationException(message, 406)