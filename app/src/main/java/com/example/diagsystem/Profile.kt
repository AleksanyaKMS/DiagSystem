package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val str1 = intent.getIntExtra("keyLoginPr", 0)
        val str2 = intent.getIntExtra("keyMarkPr", 1)
        val textView: TextView = findViewById(R.id.textView2)
//        textView.text = str2.toString()

        val backButton: ImageButton = findViewById(R.id.backButton)
        val login: TextView = findViewById(R.id.textView14)
        val model: TextView = findViewById(R.id.textView13)
        val engine: TextView = findViewById(R.id.textView12)
        val fuel: TextView = findViewById(R.id.textView10)
        val mass: TextView = findViewById(R.id.textView9)
        val taho: TextView = findViewById(R.id.textView7)
        val db = DbHelper(this, null)

        val mdl = db.getEntModel(str2)

        login.text = db.getEntUser(str1)
        model.text = mdl.mark
        engine.text = mdl.engine
        fuel.text = mdl.fuel.toString()
        mass.text = mdl.mass.toString()
        taho.text = mdl.taho.toString()

        backButton.setOnClickListener{
            val intentBack = Intent(this, MainMenu::class.java)
            startActivity(intentBack.apply{
                putExtra("keyLogin", str1.toInt())
                putExtra("keyMark", str2.toInt())
            })
        }
    }
}