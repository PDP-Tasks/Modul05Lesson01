package dev.matyaqubov.modul05lesson01.adapter

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.max

class MyDotsIndicator  : RecyclerView.ItemDecoration() {
    companion object {
        private val DP: Float = Resources.getSystem().displayMetrics.density
    }

    private var colorActive = -0x22000000
    private var colorInActive = 0x33000000

    private val mIndicatorHeight = (DP * 16).toInt()
    private val mIndicatorStrokeWidth = DP * 4
    private val mIndicatorItemLength = DP * 4
    private val mIndicatorItemPadding = DP * 8
    private val mInterpolator: Interpolator = AccelerateDecelerateInterpolator()
    private val mPaint: Paint = Paint()

    init {
        mPaint.strokeWidth = mIndicatorStrokeWidth
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = parent.adapter!!.itemCount

        val totalLength = mIndicatorItemLength * itemCount
        val paddingBetweenItems = max(0, itemCount - 1) * mIndicatorItemPadding
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f
        val indicatoPosY = parent.height - mIndicatorHeight / 2f
        drawInactiveIndicators(c, indicatorStartX, indicatoPosY, itemCount)

        val layoutManager = parent.layoutManager as LinearLayoutManager?
        val activePositioin = layoutManager!!.findFirstVisibleItemPosition()
        if (activePositioin == RecyclerView.NO_POSITION) {
            return
        }
        val activeChild = layoutManager.findViewByPosition(activePositioin)
        val left = activeChild!!.left
        val width = activeChild.width

        val progress: Float = mInterpolator.getInterpolation(left * -1 / width.toFloat())
        drawHighlights(c, indicatorStartX, indicatoPosY, activePositioin, progress)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = mIndicatorHeight
    }

    private fun drawInactiveIndicators(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosy: Float,
        itemCount: Int
    ) {
        mPaint.color = colorInActive

        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
        var start = indicatorStartX
        for (i in 0 until itemCount) {
            c.drawCircle(start, indicatorPosy, mIndicatorItemLength / 2f, mPaint)
            start += itemWidth
        }
    }

    private fun drawHighlights(
        c: Canvas, indicatorStartX: Float, indicatorPosy: Float,
        highlightPosition: Int, progress: Float
    ) {
        mPaint.color = colorActive

        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
        if (progress == 0f) {
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            c.drawCircle(highlightStart, indicatorPosy, mIndicatorItemLength / 2f, mPaint)
        } else {
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            val partialLength = mIndicatorItemLength * progress + mIndicatorItemPadding * progress
            c.drawCircle(
                highlightStart + partialLength,
                indicatorPosy,
                mIndicatorItemLength / 2f,
                mPaint
            )
        }
    }

    fun setDotColor(@ColorInt setColorActive: Int, @ColorInt setColorInactive: Int) {
        colorActive = setColorActive
        colorInActive = setColorInactive
    }
}