package com.example.slash.comflix.entities

import android.content.res.Resources
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import com.example.slash.comflix.R

/**
 * Created by Slash on 12/04/2018.
 */
class GridSpacingItemDecoration(var spaceCount:Int,var spacing: Int,var includeEdge:Boolean) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        var position= parent!!.getChildAdapterPosition(view)
        var column = position % spaceCount
        if( includeEdge ){
            outRect.left=  spacing - column*spaceCount/spaceCount
            outRect.right= (column+1)* spacing/spaceCount

            if(position< spaceCount){
                outRect.top= spacing
            }
            outRect.bottom=spacing
        }else{
            outRect.left=column*spacing/spaceCount
            outRect.right= spacing-(column+1)*spacing/spaceCount
            if(position>= spaceCount){
                outRect.top= spacing

            }
        }
    }
}
 fun dpToPx(dp:Int):Int {

    return Math.round((TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp.toFloat(), DisplayMetrics())))
}