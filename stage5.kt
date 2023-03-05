 package converter

 import java.util.*

 enum class UnitType {LENGTH, WEIGHT, TEMPERATURE, UNKNOWN}

 enum class Units(val type: UnitType, val normalizedName: String, val factor: Double) {
     METER(UnitType.LENGTH, "meter", 1.0),
     KILOMETER(UnitType.LENGTH, "kilometer", 1_000.0),
     CENTIMETER(UnitType.LENGTH, "centimeter", 0.01),
     MILLIMETER(UnitType.LENGTH, "millimeter", 0.001),
     MILE(UnitType.LENGTH, "mile", 1_609.35),
     YARD(UnitType.LENGTH, "yard", 0.9144),
     FOOT(UnitType.LENGTH, "foot", 0.3048),
     INCH(UnitType.LENGTH, "inch", 0.0254),

     GRAM(UnitType.WEIGHT, "gram", 1.0),
     KILOGRAM(UnitType.WEIGHT, "kilogram", 1_000.0),
     MILLIGRAM(UnitType.WEIGHT, "milligram", 0.001),
     POUND(UnitType.WEIGHT, "pound", 453.592),
     OUNCE(UnitType.WEIGHT, "ounce", 28.3495),

     CELSIUS(UnitType.TEMPERATURE, "degree Celsius", 0.0),
     KELVIN(UnitType.TEMPERATURE, "kelvin", 0.0),
     FAHRENHEIT(UnitType.TEMPERATURE, "degree Fahrenheit", 0.0),

     UNKNOWN(UnitType.UNKNOWN, "???", 0.0);
 }

 fun meterConverter(value: Double, source: Units): Double {
     var result = 0.0
     when (source.normalizedName) {
         "kilometer" -> result = value * Units.KILOMETER.factor
         "meter" -> result = Units.METER.factor
         "centimeter" -> result = value * Units.CENTIMETER.factor
         "millimeter" -> result = value * Units.MILLIMETER.factor
         "mile" -> result = value * Units.MILE.factor
         "yard" -> result = value * Units.YARD.factor
         "foot" -> result = value * Units.FOOT.factor
         "inch" -> result = value * Units.INCH.factor
     }
     return result
 }

 fun lengthConverter(value: Double, target: Units): Double {
     var result = 0.0
     when (target.normalizedName) {
         "kilometer" -> result = value / Units.KILOMETER.factor
         "meter" -> result = Units.METER.factor
         "centimeter" -> result = value / Units.CENTIMETER.factor
         "millimeter" -> result = value / Units.MILLIMETER.factor
         "mile" -> result = value / Units.MILE.factor
         "yard" -> result = value / Units.YARD.factor
         "foot" -> result = value / Units.FOOT.factor
         "inch" -> result = value / Units.INCH.factor
     }
     return result
 }

 fun gramConverter(value: Double, source: Units): Double {
     var result = 0.0
     when (source.normalizedName) {
         "gram" -> result = Units.GRAM.factor
         "kilogram" -> result = value * Units.KILOGRAM.factor
         "milligram" -> result = value * Units.MILLIGRAM.factor
         "pound" -> result = value * Units.POUND.factor
         "ounce" -> result = value * Units.OUNCE.factor
     }
     return result
 }

 fun weightConverter(value: Double, target: Units): Double {
     var result = 0.0
     when (target.normalizedName) {
         "gram" -> result = Units.GRAM.factor
         "kilogram" -> result = value / Units.KILOGRAM.factor
         "milligram" -> result = value / Units.MILLIGRAM.factor
         "pound" -> result = value / Units.POUND.factor
         "ounce" -> result = value / Units.OUNCE.factor
     }
     return result
 }

 fun tempConverter(source: Units, target: Units, sourceValue: Double): Double {
     val result: Double
     when {
         source == Units.FAHRENHEIT && target == Units.FAHRENHEIT -> result = sourceValue
         source == Units.CELSIUS && target == Units.CELSIUS -> result = sourceValue
         source == Units.KELVIN && target == Units.KELVIN -> result = sourceValue
         source == Units.FAHRENHEIT && target == Units.CELSIUS -> result = (sourceValue - 32.0) * 5.0 / 9.0
         source == Units.CELSIUS && target == Units.FAHRENHEIT -> result = sourceValue * 9.0 / 5.0 + 32.0
         source == Units.CELSIUS && target == Units.KELVIN -> result = sourceValue + 273.15
         source == Units.KELVIN && target == Units.CELSIUS -> result = sourceValue - 273.15
         source == Units.KELVIN && target == Units.FAHRENHEIT -> result = sourceValue * 9.0 / 5.0 - 459.67
         source == Units.FAHRENHEIT && target == Units.KELVIN -> result = (sourceValue + 459.67) * 5.0 / 9.0
         else -> result = sourceValue * source.factor / target.factor
     }
     return result
 }

 fun unitNormalizer(unit: String): Units {
     return when (unit.lowercase(Locale.getDefault())) {
         "m", "meter", "meters" -> Units.METER
         "km", "kilometer", "kilometers" -> Units.KILOMETER
         "cm", "centimeter", "centimeters" -> Units.CENTIMETER
         "mm", "millimeter", "millimeters" -> Units.MILLIMETER
         "mi", "mile", "miles" -> Units.MILE
         "yd", "yard", "yards" -> Units.YARD
         "ft", "foot", "feet" -> Units.FOOT
         "in", "inch", "inches" -> Units.INCH
         "g", "gram", "grams" -> Units.GRAM
         "kg", "kilogram", "kilograms" -> Units.KILOGRAM
         "mg", "milligram", "milligrams" -> Units.MILLIGRAM
         "lb", "pound", "pounds" -> Units.POUND
         "oz", "ounce", "ounces" -> Units.OUNCE
         "c", "dc", "celsius" -> Units.CELSIUS
         "k", "kelvin", "kelvins" -> Units.KELVIN
         "f", "df", "fahrenheit" -> Units.FAHRENHEIT
         else -> Units.UNKNOWN
     }
 }

 fun plural(unit: String) = when (unit) {
     "foot" -> "feet"
     "inch" -> "inches"
     "degree Celsius" -> "degrees Celsius"
     "degree Fahrenheit" -> "degrees Fahrenheit"
     "???" -> "???"
     else -> unit + "s"
 }

 fun processUserInput(input: String): Triple<Double, Units, Units>? {
     val cleanedInput = input
         .replace("degrees ", "")
         .replace("degree ", "")

     val (sourceValue, sourceUnit, _, targetUnit) = cleanedInput.split(" ")
     val source = unitNormalizer(sourceUnit)
     val target = unitNormalizer(targetUnit)

     return try {
         Triple(sourceValue.toDouble(), source, target)
     } catch (_: NumberFormatException) {
         null
     }
 }

 fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val userInput = readln().lowercase()
        if (userInput == "exit") break

        val cleanedInput = processUserInput(userInput)
        if (cleanedInput == null) {
            println("parse error")
            continue
        }
        val (sourceValue, source, target) = cleanedInput

        if (source == Units.UNKNOWN || target == Units.UNKNOWN) {
            val displaySource = plural(source.normalizedName)
            val displayTarget = plural(target.normalizedName)
            println("Conversion from $displaySource to $displayTarget is impossible\n")
            continue
        } else if (source.type != target.type) {
            println("Conversion from ${plural(source.normalizedName)} to ${plural(target.normalizedName)} is impossible")
            continue
        } else if (sourceValue < 0.0) {
            if (source.type == UnitType.LENGTH) {
                println("Length shouldn't be negative")
                continue
            } else if (source.type == UnitType.WEIGHT) {
                println("Weight shouldn't be negative")
                continue
            }
        } else {
            val displaySource = if (sourceValue == 1.0) source.normalizedName else plural(source.normalizedName)
            val displayTarget: String
            if (source.type == UnitType.LENGTH && target.type == UnitType.LENGTH) {
                val meterResult = meterConverter(sourceValue, source)
                val lengthResult = lengthConverter(meterResult, target)
                displayTarget = if (lengthResult == 1.0) target.normalizedName else plural(target.normalizedName)
                println("$sourceValue $displaySource is $lengthResult $displayTarget")
            } else if (source.type == UnitType.WEIGHT && target.type == UnitType.WEIGHT) {
                val gramResult = gramConverter(sourceValue, source)
                val weightResult = weightConverter(gramResult, target)
                displayTarget = if (weightResult == 1.0) target.normalizedName else plural(target.normalizedName)
                println("$sourceValue $displaySource is $weightResult $displayTarget")
            } else if (source.type == UnitType.TEMPERATURE && target.type == UnitType.TEMPERATURE) {
                val tempResult = tempConverter(source, target, sourceValue)
                displayTarget = if (tempResult == 1.0) target.normalizedName else plural(target.normalizedName)
                println("$sourceValue $displaySource is $tempResult $displayTarget")
            }
        }
    }
}
