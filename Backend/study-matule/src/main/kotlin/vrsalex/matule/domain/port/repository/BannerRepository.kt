package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.Banner


interface BannerRepository {

    fun getBanners(): List<Banner>
}