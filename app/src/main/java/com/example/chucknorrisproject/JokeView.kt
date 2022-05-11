package com.example.chucknorrisproject

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView


class JokeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): ConstraintLayout(context, attrs, defStyleAttr){

    init { LayoutInflater.from(context).inflate(R.layout.joke_layout, this, true) }

    data class Model(val model: Model){
        val jokeText : String = JokeAdapter(onBottomReach = { Log.d("jokeText", "text of the joke")}).jokes[0].value
        val favoriteJoke : Boolean = false
        val sharedJoke : Boolean = false
    }

    fun setupView(model: Model){

    }

}