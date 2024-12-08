fun getUserChoice(optionsParam: Array<String>): String {
    //Запросить у пользователя его выбор
    print("Please enter one of the following:")
    for (item in optionsParam) print(" $item")
    println(".")
    //Прочитать пользовательский ввод
    val userInput = readLine()
}