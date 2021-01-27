package com.ogungor.tabprojecttest.feed.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ogungor.tabprojecttest.R
import com.ogungor.tabprojecttest.feed.result.ResultRecyclerAdapter
import com.ogungor.tabprojecttest.network.model.MatchModel

class ProfileRecyclerAdapter(private var matchList: ArrayList<MatchModel>) :
    RecyclerView.Adapter<ProfileRecyclerAdapter.BetHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_row, parent, false)
        return BetHolder(view)
    }

    override fun onBindViewHolder(holder: BetHolder, position: Int) {
        val currentMatch = matchList[position]
        holder.run {
            currentMatch.homeTeam?.let {
                textViewMatch.text = "$it - ${currentMatch.awayTeam}"
            }
        }

    }


    override fun getItemCount(): Int {
        return matchList.size
    }

    fun setList(list: ArrayList<MatchModel>) {
        matchList = list
        notifyDataSetChanged()
    }

    class BetHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewMatch: TextView = view.findViewById(R.id.profile_mail_adress_textView)

    }


}
