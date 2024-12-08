fun printResult(userChoice: String, gameChoice: String) {
    val result: String
    //Определить результат
    if (userChoice == gameChoice) result = "Tie!"
    else if ((userChoice == "Rock" && gameChoice == "Scissors") ||
    (userChoice == "Paper" &&  gameChoice == "Rock") ||
    (userChoice == "Scissors" &&  gameChoice == "Paper")) result = "You win!"
    else result = "You lose!"
    //Вывести результат
    println("You chose $userChoice. I chose $gameChoice. $result")
}