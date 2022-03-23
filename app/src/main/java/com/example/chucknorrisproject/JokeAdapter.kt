package com.example.chucknorrisproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chucknorrisproject.R
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>(){

    var Jokes = listOf<String>("Some people balance eggs on end every Spring and Fall Equinox, but Chuck Norris balances skulls.",
        "Chuck Norris once sawed a man in half..... With his beard.",
        "When Chuck Norris snaps his fingers in your face, you shit your pants.",
        "Sasquatch brings Chuck Norris Jack Links beef jerky.",
        "There's more than one way to skin a cat. Chuck Norris knows 437 ways to skin a cat and uses a different one every morning.",
        "The closest Chuck Norris has come to getting his ass kicked was when he gave himself a dirty look in the mirror.",
        "Chuck Norris' eggs lay chickens.",
        "Once a cobra bit Chuck Norris, after 5 days of pain the cobra died.",
        "Chuck Norris can spam faster than anyone in the whole world. The speed is 0. that means infinity words per second.",
        "When Captain Phillips returned to sea.........he's bringing Chuck Norris this time!")


    class JokeViewHolder(val view: TextView) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val textview = TextView()
        return JokeViewHolder(textview)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val currentJoke = Jokes[position]
        JokeViewHolder.setText(currentJoke)
    }

    override fun getItemCount(): Int = Jokes.size

}