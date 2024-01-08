package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ModelList : AppCompatActivity() {

    private lateinit var modelsAdapter: ModelsAdapter
    var db = DbHelper(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_list)

        modelsAdapter = ModelsAdapter(db.getAllModels(), this)
        val recycler: RecyclerView = findViewById(R.id.recycler)
        val intent = Intent(this, AdminMenu::class.java)
        val button: ImageButton = findViewById(R.id.imageButton3)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = modelsAdapter

        button.setOnClickListener {
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        modelsAdapter.refreshData(db.getAllModels())
    }
}