package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val autoComp: AutoCompleteTextView = findViewById(R.id.auto_complete_text)
//        val models = resources.getStringArray(R.array.models)
//        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, models)

//        autoComp.setAdapter(arrayAdapter)

        val userLogin: EditText = findViewById(R.id.login)
        val mark: EditText = findViewById(R.id.new_mark)
        val engine: EditText = findViewById(R.id.engine)
        val fuel: EditText = findViewById(R.id.fuel)
        val mass: EditText = findViewById(R.id.mass)
        val maxTaho: EditText = findViewById(R.id.taho)
        val confButton: Button = findViewById(R.id.button)

        confButton.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val markDB = mark.text.toString().trim()
            val engineDB = engine.text.toString().trim()
            val fuelDB = fuel.text.toString().trim()
            val massDB = mass.text.toString().trim()
            val maxTahoDB = maxTaho.text.toString().trim()

            if(login == "" || markDB == "" || engineDB == "" || fuelDB == "" || massDB == "" || maxTahoDB == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            else {
                if(!engineDB.matches("\\d+(\\.\\d+)?".toRegex())) Toast.makeText(this, "Поле 'Объем двигателя' должно быть числом", Toast.LENGTH_SHORT).show()
                else {
                    if(!fuelDB.matches("\\d+".toRegex()) || !massDB.matches("\\d+".toRegex()) || !maxTahoDB.matches("\\d+".toRegex())) Toast.makeText(this, "Поля 'Объем бака', 'Масса', 'Знач. тахометра' должны быть числами < 10000", Toast.LENGTH_SHORT).show()
                    else {
                        val db = DbHelper(this, null)
                        val userFlag = db.getUser(login)
                        val modelFlag = db.getModelAll(markDB, engineDB, fuelDB, massDB, maxTahoDB)
                        val intent1 = Intent(this, MainMenu::class.java)
                        val intent2 = Intent(this, AdminMenu::class.java)

                        if ((userFlag && modelFlag) || (userFlag && (massDB == "0000"))) {
                            if (login == "admin" && massDB == "0000") {
                                Toast.makeText(this, "Вы вошли как $login", Toast.LENGTH_SHORT)
                                    .show()
                                startActivity(intent2)
                            } else {
                                val i = db.getUserIdName(login)
                                val modelId = db.getModelIdName(markDB)
                                Toast.makeText(this, "Вы вошли как $login", Toast.LENGTH_SHORT)
                                    .show()
                                startActivity(intent1.apply {
                                    putExtra("keyLogin", i)
                                    putExtra("keyMark", modelId)
                                })
                            }
                        } else {
                            if (!modelFlag) {
                                Toast.makeText(
                                    this,
                                    "Такого автомобиля нет в базе данных",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val i = db.getUserId() + 1
                                val modelId = db.getModelIdName(markDB)
                                val user = User(i, login)
                                db.addUser(user)
                                Toast.makeText(
                                    this,
                                    "Пользователь $login добавлен",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                startActivity(intent1.apply {
                                    putExtra("keyLogin", i)
                                    putExtra("keyMark", modelId)
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}