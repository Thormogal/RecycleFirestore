package com.example.recyclefirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll)

        val gameList = mutableListOf(
            Game("Super Mario Bros.", listOf("NES"), 100),
            Game("The Legend of Zelda", listOf("NES"), 200),
            Game("Minecraft", listOf("PC", "Xbox", "PS4"), 150)
        )

        val myAdapter = GameAdapter(gameList)

        val myRecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)

        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.adapter = myAdapter
    }
}