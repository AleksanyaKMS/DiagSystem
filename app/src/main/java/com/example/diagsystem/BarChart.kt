package com.example.diagsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diagsystem.databinding.ActivityBarChartBinding
import com.example.diagsystem.databinding.ActivityLineChartBinding
import kotlin.random.Random

class BarChart : AppCompatActivity() {
    private var _binding: ActivityBarChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBarChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idOfUser0 = intent.getIntExtra("keyLoginLi", 0)
        val mdlId = intent.getIntExtra("keyMarkLi", 1)
        val flag = intent.getIntExtra("flag", 1)
        val db = DbHelper(this, null)
        val userObj = db.getUserObj(idOfUser0)
        val idOfUser = userObj.userId
        val mdl = db.getEntModel(mdlId)
        var lineSet = listOf(
            "1 час" to 1F,
            "2 час" to 1F,
            "3 час" to 1F,
            "4 час" to 1F,
            "5 час" to 1F,
            "6 час" to 1F,
        )

        if(flag == 1){
            val first = Random.nextFloat() + 3
            val n1 = getRandSh(0)
            val n2 = getRandSh(n1)
            val n3 = getRandSh(n2)
            val n4 = getRandSh(n3)
            val n5 = getRandSh(n4)
            val n6 = getRandSh(n5)
            val n7 = getRandSh(n6)
            val n8 = getRandSh(n7)
            lineSet = listOf(
                "1" to (first + n1),
                "2" to (first + n2),
                "3" to (first + n3),
                "4" to (first + n4),
                "5" to (first + n5),
                "6" to (first + n6),
                "7" to (first + n7),
                "8" to (first + n8),
            )
        }
        if(flag == 2){
            val first = Random.nextFloat() + 3
            val n1 = getRand(0)
            val n2 = getRand(n1)
            val n3 = getRand(n2)
            val n4 = getRand(n3)
            val n5 = getRand(n4)
            val n6 = getRand(n5)
            val n7 = getRand(n6)
            val n8 = getRand(n7)
            lineSet = listOf(
                "1" to (first + n1),
                "2" to (first + n2),
                "3" to (first + n3),
                "4" to (first + n4),
                "5" to (first + n5),
                "6" to (first + n6),
                "7" to (first + n7),
                "8" to (first + n8),
            )
        }
        if(flag == 3){
            lineSet = listOf(
                "1ч" to (Random.nextFloat() + (7..15).random()),
                "2ч" to (Random.nextFloat() + (7..15).random()),
                "3ч" to (Random.nextFloat() + (7..15).random()),
                "4ч" to (Random.nextFloat() + (7..15).random()),
                "5ч" to (Random.nextFloat() + (7..15).random()),
                "6ч" to (Random.nextFloat() + (7..15).random()),
                "7ч" to (Random.nextFloat() + (7..15).random()),
                "8ч" to (Random.nextFloat() + (7..15).random()),
            )
        }
        if(flag == 4){
            lineSet = listOf(
                "1ч" to (Random.nextFloat() + (1..50).random()),
                "2ч" to (Random.nextFloat() + (1..50).random()),
                "3ч" to (Random.nextFloat() + (1..50).random()),
                "4ч" to (Random.nextFloat() + (1..50).random()),
                "5ч" to (Random.nextFloat() + (1..50).random()),
                "6ч" to (Random.nextFloat() + (1..50).random()),
                "7ч" to (Random.nextFloat() + (1..50).random()),
                "8ч" to (Random.nextFloat() + (1..50).random()),
            )
        }
        if(flag == 5){
            lineSet = listOf(
                "1ч" to (Random.nextFloat() + (60..150).random()),
                "2ч" to (Random.nextFloat() + (60..150).random()),
                "3ч" to (Random.nextFloat() + (60..150).random()),
                "4ч" to (Random.nextFloat() + (60..150).random()),
                "5ч" to (Random.nextFloat() + (60..150).random()),
                "6ч" to (Random.nextFloat() + (60..150).random()),
                "7ч" to (Random.nextFloat() + (60..150).random()),
                "8ч" to (Random.nextFloat() + (60..150).random()),
            )
        }

        binding.apply {
            barChart.animation.duration = animationDuration
            barChart.animate(lineSet)
            barChart.onDataPointClickListener = {index, _, _ ->
                textViewOut.text = lineSet.toList()[index].second.toString()
            }
        }

        binding.imageButton2.setOnClickListener {
            val intentBack = Intent(this, Graph_Choose::class.java)
            startActivity(intentBack.apply{
                putExtra("keyLoginGr", idOfUser)
                putExtra("keyMarkGr", mdl.modelId)
            })
        }
    }

    companion object{
        private const val animationDuration = 1000L
    }

    fun getRand(first: Int) : Int{
        if((1..2).random() > 1){
            return first + 3
        }
        else return first - 3
    }

    fun getRandSh(first: Int) : Int{
        if(first < 0){
            return first + 1
        }
        else return first - 1
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}