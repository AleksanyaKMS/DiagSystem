package com.example.diagsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ModelsAdapter(private var models: List<Model>, context: Context) :
    RecyclerView.Adapter<ModelsAdapter.ModelViewHolder>() {

    val db = DbHelper(context, null)

    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val markTextView: TextView = itemView.findViewById(R.id.mark)
        val paramsTextView: TextView = itemView.findViewById(R.id.params)
        val delBtn: ImageView = itemView.findViewById(R.id.delBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.model_item, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val model = models[position]
        val str: String = "Объем двигателя: ${model.engine};\nОбъем топливного бака: ${model.fuel};\nМасса: ${model.mass};\nМаксимальное значение тахометра: ${model.taho}"
        holder.markTextView.text = model.mark
        holder.paramsTextView.text = str

        holder.delBtn.setOnClickListener{
            db.deleteModel(model.mark)
            refreshData(db.getAllModels())
            Toast.makeText(holder.itemView.context, "Автомобиль удален", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newModels: List<Model>){
        models = newModels
        notifyDataSetChanged()
    }
}