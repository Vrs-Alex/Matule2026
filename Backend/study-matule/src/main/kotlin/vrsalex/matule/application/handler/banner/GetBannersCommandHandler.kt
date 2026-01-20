package vrsalex.matule.application.handler.banner

import org.springframework.stereotype.Component
import vrsalex.matule.application.result.banner.BannerResult
import vrsalex.matule.domain.port.repository.BannerRepository

@Component
class GetBannersCommandHandler(
    private val bannerRepository: BannerRepository
) {

    operator fun invoke(): List<BannerResult> {
        return bannerRepository.getBanners()
    }

}