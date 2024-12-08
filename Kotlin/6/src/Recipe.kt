data class Recipe_1(val title: String,
                  val mainIngredient:String,
                  val isVegetarian:Boolean = false,
                  val difficulty:String = "Easy"
    ) {
}
fun main(Args : Array<String>) {
    val r = Recipe_1("Spaghetti Bolognese","Beef")
}
