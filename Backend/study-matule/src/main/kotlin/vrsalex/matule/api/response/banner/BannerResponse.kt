package vrsalex.matule.api.response.banner

import kotlinx.serialization.Serializable
import vrsalex.matule.domain.model.Banner

@Serializable
data class BannerResponse(
    val id: Long,
    val title: String,
    val price: String,
    val image: String
)