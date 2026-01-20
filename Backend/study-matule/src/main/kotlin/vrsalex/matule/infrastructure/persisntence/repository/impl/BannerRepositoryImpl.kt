package vrsalex.matule.infrastructure.persisntence.repository.impl

import org.springframework.stereotype.Repository
import vrsalex.matule.domain.model.Banner
import vrsalex.matule.domain.port.repository.BannerRepository
import vrsalex.matule.infrastructure.persisntence.mapper.toDomain
import vrsalex.matule.infrastructure.persisntence.repository.jpa.BannerJpaRepository

@Repository
class BannerRepositoryImpl(
    private val bannerJpaRepository: BannerJpaRepository
): BannerRepository {
    override fun getBanners(): List<Banner> {
        return bannerJpaRepository.findAll().map { it.toDomain() }
    }
}