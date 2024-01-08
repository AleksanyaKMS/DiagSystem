package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val backButton: ImageButton = findViewById(R.id.imageButton2)
        val profileButton: ImageButton = findViewById(R.id.imageButton)
        val buttonErr: Button = findViewById(R.id.buttonErr)
        val buttonShow: Button = findViewById(R.id.buttonShow)
        val textView: TextView = findViewById(R.id.textView)
        val idUser = intent.getIntExtra("keyLogin", 0)
        val idModel = intent.getIntExtra("keyMark", 1)
//        textView.text = idModel.toString()

        buttonErr.setOnClickListener {
            val intentErr = Intent(this, AnalysisList::class.java)
            startActivity(intentErr.apply{
                putExtra("keyLoginErr", idUser)
                putExtra("keyMarkErr", idModel)
            })
        }

        buttonShow.setOnClickListener {
            val intentShow = Intent(this, Graph_Choose::class.java)
            startActivity(intentShow.apply{
                putExtra("keyLoginGr", idUser)
                putExtra("keyMarkGr", idModel)
            })
        }

        backButton.setOnClickListener{
            val intentBack = Intent(this, MainActivity::class.java)
            startActivity(intentBack)
        }

        profileButton.setOnClickListener{
            val intentProfile = Intent(this, Profile::class.java)
            startActivity(intentProfile.apply{
                putExtra("keyLoginPr", idUser)
                putExtra("keyMarkPr", idModel)
            })
        }
    }
}