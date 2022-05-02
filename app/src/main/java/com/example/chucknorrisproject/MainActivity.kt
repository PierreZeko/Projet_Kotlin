package com.example.chucknorrisproject

import android.animation.ObjectAnimator
import com.example.chucknorrisproject.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = adapter

        //val buttonJoke = findViewById<Button>(R.id.button_id)
        //buttonJoke.setOnClickListener { callJoke() }

        Log.d("test avant if", "$savedInstanceState")
        Log.d("test avant if", "${savedInstanceState?.containsKey(keyRotation) != true}")
        if (savedInstanceState?.containsKey(keyRotation) != true) {
            callJoke()

        /** JokeView(context = parent, attrs = null, defStyleAttr = 0).setupView(model = ) **/

        }

        // à lire: voir à la fin de la page
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        val listJokeSerialized = Json.encodeToString(adapter.jokes)
        Log.d("listSerialized", listJokeSerialized)
        savedInstanceState.putString(keyRotation, listJokeSerialized)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val listRestoredJoke = savedInstanceState.getString(keyRotation)
        val listJokeDeserialized: List<Joke> = listRestoredJoke
            ?.let { Json.decodeFromString(it) }
            ?: emptyList()
        listJokeDeserialized.forEach { adapter.updateList(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("info before ${compositeDisposable.size()}")
        compositeDisposable.clear()
        println("info after ${compositeDisposable.size()}")
    }

    private fun callJoke() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.max = 10000
        progressBar.progress = 0
        ObjectAnimator.ofInt(progressBar, "progress", progressBar.max).setDuration(2000).start()
        progressBar.visibility = View.VISIBLE
        jokeService.giveMeAJoke().toObservable().repeat(10).delay(2000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onError = {
                progressBar.visibility = View.INVISIBLE
                Log.d("error", "an error occurred") },
            onNext = {
                progressBar.visibility = View.INVISIBLE
                Log.d("success", "The joke is: ${it.value}")
                adapter.updateList(it)
            },
            onComplete = {
                Log.d("end", "10 jokes have been shown") }
        ).also { compositeDisposable.add(it) }
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

    private val keyRotation = "ListJokeToRemember"
    private val compositeDisposable = CompositeDisposable()
    private val jokeService: JokeApiService = JokeApiServiceFactory.createService()
    private val adapter = JokeAdapter(onBottomReach = {callJoke()
    Log.d("adapter", "the adapter has been called")})
}


/** chose à rajouter à la fin pour améliorer l'application:

- interface bienvenue application
- écran d'accueil avec boutons: blague (mainActivity), exit, lien github du projet, description de l'appli (ce qu'elle fait et pourquoi elle a été créée)
- ajouter des sons lors de l'activation de certaines fonctionnalités **/