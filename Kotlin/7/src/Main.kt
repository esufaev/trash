class AnimalException:Exception() {
}
class Wolf{
    var hunger = 10
    val food = "meat"
    fun eat(){
        println("The wolf is eating $food")
    }
}
class MyWolf {
    var wolf:Wolf? = Wolf()
    fun MyFunction(){
        wolf?.eat()
    }
}
fun getAlphaWolf():Wolf?{
    return Wolf()
}
fun main(args:Array<String>){
    var w: Wolf? = Wolf()
    if (w != null) {
        w.eat()
    }
    var x = w?.hunger
    println("The value of x is $x")
    var y = w?.hunger ?: -1
    println("The value of y is $y")
    var myWolf = MyWolf()
    myWolf?.wolf?.hunger = 8
    println("The value of myWolf?.wolf?.hunger is ${myWolf?.wolf?.hunger}")
    var myArray = arrayOf("Hi", "Hello", null)
    for (item in myArray) {
        item?.let { println(it) }
    }
    getAlphaWolf()?.let { it.eat() }
    w = null
    val h =w?.hunger ?: throw AnimalException()
}