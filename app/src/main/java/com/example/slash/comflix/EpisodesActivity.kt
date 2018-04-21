package com.example.slash.comflix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.slash.comflix.adapter.Episode
import com.example.slash.comflix.adapter.EpisodesAdapter
import com.example.slash.comflix.entities.GridSpacingItemDecoration
import com.example.slash.comflix.entities.dpToPx
import kotlinx.android.synthetic.main.activity_episodes.*

class EpisodesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)
        setSupportActionBar(findViewById(R.id.retour_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val viewManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        val episodesList = createEpisodesList()
        val viewAdapter = EpisodesAdapter(this,episodesList)
        episodes_list.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter= viewAdapter
            addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10),true))
        }


    }

    fun createEpisodesList():ArrayList<Episode>
    {
        var array = ArrayList<Episode>()
        for (i in 0..10)
        {
            array.add(Episode(resources.obtainTypedArray(R.array.episodeCover).getResourceId(0,-1),
                    resources.getStringArray(R.array.episodeTitle)[0],
                    resources.getStringArray(R.array.episodeDescription)[0]))
        }

        return array
    }
}
