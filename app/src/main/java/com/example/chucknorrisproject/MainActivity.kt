package com.example.chucknorrisproject

import com.example.chucknorrisproject.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //callJoke()
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = adapter
        var button = findViewById<Button>(R.id.button_id)
        button.setOnClickListener{callJoke()}
    }

    override fun onDestroy() {
        super.onDestroy()
        println("info avant ${composite_disposable.size()}")
        composite_disposable.clear()
        println("info après ${composite_disposable.size()}")
    }

    fun callJoke() {
        joke_service.giveMeAJoke().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onError = { Log.d("error", "an error occured") },
            onSuccess = {
                Log.d("success", "The joke is ${it.value}")
                adapter.updateList(it)
            }
        ).also { composite_disposable.add(it) }
    }

    /*object Jokes {
        val jokes_list = listOf<String>("Some people balance eggs on end every Spring and Fall Equinox, but Chuck Norris balances skulls.",
            "Chuck Norris once sawed a man in half..... With his beard.",
            "When Chuck Norris snaps his fingers in your face, you shit your pants.",
            "Sasquatch brings Chuck Norris Jack Links beef jerky.",
            "There's more than one way to skin a cat. Chuck Norris knows 437 ways to skin a cat and uses a different one every morning.",
            "The closest Chuck Norris has come to getting his ass kicked was when he gave himself a dirty look in the mirror.",
            "Chuck Norris' eggs lay chickens.",
            "Once a cobra bit Chuck Norris, after 5 days of pain the cobra died.",
            "Chuck Norris can spam faster than anyone in the whole world. The speed is 0. that means infinity words per second.",
            "When Captain Phillips returned to sea.........he's bringing Chuck Norris this time!")
    }*/

    val composite_disposable = CompositeDisposable()

    val joke_service: JokeApiService = JokeApiServiceFactory.createService()

    val adapter = JokeAdapter()
}