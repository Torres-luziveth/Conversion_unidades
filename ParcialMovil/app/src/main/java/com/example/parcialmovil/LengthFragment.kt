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

class LengthFragment : Fragment() {

    private lateinit var etEntrada: TextInputEditText
    private lateinit var spinnerOrigen: Spinner
    private lateinit var spinnerDestino: Spinner
    private lateinit var etResultado: TextInputEditText

    private val unidadesLongitud = arrayOf(
        "Kilómetro (km)", "Metro (m)", "Centímetro (cm)", "Milímetro (mm)"
    )

    private val factoresConversion = mapOf(
        "Kilómetro (km)" to 1.0,
        "Metro (m)" to 0.001,
        "Centímetro (cm)" to 0.00001,
        "Milímetro (mm)" to 0.000001
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_length, container, false)

        etEntrada = vista.findViewById(R.id.etInput)
        spinnerOrigen = vista.findViewById(R.id.spinnerFrom)
        spinnerDestino = vista.findViewById(R.id.spinnerTo)
        etResultado = vista.findViewById(R.id.etResult)

        configurarSpinners()
        configurarListenerEntrada()

        return vista
    }

    private fun configurarSpinners() {
        val adaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, unidadesLongitud)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerOrigen.adapter = adaptador
        spinnerDestino.adapter = adaptador
    }

    private fun configurarListenerEntrada() {
        etEntrada.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                convertirLongitud()
            }
        })
    }

    private fun convertirLongitud() {
        val textoEntrada = etEntrada.text.toString()
        if (textoEntrada.isEmpty()) {
            etResultado.setText("")
            return
        }

        val valorEntrada = textoEntrada.toDoubleOrNull() ?: return
        val unidadOrigen = spinnerOrigen.selectedItem.toString()
        val unidadDestino = spinnerDestino.selectedItem.toString()

        val factorOrigen = factoresConversion[unidadOrigen] ?: 1.0
        val factorDestino = factoresConversion[unidadDestino] ?: 1.0

        val resultado = valorEntrada * (factorOrigen / factorDestino)
        etResultado.setText(String.format("%.4f", resultado))
    }
}