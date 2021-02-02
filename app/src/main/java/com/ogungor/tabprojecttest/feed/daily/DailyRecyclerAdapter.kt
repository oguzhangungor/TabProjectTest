package com.ogungor.tabprojecttest.feed.daily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ogungor.tabprojecttest.R
import com.ogungor.tabprojecttest.network.model.MatchModel

class DailyRecyclerAdapter(private var matchList: ArrayList<MatchModel>,
                           private var listener: OnItemClickListener) :
    RecyclerView.Adapter<DailyRecyclerAdapter.BetHolder>() {

    private var commentList:ArrayList<String>?=ArrayList()
    private var teamNameList:ArrayList<String>?=ArrayList()

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
            currentMatch.bet?.let {
                textViewBet.text = it
            }
            currentMatch.rate?.let {
                textViewRate.text = it
            }
            currentMatch.oldRate?.let {

                var controlCodeState = oldRateControl(it, textViewRate.text.toString())
                if (controlCodeState == 1) {
                    oldRateViewIcon.setImageResource(R.drawable.upicon)
                } else if (controlCodeState == 0) {
                    oldRateViewIcon.setImageResource(R.drawable.downicon)
                }
            }
            currentMatch.leauge.let {
                textViewLeague.text = it
            }
            currentMatch.date.let {
                textViewDate.text = it
            }
            currentMatch.start_time.let {
                textViewStartTime.text = it
            }
            currentMatch.comment.let {
                commentList?.add(it.toString())
                teamNameList?.add(textViewMatch.text.toString())
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

    inner class BetHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        var textViewMatch: TextView = view.findViewById(R.id.match_text)
        var textViewBet: TextView = view.findViewById(R.id.bet_text)
        var textViewRate: TextView = view.findViewById(R.id.rate_text)
        var textViewLeague: TextView = view.findViewById(R.id.league_text)
        var textViewDate: TextView = view.findViewById(R.id.date_text)
        var textViewStartTime: TextView = view.findViewById(R.id.start_time_text)
        var oldRateViewIcon: ImageView = view.findViewById(R.id.old_rate_icon)


        override fun onClick(v: View?) {
            var position:Int?=adapterPosition
           if (position!=RecyclerView.NO_POSITION) {
               listener.onItemClick(position!!,commentList!!,teamNameList!!)
           }
        }

        init {
            view.setOnClickListener(this)
        }


    }
    interface OnItemClickListener {
        fun onItemClick(position: Int, commentList:ArrayList<String>, teamList:ArrayList<String>)
    }

    fun strParseInt(str: String): Double {

        var parseInt = str.toDouble()

        return parseInt
    }

    fun oldRateControl(oldRate: String, rate: String): Int {

        var controlCode: Int

        if (oldRate !== "") {
            var oldRateControl = strParseInt(oldRate)
            var rateControl = strParseInt(rate)
            controlCode = if (oldRateControl > rateControl) {
                1
            } else {
                0
            }
            return controlCode
        }
        return 2

    }


}