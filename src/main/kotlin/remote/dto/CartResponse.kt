package remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartProducts(
    val productId: Int,
    val quantity: Int
)

@Serializable
data class CartResponse(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<CartProducts>
)
