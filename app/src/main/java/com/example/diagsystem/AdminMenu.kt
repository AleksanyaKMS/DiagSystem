package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        val button: ImageButton = findViewById(R.id.backButton)
        val intentBack = Intent(this, MainActivity::class.java)
        val intentNew = Intent(this, AddMark::class.java)
        val intentList = Intent(this, ModelList::class.java)
        val addButton: Button = findViewById(R.id.addBtn)
        val listButton: Button = findViewById(R.id.listBtn)

        button.setOnClickListener {
            startActivity(intentBack)
        }

        addButton.setOnClickListener {
            startActivity(intentNew)
        }

        listButton.setOnClickListener {
            startActivity(intentList)
        }
    }
}