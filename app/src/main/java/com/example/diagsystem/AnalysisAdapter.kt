package com.example.diagsystem

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AnalysisAdapter(private var errors: List<Errors>, context: Context) :
    RecyclerView.Adapter<AnalysisAdapter.AnalysisViewHolder>() {

    val db = DbHelper(context, null)

    class AnalysisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val markTextView: TextView = itemView.findViewById(R.id.mark)
        val params1TextView: TextView = itemView.findViewById(R.id.params1)
        val params2TextView: TextView = itemView.findViewById(R.id.params2)
        val params3TextView: TextView = itemView.findViewById(R.id.params3)
        val params4TextView: TextView = itemView.findViewById(R.id.params4)
        val params5TextView: TextView = itemView.findViewById(R.id.params5)
        val params6TextView: TextView = itemView.findViewById(R.id.params6)
        val delBtn: ImageView = itemView.findViewById(R.id.delBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.analysis_item, parent, false)
        return AnalysisViewHolder(view)
    }

    override fun getItemCount(): Int = errors.size

    override fun onBindViewHolder(holder: AnalysisViewHolder, position: Int) {
        val errors = errors[position]
        val str1: String ="Крат. топливная коррекция: ${errors.shortCor}%"
        val str2: String ="Долгов. топливная коррекция: ${errors.longCor}%"
        val str3: String ="Расход топлива: ${errors.expenses}"
        val str4: String ="Расстояние: ${errors.distance}км"
        val str5: String ="Средняя скорость: ${errors.speed}км/ч"
        val str6: String ="Дата: ${errors.date}"
        holder.markTextView.text = errors.mark
        holder.params1TextView.text = str1
        holder.params2TextView.text = str2
        holder.params3TextView.text = str3
        holder.params4TextView.text = str4
        holder.params5TextView.text = str5
        holder.params6TextView.text = str6

        if(errors.shortCor > 5) {
            holder.params1TextView.setBackgroundColor(Color.RED)
            if((1..2).random() == 2) holder.params1TextView.text = str1 + " Ошибка: P0172 (повышена)"
            else holder.params1TextView.text = str1 + " Ошибка: P0171 (понижена)"
        }
        if(errors.longCor > 5) {
            holder.params2TextView.setBackgroundColor(Color.RED)
            if((1..2).random() == 2) holder.params2TextView.text = str2 + " Ошибка: P0172 (повышена)"
            else holder.params2TextView.text = str2 + " Ошибка: P0171 (понижена)"
        }
        val mdl = db.getEntModel(db.getModelIdName(errors.mark))
        val engine = mdl.engine.replace(".", "").toInt()
        if(engine in 10..15 && errors.expenses !in 8..12) {
            holder.params3TextView.setBackgroundColor(Color.RED)
            holder.params3TextView.text = str3 + " Ошибка: P0038 (повышен)"
        }
        if(engine in 16..24 && errors.expenses !in 10..14) {
            holder.params3TextView.setBackgroundColor(Color.RED)
            holder.params3TextView.text = str3 + " Ошибка: P0038 (повышен)"
        }
        if(engine >= 25 && errors.expenses !in 15..20) {
            holder.params3TextView.setBackgroundColor(Color.RED)
            holder.params3TextView.text = str3 + " Ошибка: P0038 (повышен)"
        }

        holder.delBtn.setOnClickListener{
            db.deleteAnalysis(errors.id)
            db.deleteMeas(errors.idM)
            refreshData(db.getAllAnalysis(errors.user))
            Toast.makeText(holder.itemView.context, "Запись об ошибке удалена", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newAnalysis: List<Errors>){
        errors = newAnalysis
        notifyDataSetChanged()
    }
}