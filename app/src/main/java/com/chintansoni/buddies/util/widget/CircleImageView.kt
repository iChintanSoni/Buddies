package com.chintansoni.buddies.util.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var path = Path()

    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        radius = measuredWidth / 2f
        centerX = measuredWidth / 2f
        centerY = measuredHeight / 2f

    }

    override fun onDraw(canvas: Canvas?) {
        path.addCircle(centerX, centerY, radius, Path.Direction.CW)
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }

}