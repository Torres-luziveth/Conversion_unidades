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

class MassFragment : Fragment() {

    private lateinit var etEntrada: TextInputEditText
    private lateinit var spinnerOrigen: Spinner
    private lateinit var spinnerDestino: Spinner
    private lateinit var etResultado: TextInputEditText

    private val unidadesMasa = arrayOf(
        "Kilogramo (kg)", "Gramo (g)", "Miligramo (mg)",
        "Libra (lb)"
    )

    private val factoresConversion = mapOf(
        "Kilogramo (kg)" to 1.0,
        "Gramo (g)" to 0.001,
        "Miligramo (mg)" to 0.000001,
        "Libra (lb)" to 0.453592
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_mass, container, false)

        etEntrada = vista.findViewById(R.id.etInput)
        spinnerOrigen = vista.findViewById(R.id.spinnerFrom)
        spinnerDestino = vista.findViewById(R.id.spinnerTo)
        etResultado = vista.findViewById(R.id.etResult)

        configurarSpinners()
        configurarListenerEntrada()

        return vista
    }

    private fun configurarSpinners() {
        val adaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, unidadesMasa)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerOrigen.adapter = adaptador
        spinnerDestino.adapter = adaptador
    }

    private fun configurarListenerEntrada() {
        etEntrada.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                convertirMasa()
            }
        })
    }

    private fun convertirMasa() {
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