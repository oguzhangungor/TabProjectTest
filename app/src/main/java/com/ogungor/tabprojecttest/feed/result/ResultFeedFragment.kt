package com.ogungor.tabprojecttest.feed.result

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ogungor.tabprojecttest.R
import com.ogungor.tabprojecttest.feed.daily.DailyRecyclerAdapter
import com.ogungor.tabprojecttest.network.model.MatchModel

class ResultFeedFragment : Fragment(), ResultFeedFragmentContract.View {

    private lateinit var resultFeedFragmentPresenter: ResultFeedFragmentContract.Presenter
    private  lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ResultRecyclerAdapter



    override fun onAttach(context: Context) {
        super.onAttach(context)
       resultFeedFragmentPresenter= ResultFeedFragmentPresenter().apply {
            setView(this@ResultFeedFragment)
            create()
            getDataFromFireStore()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_result_feed, container, false)
        recyclerView= view.findViewById(R.id.result_recycler_view)
        layoutManager= LinearLayoutManager(context)
        recyclerView.layoutManager= layoutManager
        adapter= ResultRecyclerAdapter(ArrayList<MatchModel>())
        recyclerView.adapter=adapter


        return view
    }

    override fun initUi() {

    }

    override fun showAllMatches(model: java.util.ArrayList<MatchModel>) {
        adapter.setList(model)
    }
}