package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //ViewBinding!!!!!!!

    private lateinit var binding: ActivityMainBinding // Instanciando e Aguardando a Invocação.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater) // root = layoutInflater
        setContentView(binding.root) // Acessando o Binding para, também, acessar os componentes.

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        binding.buttonCalculate.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.button_calculate -> calculate()
        }
    }

    private fun isValid(): Boolean {

        return (
                binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                &&binding.editAuto.text.toString() != ""
                && binding.editAuto.text.toString().toFloat() != 0f
        )
    }

    private fun calculate() {

        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAuto.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy
            val totalValueStr = "R$ ${"%.2f".format(totalValue)}"

            binding.textTotalValue.text = totalValueStr

        } else {
            Toast.makeText(this, R.string.message, Toast.LENGTH_SHORT).show()
        }



    }

}
