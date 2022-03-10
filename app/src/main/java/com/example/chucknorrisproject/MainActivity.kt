package com.example.chucknorrisproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var jokes_list = listOf<String>("Some people balance eggs on end every Spring and Fall Equinox, but Chuck Norris balances skulls.",
        "Chuck Norris once sawed a man in half..... With his beard.",
        "When Chuck Norris snaps his fingers in your face, you shit your pants.",
        "Chuck Norris's second child is Tim Tebow.",
        "There's more than one way to skin a cat. Chuck Norris knows 437 ways to skin a cat and uses a different one every morning.",
        "The closest Chuck Norris has come to getting his ass kicked was when he gave himself a dirty look in the mirror.",
        "Chuck Norris' eggs lay chickens.",
        "Once a cobra bit Chuck Norris, after 5 days of pain the cobra died.",
        "Chuck Norris can spam faster than anyone in the whole world. The speed is 0. that means infinity words per second.",
        "When Captain Phillips returned to sea.........he's bringing Chuck Norris this time!")

    Log.d("list", jokes_list.toString());




    }