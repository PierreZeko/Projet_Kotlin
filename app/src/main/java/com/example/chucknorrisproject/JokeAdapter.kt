package com.example.chucknorrisproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chucknorrisproject.R
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>(){

    class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view){
       val joke_1 = view.findViewById<TextView>(R.id.joke_1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = 10

}