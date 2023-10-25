package remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import remote.dto.CartResponse
import remote.dto.ProductRequest
import remote.dto.ProductResponse

interface CartService {
    //  Product Methods
    suspend fun getAllProducts(): List<ProductResponse>
    suspend fun getASingleProduct(productId: Int): ProductResponse?
    suspend fun getLimitedNumberOfProducts(quantity: Int): List<ProductResponse>
    suspend fun getProductsSortedDesc(): List<ProductResponse>
    suspend fun getProductsSortedAsc(): List<ProductResponse>
    suspend fun getAllCategories(): List<String>
    suspend fun getProductsInCategory(category: String): List<ProductResponse>
    suspend fun getProductsInMultipleCategories(categories: List<String>): List<ProductResponse>
    suspend fun addNewProduct(request: ProductRequest): ProductResponse?
    suspend fun editProduct(request: ProductRequest, productId: Int): ProductResponse?
    suspend fun deleteProduct(productId: Int): ProductResponse?

    //  Cart Methods
    suspend fun getAllCarts(): List<CartResponse>
    suspend fun getAllCartsBySpecificProductId(productId: Int): List<CartResponse>


    companion object {
        fun create(): CartService {
            return CartServiceImpl(
                client = HttpClient(OkHttp) {
                    engine {
                        config {
                            followRedirects(true)
                        }
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }
                }
            )
        }
    }
}