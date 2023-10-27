import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import remote.CartService

fun main() {
    runBlocking {
        val service = CartService.create()

        // Cart Methods Use
        val products = service.getAllProducts()
        //  println(products)

        val product = service.getASingleProduct(1)
        //  println(product)

        val specificNumberOfProducts = service.getLimitedNumberOfProducts(1)
        //  println(specificNumberOfProducts)

        val productsDesc = service.getProductsSortedDesc()
        val productsAsc = service.getProductsSortedAsc()

        val categories = service.getAllCategories()
        val productsByCategory = service.getProductsInCategory(categories[0])
        // println(productsByCategory)

        val productsByCategories = service.getProductsInMultipleCategories(listOf(categories[0], categories[2]))
        // println(productsByCategories)

        val carts = service.getAllCarts()
        val cartsByProductId = service.getAllCartsBySpecificProductId(1)
        //  println(cartsByProductId)
    }
}