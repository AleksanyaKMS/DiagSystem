package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class AddMark : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mark)

        val engine: EditText = findViewById(R.id.engine)
        val mark: EditText = findViewById(R.id.mark)
        val fuel: EditText = findViewById(R.id.fuel)
        val mass: EditText = findViewById(R.id.mass)
        val taho: EditText = findViewById(R.id.taho)
        val per1: EditText = findViewById(R.id.per1)
        val per2: EditText = findViewById(R.id.per2)
        val per3: EditText = findViewById(R.id.per3)
        val per4: EditText = findViewById(R.id.per4)
        val per5: EditText = findViewById(R.id.per5)
        val per6: EditText = findViewById(R.id.per6)
        val button: Button = findViewById(R.id.button)
        val backBtn: ImageButton = findViewById(R.id.imageButton3)
        val intent = Intent(this, AdminMenu::class.java)

        button.setOnClickListener {
            val engineDB: String = engine.text.toString().trim()
            val markDB: String = mark.text.toString().trim()
            val fuelDB: String = fuel.text.toString().trim()
            val massDB: String = mass.text.toString().trim()
            val tahoDB: String = taho.text.toString().trim()
            val per1DB: String = per1.text.toString().trim()
            val per2DB: String = per2.text.toString().trim()
            val per3DB: String = per3.text.toString().trim()
            val per4DB: String = per4.text.toString().trim()
            val per5DB: String = per5.text.toString().trim()
            val per6DB: String = per6.text.toString().trim()
            val db = DbHelper(this, null)

            if(markDB == "" || engineDB == "" || fuelDB == "" || massDB== "" || tahoDB == "" || per1DB == "" ||  per2DB == "" || per3DB == "" || per4DB == "" || per5DB == "" || per6DB == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            else{
                if(!engineDB.matches("\\d+(\\.\\d+)?".toRegex())) Toast.makeText(this, "Поле 'Объем двигателя' должно быть числом формата х.х", Toast.LENGTH_SHORT).show()
                else {
                    if (!fuelDB.matches("\\d+".toRegex()) || !massDB.matches("\\d+".toRegex()) || !tahoDB.matches(
                            "\\d+".toRegex()
                        )
                    ) Toast.makeText(
                        this,
                        "Поля 'Объем бака', 'Масса', 'Знач. тахометра' должны быть целыми числами",
                        Toast.LENGTH_SHORT
                    ).show()
                    else {
                        if (db.getModel(markDB)) {
                            Toast.makeText(this, "Такая марка уже добавлена", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val mId: Int = db.getModelId() + 1
                            val model = Model(
                                mId,
                                engineDB,
                                fuelDB.toInt(),
                                massDB.toInt(),
                                tahoDB.toInt(),
                                markDB,
                                per1DB,
                                per2DB,
                                per3DB,
                                per4DB,
                                per5DB,
                                per6DB
                            )
                            db.addModel(model)
//                    mark.setText(db.getModelId())
                            Toast.makeText(this, "Марка автомобиля добавлена", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

        backBtn.setOnClickListener {
            startActivity(intent)
        }
    }
}