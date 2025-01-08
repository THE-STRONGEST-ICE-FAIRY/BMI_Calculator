package com.example.bmicalculator

class BMICalculator {
    companion object {
        fun calculateBMI(height: Double, weight: Double, heightUnit: String, weightUnit: String): String {
            // Convert height to meters
            val heightInMeters = when (heightUnit.lowercase()) {
                "cm" -> height / 100
                "m" -> height
                "ft" -> {
                    val feet = height.toInt()
                    val inches = ((height - feet) * 100).toInt()
                    (feet * 0.3048) + (inches * 0.0254)
                }
                "inch" -> height * 0.0254
                else -> throw IllegalArgumentException("Invalid height unit")
            }

            // Convert weight to kilograms
            val weightInKg = when (weightUnit.lowercase()) {
                "kg" -> weight
                "lbs" -> weight * 0.453592
                else -> throw IllegalArgumentException("Invalid weight unit")
            }

            // Calculate BMI
            val bmi = weightInKg / (heightInMeters * heightInMeters)
            val status = when {
                bmi < 18.5 -> "Underweight"
                bmi in 18.5..24.9 -> "Normal weight"
                bmi in 25.0..29.9 -> "Overweight"
                else -> "Obese"
            }

            return "BMI: %.2f, Status: $status".format(bmi)
        }
    }
}