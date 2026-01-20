package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.domain.model.Banner
import vrsalex.matule.infrastructure.persisntence.entity.BannerEntity

fun BannerEntity.toDomain(): Banner = Banner(
    id = this.id!!,
    title = this.title,
    price = this.price,
    image = this.image
)