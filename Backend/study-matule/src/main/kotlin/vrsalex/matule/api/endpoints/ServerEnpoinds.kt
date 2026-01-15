package vrsalex.matule.api.endpoints

object ServerEndpoints {

    object API{
        const val API_PREFIX = "/api"
        const val AUTH_PREFIX = "/auth"

        const val AUTH_REFRESH_TOKEN_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/refresh-token"
        const val AUTH_USER_REGISTER_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/register"
        const val AUTH_USER_REGISTER_VERIFY_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/register/verify"
        const val AUTH_USER_LOGIN_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/login"
        const val AUTH_USER_LOGOUT_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/logout"


        const val PROFILE_PREFIX = "/profile"
        const val PROFILE_GET_DATA_ENDPOINT = "$API_PREFIX$PROFILE_PREFIX/get"


        const val PROJECT_PREFIX = "/project"
        const val USER_PROJECTS_GET_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/get"
        const val USER_PROJECTS_ADD_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/add"
        const val USER_PROJECTS_REMOVE_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/remove"

        const val USER_PROJECT_TYPE_GET_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/type/get"
        const val USER_PROJECT_CATEGORY_GET_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/category/get"


        val PUBLIC_ENDPOINTS = setOf(
            AUTH_REFRESH_TOKEN_ENDPOINT,
            AUTH_USER_REGISTER_ENDPOINT,
            AUTH_USER_REGISTER_VERIFY_ENDPOINT,
            AUTH_USER_LOGIN_ENDPOINT
        )
    }

}