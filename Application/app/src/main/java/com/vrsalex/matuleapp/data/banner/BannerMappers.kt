package com.vrsalex.matuleapp.data.banner

import com.vrsalex.matuleapp.data.local.db.entity.BannerEntity
import com.vrsalex.matuleapp.domain.banner.Banner
import com.vrsalex.network.api.dto.response.BannerResponse

fun BannerResponse.toEntity(baseUrl: String) = BannerEntity(
    id = this.id,
    name = this.title,
    price = this.price,
    imageUrl = baseUrl + this.image
)

fun BannerEntity.toBanner() = Banner(
    id = this.id,
    title = this.name,
    price = this.price,
    imageUrl = this.imageUrl
)