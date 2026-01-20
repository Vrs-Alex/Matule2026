package vrsalex.matule.infrastructure.persisntence.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import vrsalex.matule.infrastructure.persisntence.entity.BannerEntity

interface BannerJpaRepository: JpaRepository<BannerEntity, Long>