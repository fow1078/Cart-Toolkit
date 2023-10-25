package remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String,
)
