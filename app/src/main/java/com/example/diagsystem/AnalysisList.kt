package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnalysisList : AppCompatActivity() {
    private lateinit var analysisAdapter: AnalysisAdapter
    var db = DbHelper(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_list)

        val idOfUser0 = intent.getIntExtra("keyLoginErr", 0)
        val mdlId = intent.getIntExtra("keyMarkErr", 1)
        val userObj = db.getUserObj(idOfUser0)
        val idOfUser = userObj.userId
        analysisAdapter = AnalysisAdapter(db.getAllAnalysis(idOfUser), this)
        val recycler: RecyclerView = findViewById(R.id.recycler)
        val intent = Intent(this, MainMenu::class.java)
        val button: ImageButton = findViewById(R.id.imageButton3)

        var randShCor = (0..100).random()
        var randLoCor = (0..100).random()
        var randExpenses = (0..100).random()
        var randDistance = (0..100).random()
        var randSpeed: Int = 60
        val mdl = db.getEntModel(mdlId)
        val engine = mdl.engine.replace(".", "").toInt()
        val textView: TextView = findViewById(R.id.textView4)
//        textView.text = engine.toString()

        val measId = db.getMeasId() + 1

        if(randShCor > 30) randShCor = (6..10).random()
        else randShCor = (1..5).random()
        if(randLoCor > 30) randLoCor = (6..10).random()
        else randLoCor = (1..5).random()
        if(randExpenses > 30){
            if(engine in 10..15) randExpenses = (8..12).random()
            if(engine in 16..24) randExpenses = (10..14).random()
            if(engine >= 25) randExpenses = (15..20).random()
        }
        else{
            if(engine in 10..15) randExpenses = (6..8).random()
            if(engine in 16..24) randExpenses = (7..10).random()
            if(engine >= 25) randExpenses = (9..15).random()
        }
        if(randDistance > 30){
            val timeHour = (1..8).random()
            val mark = mdl.mark
            val taho = (1..(mdl.taho/1000)).random()
            val per = (1..6).random()
            val perFor = db.getModelPer(mark, per).replace(".","").toInt()
            randSpeed = ((19900 * 60 * taho) / (38 * perFor)).toInt()
            randDistance = (randSpeed * timeHour).toInt()
        }

        val meas = Measurement(measId, randShCor, randLoCor, randExpenses, randDistance, randSpeed)
        val idAn = db.getAnId() + 1
//        val idUser = db.getUserId() + 1
//        val idModel = db. getModelId() + 1
        val day = (1..30).random()
        val month = (1..12).random()
        val year = (2021..2023).random()
        val strAn = "$day.$month.$year"
        val an = Analysis(idAn, idOfUser, mdl.modelId, measId, strAn)

        db.addMeas(meas)
        db.addAn(an)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = analysisAdapter

        button.setOnClickListener {
            startActivity(intent.apply{
                putExtra("keyLogin", idOfUser)
                putExtra("keyMark", mdl.modelId)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        val idOfUser = intent.getIntExtra("keyLoginErr", 0)
        analysisAdapter.refreshData(db.getAllAnalysis(idOfUser))
    }
}