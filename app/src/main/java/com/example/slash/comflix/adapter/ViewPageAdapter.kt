package com.example.slash.comflix.adapter



import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.example.slash.comflix.fragment.MoviesFragment
import com.example.slash.comflix.fragment.PersonFragment
import com.example.slash.comflix.fragment.SeriesFragment
import com.example.slash.comflix.fragment.TrendingFragment


class ViewPageAdapter: FragmentPagerAdapter {
    private val FRAGMENT_COUNT = 4

    constructor(fm: FragmentManager?) : super(fm)


    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {return TrendingFragment()}
            1 ->  return MoviesFragment()
            2 ->  return SeriesFragment()
            3->  return PersonFragment()
            else -> { return  TrendingFragment()
            }
        }
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.i("title=",position.toString())
        when (position) {
            0 -> return "Trending"
            1 -> return "Movies"
            2 -> return "Series"
            3 -> return "Persons"
            else -> { return  "Trending"
            }
        }
    }

}