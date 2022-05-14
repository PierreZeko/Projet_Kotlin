package com.example.chucknorrisproject

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class JokeTouchHelper(private val onJokeRemoved: Boolean, private val onItemMoved: Boolean) : ItemTouchHelper(
    object : ItemTouchHelper.SimpleCallback(
        UP or DOWN,
        LEFT or RIGHT
    ) {
        val adapter = JokeAdapter(onBottomReach = {})

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            if (onItemMoved) {
                Log.d("a joke has been moved", adapter.jokes[viewHolder.layoutPosition].value)
                return true
            }
            else {
                return false
            }
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            if (onJokeRemoved) {
                Log.d("a joke has been removed", adapter.jokes[viewHolder.layoutPosition].value)
                adapter.notifyItemRemoved(viewHolder.layoutPosition)
            }
        }
    }
)
