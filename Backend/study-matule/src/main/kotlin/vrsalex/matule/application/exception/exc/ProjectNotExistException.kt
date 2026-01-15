package vrsalex.matule.application.exception.exc

import vrsalex.matule.application.exception.MyApplicationException

internal class ProjectNotExistException(message: String): MyApplicationException(message, 404)