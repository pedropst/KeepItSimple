package com.pedropst.KeepItSimple

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    val listOfAndroidDefaultApps = listOf<String>("discar", "mensagens")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val emergencyNumberField = findViewById<EditText>(R.id.editTextTextPersonName)
        val applyButton = findViewById<Button>(R.id.apply)

        val spinner = findViewById<Spinner>(R.id.spinnerBtnCount)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.btnCount,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter }

        loadData(emergencyNumberField, spinner)

        applyButton.setOnClickListener{
            saveData(emergencyNumberField, spinner)

            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }
    }

    private fun saveData(emergencyNumberField : TextView, btnCount : Spinner){
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val editor = sharedPref.edit();
        editor.apply{
            putString("emergNum", emergencyNumberField.text.toString())
            putString("btnCount", btnCount.selectedItem.toString())
            putInt("btnCountPos", btnCount.selectedItemPosition)
        }.apply()

        Toast.makeText(this,"Dados foram salvos com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun loadData(emergencyNumberField : TextView, btnCount : Spinner)
    {
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        emergencyNumberField.text = sharedPref.getString("emergNum", null)
        btnCount.setSelection(sharedPref.getInt("btnCountPos", 0))
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}