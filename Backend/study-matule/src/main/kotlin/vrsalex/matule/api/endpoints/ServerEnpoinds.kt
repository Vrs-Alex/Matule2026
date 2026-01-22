package vrsalex.matule.api.endpoints

object ServerEndpoints {

    object API{
        const val API_PREFIX = "/api"
        const val AUTH_PREFIX = "/auth"

        const val AUTH_REFRESH_TOKEN_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/refresh-token"
        const val AUTH_USER_REGISTER_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/signup"
        const val AUTH_USER_REGISTER_VERIFY_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/register/verify"
        const val AUTH_USER_LOGIN_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/login"
        const val AUTH_USER_LOGOUT_ENDPOINT = "$API_PREFIX$AUTH_PREFIX/logout"


        const val PROFILE_PREFIX = "/profile"
        const val PROFILE_GET_DATA_ENDPOINT = "$API_PREFIX$PROFILE_PREFIX/get"

        const val BANNER_PREFIX = "/banner"
        const val BANNER_GET_ENDPOINT = "$API_PREFIX$BANNER_PREFIX/get"

        const val CATEGORY_PREFIX = "/category"
        const val CATEGORY_GET_ENDPOINT = "$API_PREFIX$CATEGORY_PREFIX/get"

        const val PRODUCTS_PREFIX = "/product"
        const val PRODUCTS_GET_ENDPOINT = "$API_PREFIX$PRODUCTS_PREFIX/get"

        const val CART_PREFIX = "/cart"
        const val USER_CART_GET_ENDPOINT = "$API_PREFIX$CART_PREFIX/get"
        const val USER_CART_ADD_ENDPOINT = "$API_PREFIX$CART_PREFIX/add"
        const val USER_CART_REMOVE_ENDPOINT = "$API_PREFIX$CART_PREFIX/remove/{cart_item_id}"
        const val USER_CART_REMOVE_ALL_ENDPOINT = "$API_PREFIX$CART_PREFIX/remove/all"

        const val PROJECT_PREFIX = "/project"
        const val USER_PROJECTS_GET_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/get"
        const val USER_PROJECTS_ADD_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/add"
        const val USER_PROJECTS_REMOVE_ENDPOINT = "$API_PREFIX$PROJECT_PREFIX/remove"

        const val IMAGES_PREFIX = "$API_PREFIX/image"



        val PUBLIC_ENDPOINTS = setOf(
            AUTH_REFRESH_TOKEN_ENDPOINT,
            AUTH_USER_REGISTER_ENDPOINT,
            AUTH_USER_REGISTER_VERIFY_ENDPOINT,
            AUTH_USER_LOGIN_ENDPOINT
        )
    }

}