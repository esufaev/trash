fun myFunction(str:String) {
    try{
        var x = str.toInt()
        println(x)
    } catch(e: NumberFormatException){
        println("Bummer")
    }
    println("myFunction has ended")
}