package com.example.chucknorrisproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView


class JokeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): ConstraintLayout(context, attrs, defStyleAttr){

    init { LayoutInflater.from(context).inflate(R.layout.joke_layout, this, true) }
    val jokeStarButton: ImageButton = findViewById<ImageButton>(R.id.star_button)
    val jokeShareButton: ImageButton = findViewById<ImageButton>(R.id.share_button)

    data class Model(val position: Int,
                     val joke : List<Joke> = JokeAdapter(onBottomReach = { }).jokes,
                     val jokeText : String = joke[position].value,
                     val jokeId: String = joke[position].id,
                     val jokeUrl: String = joke[position].url,
                     var favoriteJoke : Boolean = false,
                     var sharedJoke : Boolean = false
    )

    fun setupView(model: Model, onStarClicked: (model: Model) -> Unit, onShareClicked: (model: Model) -> Unit) {
        jokeStarButton.setOnClickListener {
            Log.d("starButton", "on update le model")
            Log.d("favoriteJoke avant", "${model.favoriteJoke}")
            model.favoriteJoke = !model.favoriteJoke

            Log.d("favoriteJoke après", "${model.favoriteJoke}")

            if (model.favoriteJoke) {
                Log.d("save button clicked 1", model.jokeId)
                jokeStarButton.setBackgroundResource(R.drawable.ic_baseline_star_rate_24)
            }
            else {
                Log.d("save button clicked 2", model.jokeId)
                jokeStarButton.setBackgroundResource(R.drawable.ic_baseline_star_border_24)
            }
        }
        jokeShareButton.setOnClickListener {
            Log.d("shareButton", "on update le model 2")
            model.sharedJoke = !model.sharedJoke
            Log.d("shareJoke favorite", "${model.favoriteJoke}")
            Log.d("share button clicked", model.jokeId)
            val intent :Intent = Intent().apply{
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, model.jokeUrl)
            }
            startActivity(context, intent, Bundle())
            model.sharedJoke = !model.sharedJoke
        }
    }
}