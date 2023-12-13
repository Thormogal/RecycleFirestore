package com.example.recyclefirestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private var gameList: MutableList<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameName: TextView = itemView.findViewById(R.id.game_name)
        val gameConsoles: TextView = itemView.findViewById(R.id.game_consoles)
        val gameVotes: ProgressBar = itemView.findViewById(R.id.game_votes)
        val gameVotesNumbers: TextView = itemView.findViewById(R.id.gameVotesTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = gameList[position]
        holder.gameName.text = currentItem.name
        holder.gameConsoles.text = currentItem.consoles.joinToString(", ")
        holder.gameVotes.progress = calculateVotePercentage(currentItem.votes)

        val votesString = holder.itemView.context.getString(R.string.vote_count, currentItem.votes)
        holder.gameVotesNumbers.text = votesString
    }

    override fun getItemCount() = gameList.size

    private fun calculateVotePercentage(votes: Int): Int {
        val totalVotes = gameList.sumOf { it.votes }
        return if (totalVotes > 0) votes * 100 / totalVotes else 0
    }

    fun sortGameList(list: MutableList<Game>) {
        gameList.clear()
        gameList.addAll(list.sortedByDescending { it.votes })
        notifyDataSetChanged()
    }
}