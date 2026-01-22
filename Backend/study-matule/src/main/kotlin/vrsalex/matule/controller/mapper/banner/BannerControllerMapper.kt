package vrsalex.matule.controller.mapper.banner

import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.response.banner.BannerResponse
import vrsalex.matule.domain.model.Banner

fun Banner.toResponse() = BannerResponse(
    id = this.id!!,
    title = this.title,
    price = this.price,
    image = "image/" + this.image,
)