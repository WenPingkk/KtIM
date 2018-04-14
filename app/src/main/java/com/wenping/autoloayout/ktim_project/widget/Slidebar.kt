package com.wenping.autoloayout.ktim_project.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.wenping.autoloayout.ktim_project.R
import org.jetbrains.anko.sp

/**
 * Author WenPing
 * CreateTime 2018/4/14.
 * Description:
 */
class Slidebar(context: Context, attrs: AttributeSet?=null) : View(context, attrs) {

    var sectionHeight = 0f
    var textBaseLine = 0f
    var onSectionChangeListener:OnSectionChangeListener?=null

    var paint = Paint()

    companion object {
        private val SECTIONS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L","M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize= sp(12).toFloat()

            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //计算么个字符分配的高度
        sectionHeight = h*1.0f/ SECTIONS.size
        val fonrtMetric = paint.fontMetrics
        //计算绘制文本的高度；以baseline处为基线，asent为负值，descent为正值
        val textHeight = fonrtMetric.descent-fonrtMetric.ascent
        //计算基准线
        textBaseLine = sectionHeight/2+(textHeight/2-fonrtMetric.descent)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制字符的起始位置
        val x = width*1.0f/2
        var baseLine = textBaseLine
        SECTIONS.forEach {
            canvas.drawText(it,x,baseLine,paint)
            //更新，绘制下一个字符
            baseLine+=sectionHeight
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN->{
                setBackgroundResource(R.drawable.bg_slide_bar)
            }
            MotionEvent.ACTION_UP->{
                setBackgroundColor(Color.TRANSPARENT)
            }
        }
        return true
    }

    interface OnSectionChangeListener {

        fun onSectionChange(frstLetter:String)

        fun onSlideFinish()
    }

}