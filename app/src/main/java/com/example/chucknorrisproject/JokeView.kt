package com.example.chucknorrisproject

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView


class JokeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): ConstraintLayout(context, attrs, defStyleAttr){

    init { LayoutInflater.from(context).inflate(R.layout.joke_layout, this, true) }

    data class Model(val position: Int,
                     val joke : List<Joke> = JokeAdapter(onBottomReach = { }).jokes,
                     val jokeText : String = joke[position].value,
                     val jokeId: String = joke[position].id,
                     val favoriteJoke : Boolean = false,
                     val sharedJoke : Boolean = false
    )

    fun setupView(model: Model) {
        val jokeValue = model.jokeText
        val textview: TextView = findViewById<TextView>(R.id.textview_id)
        textview.text = jokeValue
        if (model.favoriteJoke) {
            val favoriteImage = findViewById<ImageView>(R.id.star_image)
            favoriteImage.setImageResource(R.drawable.ic_baseline_star_rate_24)
        }
    }

}