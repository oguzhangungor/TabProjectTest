package com.ogungor.tabprojecttestlist.feed.topten

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ogungor.tabprojecttestlist.R
import com.ogungor.tabprojecttestlist.network.model.MatchModel


class TopTenFeedFragment : Fragment(),TopTenFeedFragmentContract.View {
    private lateinit var topTenFeedFragmentPresenter: TopTenFeedFragmentContract.Presenter
    private  lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TopTenRecyclerAdapter



    override fun onAttach(context: Context) {
        super.onAttach(context)
        topTenFeedFragmentPresenter= TopTenFeedFragmentPresenter().apply {
            setView(this@TopTenFeedFragment)
            create()
            getDataFromFireStore()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_top_ten_feed, container, false)
        recyclerView= view.findViewById(R.id.topten_recycler_view)
        layoutManager= LinearLayoutManager(context)
        recyclerView.layoutManager= layoutManager
        adapter= TopTenRecyclerAdapter(ArrayList<MatchModel>())
        recyclerView.adapter=adapter


        return view
    }

    override fun initUi() {

    }

    override fun showAllMatches(model: java.util.ArrayList<MatchModel>) {
        adapter.setList(model)
    }
}