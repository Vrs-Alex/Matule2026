package vrsalex.matule.controller.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.response.banner.BannerResponse
import vrsalex.matule.application.handler.banner.GetBannersCommandHandler
import vrsalex.matule.controller.mapper.banner.toResponse
import vrsalex.matule.domain.model.Banner

@RestController
class BannerController(
    private val getBannersCommandHandler: GetBannersCommandHandler
) {

    @GetMapping(ServerEndpoints.API.BANNER_GET_ENDPOINT)
    fun getBanners(): ResponseEntity<List<BannerResponse>> {
        val banners = getBannersCommandHandler().map { it.toResponse() }
        return ResponseEntity.ok(banners)
    }

}