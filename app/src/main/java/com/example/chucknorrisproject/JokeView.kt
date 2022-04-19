package com.example.chucknorrisproject

import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class JokeView() @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): ConstraintLayout(){

    val view: ConstraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent, false) as ConstraintLayout

    inner data class Model(){

    }

    fun setupView(model: Model){

    }

}