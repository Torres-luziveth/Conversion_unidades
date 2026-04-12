package com.example.parcialmovil

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class TemperatureFragment : Fragment() {

    private lateinit var etEntrada: TextInputEditText
    private lateinit var spinnerOrigen: Spinner
    private lateinit var spinnerDestino: Spinner
    private lateinit var etResultado: TextInputEditText

    private val unidadesTemp = arrayOf("Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_temperature, container, false)

        etEntrada = vista.findViewById(R.id.etInput)
        spinnerOrigen = vista.findViewById(R.id.spinnerFrom)
        spinnerDestino = vista.findViewById(R.id.spinnerTo)
        etResultado = vista.findViewById(R.id.etResult)

        configurarSpinners()
        configurarListenerEntrada()

        return vista
    }

    private fun configurarSpinners() {
        val adaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, unidadesTemp)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerOrigen.adapter = adaptador
        spinnerDestino.adapter = adaptador
    }

    private fun configurarListenerEntrada() {
        etEntrada.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                convertirTemperatura()
            }
        })
    }

    private fun convertirTemperatura() {
        val textoEntrada = etEntrada.text.toString()
        if (textoEntrada.isEmpty()) {
            etResultado.setText("")
            return
        }

        val valorEntrada = textoEntrada.toDoubleOrNull() ?: return
        val unidadOrigen = spinnerOrigen.selectedItem.toString()
        val unidadDestino = spinnerDestino.selectedItem.toString()

        val resultado = when {
            unidadOrigen == "Celsius (°C)" && unidadDestino == "Fahrenheit (°F)" -> (valorEntrada * 9/5) + 32
            unidadOrigen == "Fahrenheit (°F)" && unidadDestino == "Celsius (°C)" -> (valorEntrada - 32) * 5/9
            unidadOrigen == "Celsius (°C)" && unidadDestino == "Kelvin (K)" -> valorEntrada + 273.15
            unidadOrigen == "Kelvin (K)" && unidadDestino == "Celsius (°C)" -> valorEntrada - 273.15
            unidadOrigen == "Fahrenheit (°F)" && unidadDestino == "Kelvin (K)" -> (valorEntrada - 32) * 5/9 + 273.15
            unidadOrigen == "Kelvin (K)" && unidadDestino == "Fahrenheit (°F)" -> (valorEntrada - 273.15) * 9/5 + 32
            else -> valorEntrada
        }

        etResultado.setText(String.format("%.2f", resultado))
    }
}