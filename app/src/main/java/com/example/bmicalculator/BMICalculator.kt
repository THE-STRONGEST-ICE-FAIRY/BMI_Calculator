package com.example.bmicalculator

class BMICalculator {
    companion object {
        fun calculateBMI(height: Double, inches: Double, weight: Double, heightUnit: String, weightUnit: String): Double {
            // Convert height to meters
            val heightInMeters = when (heightUnit.lowercase()) {
                "cm" -> height / 100
                "m" -> height
                "ft" -> {
                    (height * 0.3048) + (inches * 0.0254)
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

            return bmi
        }

        fun calculateStatus(bmi: Double): String {
            val status = when {
                bmi < 18.5 -> "UNDERWEIGHT"
                bmi in 18.5..24.9 -> "NORMAL WEIGHT"
                bmi in 25.0..29.9 -> "OVERWEIGHT"
                bmi in 30.0..34.9 -> "OBESITY CLASS I"
                bmi in 35.0..39.9 -> "OBESITY CLASS II"
                else -> "OBESITY CLASS III"
            }
            return status
        }
    }
}