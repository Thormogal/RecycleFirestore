package com.example.recyclefirestore

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class PollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll)

        val db = FirebaseFirestore.getInstance()
        val gamesCollection = db.collection("games")
        val myRecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        val addGameButton = findViewById<Button>(R.id.addGameButton)
        val myAdapter = GameAdapter(mutableListOf())
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.adapter = myAdapter

        gamesCollection.get()
            .addOnSuccessListener { result ->
                val gameList = result.map { document ->
                    Game(
                        name = document.getString("name") ?: "",
                        consoles = (document.get("consoles") as? List<String>)?.toMutableList() ?: mutableListOf(),
                        votes = document.getLong("votes")?.toInt() ?: 0
                    )
                }.toMutableList()
                myAdapter.sortGameList(gameList)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        addGameButton.setOnClickListener {

        }

    }
}