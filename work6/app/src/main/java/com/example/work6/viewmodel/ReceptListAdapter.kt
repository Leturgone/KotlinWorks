package com.example.work6.viewmodel;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.work6.R
import com.example.work6.recept.Recept

class ReceptListAdapter(private var recepts: List<Recept>)
    : RecyclerView.Adapter<ReceptListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.itemTextView)
        val textView2: TextView = view.findViewById(R.id.textView)
        val textView3: TextView = view.findViewById(R.id.textView2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recepts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recept = recepts[position]
        holder.textView.text = recept.name
        holder.textView2.text = recept.id.toString()
        holder.textView3.text = recept.difficulty

    }

    fun updateData(newItems: List<Recept>) {
        recepts = newItems
        notifyDataSetChanged() // уведомление об изменениях
    }

}