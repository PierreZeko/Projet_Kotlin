package com.example.chucknorrisproject

import android.animation.ObjectAnimator
import com.example.chucknorrisproject.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = adapter
        callJoke()

        /**recyclerview.addOnScrollListener(RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerViewIntern: RecyclerView, dx, dy) {
                super.onScrolled(recyclerViewIntern, dx, dy)
                if (! isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == numberJokes) {
                        recyclerview.adapter = JokeAdapter()
                    }
                }
            }
        })**/


        // à lire: voir à la fin de la page
    }

    override fun onDestroy() {
        super.onDestroy()
        println("info avant ${composite_disposable.size()}")
        composite_disposable.clear()
        println("info après ${composite_disposable.size()}")
    }

    fun callJoke() {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.max = 10000
        progressBar.progress = 0
        ObjectAnimator.ofInt(progressBar, "progress", progressBar.max).setDuration(2000).start()
        progressBar.visibility = View.VISIBLE
        joke_service.giveMeAJoke().toObservable().repeat(10).delay(2000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onError = {
                progressBar.visibility = View.INVISIBLE
                Log.d("error", "an error occured") },
            onNext = {
                progressBar.visibility = View.INVISIBLE
                Log.d("success", "The joke is: ${it.value}")
                adapter.updateList(it)
            },
            onComplete = {Log.d("end", "10 jokes have been shown")}
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

    val linearLayoutManager: LinearLayoutManager = findViewById<RecyclerView>(R.id.recycler_view).layoutManager as LinearLayoutManager
    val lastJokeVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()
    val isLoading = false
    val numberJokes = 10
}


/** chose à rajouter à la fin pour améliorer l'application:

- image application
- interface bienvenue application
- écran d'accueil avec boutons: blague (mainActivity), exit, lien github du projet, description de l'appli (ce qu'elle fait et pourquoi elle a été créée)
- ajouter des sons lors de l'activation de certaines fonctionnalités **/