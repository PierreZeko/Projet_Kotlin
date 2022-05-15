package com.example.chucknorrisproject

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class JokeTouchHelper(private val onJokeRemoved: Boolean, private val onItemMoved: Boolean) : ItemTouchHelper(
    object : ItemTouchHelper.SimpleCallback(
        UP or DOWN,
        LEFT or RIGHT
    ) {
        val adapter = JokeAdapter(onBottomReach = {})

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            if (onItemMoved) {
                Log.d("a joke has been moved", adapter.jokes[viewHolder.layoutPosition].value)
                val jokeInitialPosition: Int = viewHolder.layoutPosition
                val jokeFinalPosition: Int = target.layoutPosition
                Collections.swap(adapter.jokes, jokeInitialPosition, jokeFinalPosition)
                adapter.notifyItemMoved(jokeInitialPosition, jokeInitialPosition)
                return false
            }
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            if (onJokeRemoved) {
                when(swipeDir){
                    LEFT -> {
                        Log.d("a joke has been removed", adapter.jokes[viewHolder.layoutPosition].value)
                        adapter.jokes.remove(adapter.jokes[viewHolder.layoutPosition])
                        adapter.notifyItemRemoved(viewHolder.layoutPosition)
                    }

                    RIGHT -> {
                        Log.d("a joke has been removed", adapter.jokes[viewHolder.layoutPosition].value)
                        adapter.jokes.remove(adapter.jokes[viewHolder.layoutPosition])
                        adapter.notifyItemRemoved(viewHolder.layoutPosition)
                    }
                }
            }
        }
    }
)
