package vrsalex.matule.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.domain.port.repository.UserRepository
import vrsalex.matule.security.exception.exc.AuthFilterException
import vrsalex.matule.security.jwt.JwtServiceImpl


@Component("apiAuthFilter")
class ApiAuthFilter(
    @param:Value("\${jwt.prefix}") private val tokenPrefix: String,
    private val jwtServiceImpl: JwtServiceImpl,
    private val userRepository: UserRepository
): OncePerRequestFilter() {

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return ServerEndpoints.API.PUBLIC_ENDPOINTS.contains(request.requestURI)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.requestURI
        val token = extractTokenFromRequest(request)

        if (token != null) {
            if (!path.startsWith(ServerEndpoints.API.AUTH_REFRESH_TOKEN_ENDPOINT)) {
                authenticateRequest(token)
            }
        }
        filterChain.doFilter(request, response)
    }


    private fun authenticateRequest(accessToken: String) {
        if (SecurityContextHolder.getContext().authentication == null){
            if (!jwtServiceImpl.validateAccessToken(accessToken))
                throw AuthFilterException("Invalid access token")
            val userId = jwtServiceImpl.getUserIdFromToken(accessToken)
            val user = userRepository.findById(userId)
            if (user != null) {
                val auth = UsernamePasswordAuthenticationToken(user, null, null)
                SecurityContextHolder.getContext().authentication = auth
            }
            else {
                throw AuthFilterException("User with id $userId not found")
            }
        }
    }


    private fun extractTokenFromRequest(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")
        if (header != null && header.startsWith(tokenPrefix)) {
            return header.substring(tokenPrefix.length)
        }
        return null
    }


}