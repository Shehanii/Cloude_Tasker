package com.aula.sqlitetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    // Database
    val databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        val edit = intent.getBooleanExtra("edit", false)
        val position = intent.getIntExtra("position", 0)
        if(edit){
            val pessoa = databaseHandler.getPessoa(position)
            etNome.setText(pessoa.nome)
            btnInsertNome.setText("Edit")
        }
        btnInsertNome.setOnClickListener {
            if(etNome.text.toString() == ""){
                Toast.makeText(this,"Empty.",Toast.LENGTH_SHORT).show()
            }
            else {
                if(edit){
                    val pessoa = Detail(position, etNome.text.toString())
                    databaseHandler.updatePessoa(pessoa)
                    finish()
                }
                else {
                    val Detail = Detail(0, etNome.text.toString())
                    databaseHandler.addPessoa(Detail)
                    finish()
                }
            }
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }
}