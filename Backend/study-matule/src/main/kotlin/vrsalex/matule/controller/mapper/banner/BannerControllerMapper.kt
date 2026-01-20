package vrsalex.matule.controller.mapper.banner

import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.response.banner.BannerResponse
import vrsalex.matule.domain.model.Banner

fun Banner.toResponse() = BannerResponse(
    id = this.id!!,
    title = this.title,
    image = ServerEndpoints.API.IMAGES_PREFIX + "/" + this.image,
)