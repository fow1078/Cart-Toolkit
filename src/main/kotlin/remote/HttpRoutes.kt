package remote

object HttpRoutes {
    private const val BASE_URL: String = "https://fakestoreapi.com"
    const val PRODUCTS: String = "$BASE_URL/products"
    const val CARTS: String = "$BASE_URL/carts"
    const val USERS: String = "$BASE_URL/users"
    const val LOGIN: String = "$BASE_URL/auth/login"
}