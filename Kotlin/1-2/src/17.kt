fun main(args: Array<String>) {
    val options = arrayOf("Rock", "Paper", "Scissors")
    val gameChoice = getGameChoice2(options)
    val userChoice = getUserChoice2(options)
}
fun getGameChoice2(optionsParam: Array<String>) =
    optionsParam[(Math.random() * optionsParam.size).toInt()]
fun getUserChoice2(optionsParam: Array<String>): String {
    var isValidChoice = false
    var userChoice = ""
    //Выполнять цикл, пока пользователь не введет допустимый вариант
    while (!isValidChoice) {
        //Запросить у пользователя его выбор
        print("Please enter one of the following:")
        for (item in optionsParam) print(" $item")
        println(".")
        //Прочитать пользовательский ввод
        val userInput = readLine()
        //Проверить пользовательский ввод
        if (userInput != null && userInput in optionsParam) {
            isValidChoice = true
            userChoice = userInput
        }
        //Если выбран недопустимый вариант, сообщить пользователю
        if (!isValidChoice) println("You must enter a valid choice.")
    }
    return userChoice
}