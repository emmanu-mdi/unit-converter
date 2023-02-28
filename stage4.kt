 package converter

 fun meterConverter(value: Double, sourceUnit: String): Double {
     var result = 0.0
     when (sourceUnit) {
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

 fun lengthConverter(value: Double, targetUnit: String): Double {
     var result = 0.0
     when (targetUnit) {
         "kilometers", "kilometer", "km" -> result = value / 1000
         "m", "meter", "meters" -> result = value
         "cm", "centimeter", "centimeters" -> result = value * 100
         "mm", "millimeter", "millimeters" -> result = value * 1000
         "mi", "mile", "miles" -> result = value / 1609.35
         "yd", "yard", "yards" -> result = value / 0.9144
         "ft", "foot", "feet" -> result = value / 0.3048
         "in", "inch", "inches" -> result = value / 0.0254
     }
     return result
 }

 fun gramConverter(value: Double, sourceUnit: String): Double {
     var result = 0.0
     when (sourceUnit) {
         "g", "gram", "grams" -> result = value
         "kg", "kilogram", "kilograms" -> result = value * 1000
         "mg", "milligram", "milligrams" -> result = value * 0.001
         "lb", "pound", "pounds" -> result = value * 453.592
         "oz", "ounce", "ounces" -> result = value * 28.3495
     }
     return result
 }

 fun weightConverter(value: Double, targetUnit: String): Double {
     var result = 0.0
     when (targetUnit) {
         "g", "gram", "grams" -> result = value
         "kg", "kilogram", "kilograms" -> result = value * 0.001
         "mg", "milligram", "milligrams" -> result = value * 1000
         "lb", "pound", "pounds" -> result = value / 453.592
         "oz", "ounce", "ounces" -> result = value / 28.3495
     }
     return result
 }

 fun unitConverter(value: Double, unit: String): String {
     var unitDef = ""
     when (unit) {
         "kilometers", "kilometer", "km" -> unitDef = if (value == 1.0) "kilometer" else "kilometers"
         "m", "meter", "meters" -> unitDef = if (value == 1.0) "meter" else "meters"
         "cm", "centimeter", "centimeters" -> unitDef = if (value == 1.0) "centimeter" else "centimeters"
         "mm", "millimeter", "millimeters" -> unitDef = if (value == 1.0) "millimeter" else "millimeters"
         "yd", "yard", "yards" -> unitDef = if (value == 1.0) "yard" else "yards"
         "ft", "foot", "feet" -> unitDef = if (value == 1.0) "foot" else "feet"
         "in", "inch", "inches" -> unitDef = if (value == 1.0) "inch" else "inches"
         "mi", "mile", "miles" -> unitDef = if (value == 1.0) "mile" else "miles"
         "g", "gram", "grams" -> unitDef = if (value == 1.0) "gram" else "grams"
         "kg", "kilogram", "kilograms" -> unitDef = if (value == 1.0) "kilogram" else "kilograms"
         "mg", "milligram", "milligrams" -> unitDef = if (value == 1.0) "milligram" else "milligrams"
         "lb", "pound", "pounds" -> unitDef = if (value == 1.0) "pound" else "pounds"
         "oz", "ounce", "ounces" -> unitDef = if (value == 1.0) "ounce" else "ounces"
     }
     return unitDef
 }

 fun unitConverterForErros(unit: String): String {
     var unitForErro = ""
     when (unit) {
         "kilometers", "kilometer", "km" -> unitForErro = "kilometers"
         "m", "meter", "meters" -> unitForErro = "meters"
         "cm", "centimeter", "centimeters" -> unitForErro = "centimeters"
         "mm", "millimeter", "millimeters" -> unitForErro = "millimeters"
         "yd", "yard", "yards" -> unitForErro = "yards"
         "ft", "foot", "feet" -> unitForErro = "feet"
         "in", "inch", "inches" -> unitForErro = "inches"
         "mi", "mile", "miles" -> unitForErro = "miles"
         "g", "gram", "grams" -> unitForErro = "grams"
         "kg", "kilogram", "kilograms" -> unitForErro = "kilograms"
         "mg", "milligram", "milligrams" -> unitForErro = "milligrams"
         "lb", "pound", "pounds" -> unitForErro = "pounds"
         "oz", "ounce", "ounces" -> unitForErro = "ounces"
     }
     return unitForErro
 }

fun main() {
    val units = listOf("kilometers", "km", "kilometer",
        "m", "meter", "meters", "cm", "centimeter", "centimeters",
        "mm", "millimeter", "millimeters", "mi", "mile", "miles",
        "yd", "yard", "yards", "ft", "foot", "feet", "in", "inch", "inches",
        "g", "gram", "grams", "kg", "kilogram", "kilograms",
        "mg", "milligram", "milligrams", "lb", "pound", "pounds",
        "oz", "ounce", "ounces")
    val lengthUnits = listOf("kilometers", "km", "kilometer",
        "m", "meter", "meters", "cm", "centimeter", "centimeters",
        "mm", "millimeter", "millimeters", "mi", "mile", "miles",
        "yd", "yard", "yards", "ft", "foot", "feet", "in", "inch", "inches")
    val weightUnits = listOf("g", "gram", "grams", "kg", "kilogram", "kilograms",
        "mg", "milligram", "milligrams", "lb", "pound", "pounds",
        "oz", "ounce", "ounces")

    while (true) {
        print("Enter what you want to convert (or exit): ")
        val userImput = readln().lowercase().split(" ").toList()
        if (userImput[0] == "exit") break
        val value = userImput[0].toDoubleOrNull()
        var sourceUnit = userImput[1]
        var targetUnit = userImput[3]

        val erroSourceUnit = unitConverterForErros(sourceUnit)
        val erroTargetUnit = unitConverterForErros(targetUnit)
        if (sourceUnit !in units && targetUnit !in units) {
            println("Conversion from ??? to ??? is impossible\n")
            println()
        } else if (sourceUnit !in units && targetUnit in units) {
            println("Conversion from ??? to $erroTargetUnit is impossible\n")
            println()
        } else if (sourceUnit in units && targetUnit !in units) {
            println("Conversion from $erroSourceUnit to ??? is impossible\n")
            println()
        } else {
            sourceUnit = value?.let { unitConverter(it, sourceUnit) }.toString()
            if (sourceUnit in lengthUnits && targetUnit in lengthUnits) {
                val meterResult = value?.let { meterConverter(it, sourceUnit) }
                val lengthResult = meterResult?.let { lengthConverter(it, targetUnit) }
                targetUnit = lengthResult?.let { unitConverter(it, targetUnit) }.toString()
                print("$value ")
                print("$sourceUnit is $lengthResult $targetUnit\n")
                println()
            } else if (sourceUnit in weightUnits && targetUnit in weightUnits) {
                val gramResult= value?.let { gramConverter(it, sourceUnit) }
                val weightResult = gramResult?.let { weightConverter(it, targetUnit) }
                targetUnit = weightResult?.let { unitConverter(it, targetUnit) }.toString()
                print("$value ")
                print("$sourceUnit is $weightResult $targetUnit\n")
                println()
            } else {
                println("Conversion from $erroSourceUnit to $erroTargetUnit is impossible")
                println()
            }
        }
    }
}
