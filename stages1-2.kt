 package converter

 fun converter(value: Double, unitMeasure: String): Double {
     var result = 0.0
     when (unitMeasure) {
         "kilometers", "kilometer", "km" -> result = value * 1000
         "m", "meter", "meters" -> result = value
         "cm", "centimeter", "centimeters" -> result = value * 0.01
         "mm", "millimeter", "millimeters" -> result = value * 0.001
         "mi", "mile", "miles" -> result = value * 1609.35
         "yd", "yard", "yards" -> result = value * 0.9144
         "ft", "foot", "feet" -> result = value * 0.3048
         "in", "inch", "inches" -> result = value * 0.0254
     }
     return result
 }

fun main() {
    print("Enter a number and a measure of length: ")
    val userImput = readln().lowercase().split(" ").toList()
    val value = userImput[0].toDouble()
    var unitMeasure = userImput[1]
    val units = listOf("kilometers", "km", "kilometer",
        "m", "meter", "meters", "cm", "centimeter", "centimeters",
        "mm", "millimeter", "millimeters", "mi", "mile", "miles",
        "yd", "yard", "yards", "ft", "foot", "feet", "in", "inch", "inches")

    if (unitMeasure !in units) {
        println("Wrong input. Unknown unit $unitMeasure")
    } else {
        print("$value ")
        val result = converter(value, unitMeasure)
        val resultUnit = if (result == 1.0) "meter" else "meters"
        when (unitMeasure) {
            "kilometers", "kilometer", "km" -> unitMeasure = if (value == 1.0) "kilometer" else "kilometers"
            "m", "meter", "meters" -> unitMeasure = if (value == 1.0) "meter" else "meters"
            "cm", "centimeter", "centimeters" -> unitMeasure = if (value == 1.0) "centimeter" else "centimeters"
            "mm", "millimeter", "millimeters" -> unitMeasure = if (value == 1.0) "millimeter" else "millimeters"
            "yd", "yard", "yards" -> unitMeasure = if (value == 1.0) "yard" else "yards"
            "ft", "foot", "feet" -> unitMeasure = if (value == 1.0) "foot" else "feet"
            "in", "inch", "inches" -> unitMeasure = if (value == 1.0) "inch" else "inches"
            "mi", "mile", "miles" -> unitMeasure = if (value == 1.0) "mile" else "miles"
        }
        print("$unitMeasure is $result $resultUnit")
    }
}
