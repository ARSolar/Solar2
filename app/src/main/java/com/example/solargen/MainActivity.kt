package com.example.solargen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solargen.databinding.ActivityMainBinding
import kotlin.math.cos
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateGeneration() }
    }

    private fun calculateGeneration() {
        val panels = binding.panelCount.text.toString().toIntOrNull() ?: 0
        val orientation = binding.orientation.text.toString().toDoubleOrNull() ?: 0.0
        val tilt = binding.tilt.text.toString().toDoubleOrNull() ?: 0.0
        val shadow = parseShadowHours(binding.shadowHours.text.toString())

        val yearlyInsulation = getYearlySolarIrradiance(orientation, tilt, shadow)
        val generationKWh = panels * yearlyInsulation * PANEL_EFFICIENCY

        binding.resultText.text = getString(R.string.annual_generation, generationKWh)
    }

    private fun parseShadowHours(text: String): List<Int> {
        return text.split(",", " ")
            .mapNotNull {
                val parts = it.split("-")
                if (parts.size == 2) {
                    val start = parts[0].toIntOrNull()
                    val end = parts[1].toIntOrNull()
                    if (start != null && end != null) (start until end).toList() else null
                } else {
                    null
                }
            }
            .flatten()
    }

    private fun getYearlySolarIrradiance(orientation: Double, tilt: Double, shadow: List<Int>): Double {
        val baseIrradiance = 1500 // kWh/m^2 year, placeholder for Brazilian average
        val orientationFactor = cos(Math.toRadians(orientation))
        val tiltFactor = cos((tilt - OPTIMAL_TILT) * PI / 180)
        val shadowFactor = 1 - shadow.size / 24.0

        return baseIrradiance * orientationFactor * tiltFactor * shadowFactor
    }

    companion object {
        private const val OPTIMAL_TILT = 23.5
        private const val PANEL_EFFICIENCY = 0.18 // placeholder efficiency
    }
}
