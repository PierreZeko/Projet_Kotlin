package com.example.chucknorrisproject

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
        val jokeStarButton: Button = view.findViewById<Button>(R.id.star_button)
        val favoriteImage: ImageView = view.findViewById<ImageView>(R.id.star_image)
        val jokeShareButton: Button = view.findViewById<Button>(R.id.share_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        // val view: ConstraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent, false) as ConstraintLayout
        val jokeView: JokeView = JokeView(parent.context, attrs = null, defStyleAttr = 0)
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
        holder.jokeStarButton.setOnClickListener {
            val newModel = JokeView.Model(
                position = model.position,
                model.joke,
                model.jokeText,
                model.jokeId,
                model.jokeUrl,
                favoriteJoke = !model.favoriteJoke,
                model.sharedJoke
            )
            if (newModel.favoriteJoke) {
                Log.d("save button clicked", newModel.jokeId)
                holder.view.setupView(newModel)
                holder.favoriteImage.setImageResource(R.drawable.ic_baseline_star_rate_24)
            }
            else {
                Log.d("save button clicked", newModel.jokeId)
                holder.view.setupView(newModel)
                holder.favoriteImage.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }

        holder.jokeShareButton.setOnClickListener {
            val newModel = JokeView.Model(
                position = model.position,
                model.joke,
                model.jokeText,
                model.jokeId,
                model.jokeUrl,
                model.favoriteJoke,
                sharedJoke = true
            )
            Log.d("share button clicked", newModel.jokeId)
            holder.view.setupView(newModel)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra("Share this", newModel.jokeUrl)
            val intentChooser = Intent.createChooser(intent, "Share with:")
        }
    }

    override fun getItemCount(): Int = jokes.size

}