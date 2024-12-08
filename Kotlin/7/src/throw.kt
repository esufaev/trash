fun setWorkRatePercentAge(x:Int){
    if (x !in 0..100){
        throw IllegalArgumentException("Percentage not in range 0..100 $x")
        }
    //Код,выполняемый для допустимого аргумента
}
fun main(args: Array<String>) {
    try {
        setWorkRatePercentAge(110)
    } catch (e: IllegalArgumentException){
        //код обработки исключения
    }
}
