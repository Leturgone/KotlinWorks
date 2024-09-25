package com.example.work5.viewmodel;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.work5.R
import com.example.work5.model.dog.Dog

class DogListAdapter(private val dogs: MutableList<Dog>)
    : RecyclerView.Adapter<DogListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.itemTextView)
        val imageView: ImageView = view.findViewById(R.id.ittemImageView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogs[position]
        holder.textView.text = dog.id.toString()
        Glide.with(holder.itemView.context).load(dog.image).into(holder.imageView)

    }

}