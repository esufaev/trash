open class Vehicle {
    open fun lowerTemperature(){
        println("Turn down temperature")
    }
}
open class Car : Vehicle(){
    override fun lowerTemperature() {
        println("Turn on air conditioning")
    }
}
class ConvertibleCar: Car(){
   final override fun lowerTemperature() {
        println("Open roof")
    }
}