package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class Graph_Choose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_choose)

        val idOfUser0 = intent.getIntExtra("keyLoginGr", 0)
        val mdlId = intent.getIntExtra("keyMarkGr", 1)
        val db = DbHelper(this, null)
        val userObj = db.getUserObj(idOfUser0)
        val idOfUser = userObj.userId
        val mdl = db.getEntModel(mdlId)
        val autoComp: AutoCompleteTextView = findViewById(R.id.auto_complete_text)
        val graphs = resources.getStringArray(R.array.pokaz)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, graphs)
        val autoComp2: AutoCompleteTextView = findViewById(R.id.auto_complete_text2)
        val pokaz = resources.getStringArray(R.array.graph)
        val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_item, pokaz)
        val button2: Button = findViewById(R.id.button2)

        autoComp.setAdapter(arrayAdapter)
        autoComp2.setAdapter(arrayAdapter2)

        val button: ImageButton = findViewById(R.id.imageButton2)

        button.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent.apply{
                putExtra("keyLogin", idOfUser)
                putExtra("keyMark", mdl.modelId)
            })
        }

        button2.setOnClickListener {
            val graphic: AutoCompleteTextView = findViewById(R.id.auto_complete_text2)
            val pokazat: AutoCompleteTextView = findViewById(R.id.auto_complete_text)
            if(graphic.text.toString() == "Линейный" && pokazat.text.toString() != "") {
                var pokazFlag: Int = 0
                if(pokazat.text.toString() == "Крат. коррекция") pokazFlag = 1
                if(pokazat.text.toString() == "Долг. коррекция") pokazFlag = 2
                if(pokazat.text.toString() == "Расход топлива") pokazFlag = 3
                if(pokazat.text.toString() == "Расстояние") pokazFlag = 4
                if(pokazat.text.toString() == "Скорость") pokazFlag = 5
                val intent2 = Intent(this, LineChart::class.java)
                startActivity(intent2.apply {
                    putExtra("keyLoginLi", idOfUser)
                    putExtra("keyMarkLi", mdl.modelId)
                    putExtra("flag", pokazFlag)
                })
            }
            else {
                if (graphic.text.toString() == "Диаграмма" && pokazat.text.toString() != "") {
                    var pokazFlag: Int = 0
                    if (pokazat.text.toString() == "Крат. коррекция") pokazFlag = 1
                    if (pokazat.text.toString() == "Долг. коррекция") pokazFlag = 2
                    if (pokazat.text.toString() == "Расход топлива") pokazFlag = 3
                    if (pokazat.text.toString() == "Расстояние") pokazFlag = 4
                    if (pokazat.text.toString() == "Скорость") pokazFlag = 5
                    val intent3 = Intent(this, BarChart::class.java)
                    startActivity(intent3.apply {
                        putExtra("keyLoginLi", idOfUser)
                        putExtra("keyMarkLi", mdl.modelId)
                        putExtra("flag", pokazFlag)
                    })
                }
                else{
                    Toast.makeText(this, "Графическое отображение или показатель не выбраны", Toast.LENGTH_SHORT)
                }
            }
        }
    }
}