package remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import remote.dto.CartResponse
import remote.dto.ProductRequest
import remote.dto.ProductResponse

data class CartServiceImpl(
    private val client: HttpClient
): CartService {
    //  ============================
    //       Product Methods
    //  ============================
    override suspend fun getAllProducts(): List<ProductResponse> {
        return try {
            client.get(HttpRoutes.PRODUCTS).body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getASingleProduct(productId: Int): ProductResponse? {
        return try {
            client.get("${HttpRoutes.PRODUCTS}/$productId").body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun getLimitedNumberOfProducts(quantity: Int): List<ProductResponse> {
        return try {
            client.get("${HttpRoutes.PRODUCTS}/?limit=$quantity").body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getProductsSortedDesc(): List<ProductResponse> {
        return try {
            client.get("${HttpRoutes.PRODUCTS}/?sort=desc").body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getProductsSortedAsc(): List<ProductResponse> {
        return try {
            client.get("${HttpRoutes.PRODUCTS}/?sort=asc").body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getAllCategories(): List<String> {
        return try {
            client.get("${HttpRoutes.PRODUCTS}/categories").body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getProductsInMultipleCategories(categories: List<String>): List<ProductResponse> {
        return try {
            val products: List<ProductResponse> = client.get(HttpRoutes.PRODUCTS).body()
            products.filter { categories.contains(it.category) }
        }  catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getProductsInCategory(category: String): List<ProductResponse> {
        return try {
            val products: List<ProductResponse> = client.get(HttpRoutes.PRODUCTS).body();
            products.filter { it.category == category }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun addNewProduct(request: ProductRequest): ProductResponse? {
        try {
            val response: ProductResponse? = client.post {
                url(HttpRoutes.PRODUCTS)
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            return response
        } catch (e: RedirectResponseException) {
            println("Error" + e.response.status.description)
            return null
        } catch (e: ClientRequestException) {
            println("Error" + e.response.status.description)
            return null
        } catch (e: ServerResponseException) {
            println("Error" + e.response.status.description)
            return null
        } catch (e: Exception) {
            println("Error" + e.message)
            return null
        }
    }

    override suspend fun editProduct(request: ProductRequest, productId: Int): ProductResponse? {
        return try {
            client.put {
                url("${HttpRoutes.PRODUCTS}/$productId")
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        } catch (e: RedirectResponseException) {
            println("Error" + e.response.status.description)
            return null
        } catch (e: ClientRequestException) {
            println("Error" + e.response.status.description)
            return null
        } catch (e: ServerResponseException) {
            println("Error" + e.response.status.description)
            return null
        } catch (e: Exception) {
            println("Error" + e.message)
            return null
        }
    }

    override suspend fun deleteProduct(productId: Int): ProductResponse? {
        return try {
            client.delete("${HttpRoutes.PRODUCTS}/$productId").body()
        } catch (e: RedirectResponseException) {
            println(e.response.status.description)
            null
        } catch (e: ClientRequestException) {
            println(e.response.status.description)
            null
        } catch (e: ServerResponseException) {
            println(e.response.status.description)
            null
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }
    //  ============================
    //         Cart Methods
    //  ============================
    override suspend fun getAllCarts(): List<CartResponse> {
        return try {
            client.get(HttpRoutes.CARTS).body()
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getAllCartsBySpecificProductId(productId: Int): List<CartResponse> {
        return try {
            val response: List<CartResponse> = client.get(HttpRoutes.CARTS).body()
            val filtered = response.filter { it.products.map { product -> product.productId }.contains(productId) }
            filtered
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}
