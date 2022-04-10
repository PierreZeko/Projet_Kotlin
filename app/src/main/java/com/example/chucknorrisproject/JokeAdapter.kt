package com.example.chucknorrisproject

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chucknorrisproject.R
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>(/**onBottomReached**/){

    var jokes = listOf<Joke>()

    fun updateList(it: Joke) {
        jokes = jokes + it
        notifyDataSetChanged()
    }

    /**fun onBottomReached() {

    }**/

    inner class JokeViewHolder(val view: ConstraintLayout) : RecyclerView.ViewHolder(view){
        val textview = view.findViewById<TextView>(R.id.textview_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view: ConstraintLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent, false) as ConstraintLayout
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val currentJoke: Joke = jokes[position]
        holder.textview.text = currentJoke.value
    }

    override fun getItemCount(): Int = jokes.size

}