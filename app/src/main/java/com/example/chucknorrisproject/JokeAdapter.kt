package com.example.chucknorrisproject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter(val onBottomReach: () -> Unit) : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>(){

    var jokes = listOf<Joke>()

    fun updateList(it: Joke) {
        jokes = jokes + it
        notifyDataSetChanged()
    }

    inner class JokeViewHolder(val view: JokeView) : RecyclerView.ViewHolder(view){
        val textview: TextView = view.findViewById<TextView>(R.id.textview_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val jokeView: JokeView = JokeView(parent.context, attrs = null, defStyleAttr = 0)
        // val view: ConstraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent, false) as ConstraintLayout
        return JokeViewHolder(jokeView)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        //val currentJoke: Joke = jokes[position]
        //holder.textview.text = currentJoke.value

        val model: JokeView.Model = JokeView.Model(position,
            jokes,
            jokes[position].value,
            jokes[position].id,
            favoriteJoke = false,
            sharedJoke = false)

        holder.view.setupView(model)
        Log.d("onBind", "position=${position}, jokeSize=${jokes.lastIndex}")
        if (position == jokes.lastIndex && position >= 9) {
            onBottomReach()
        }
    }

    override fun getItemCount(): Int = jokes.size

}