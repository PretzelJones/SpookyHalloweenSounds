package design.bosson.spookyhalloweensounds.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Choreographer
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.appcompat.widget.AppCompatButton
import design.bosson.spookyhalloweensounds.R

class SeekButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {
    init { clipToOutline = true }
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.colorSeekBar)
    }
    //private val rect = RectF()

    private var getDurationMs: (() -> Long)? = null
    private var getPositionMs: (() -> Long)? = null
    private var seekToMs: ((Long) -> Unit)? = null
    private var isPlaying: (() -> Boolean)? = null

    private var scrubbing = false
    private var scrubbedPosMs = 0L

    private val touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop
    private var downX: Float = 0f
    private var moved: Boolean = false

    private val choreographer: Choreographer = Choreographer.getInstance()
    private val frameCb = object : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            if (isPlaying?.invoke() == true || scrubbing) {
                invalidate()
                choreographer.postFrameCallback(this)
            }
        }
    }

    fun bind(
        getDurationMs: () -> Long,
        getPositionMs: () -> Long,
        seekToMs: (Long) -> Unit,
        isPlaying: () -> Boolean
    ) {
        this.getDurationMs = getDurationMs
        this.getPositionMs = getPositionMs
        this.seekToMs = seekToMs
        this.isPlaying = isPlaying
        startTicker()
        invalidate()
    }

    fun onPlayStateChanged() {
        startTicker()
        invalidate()
    }

    private fun startTicker() {
        choreographer.removeFrameCallback(frameCb)
        if (isPlaying?.invoke() == true) choreographer.postFrameCallback(frameCb)
    }

    override fun onDetachedFromWindow() {
        choreographer.removeFrameCallback(frameCb)
        super.onDetachedFromWindow()
    }

    override fun onDraw(canvas: Canvas) {
        val dur = getDurationMs?.invoke() ?: 0L
        if (dur > 0L) {
            val pos = if (scrubbing) scrubbedPosMs else (getPositionMs?.invoke() ?: 0L)
            val pct = (pos.toFloat() / dur.toFloat()).coerceIn(0f, 1f)

            // edge-to-edge, clipping handled by clipToOutline=true
            val left = 0f
            val top = 0f
            val right = width.toFloat()
            val bottom = height.toFloat()
            val fillRight = left + (right - left) * pct
            canvas.drawRect(left, top, fillRight, bottom, progressPaint)
        }

        super.onDraw(canvas)
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val dur = getDurationMs?.invoke() ?: return super.onTouchEvent(event)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                parent?.requestDisallowInterceptTouchEvent(true)
                downX = event.x
                moved = false
                scrubbing = false
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (!moved && kotlin.math.abs(event.x - downX) > touchSlop) moved = true
                if (moved) {
                    scrubbing = true
                    scrubbedPosMs = xToMs(event.x, dur)
                    cancelLongPress()
                    isPressed = false
                    invalidate()
                    return true
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent?.requestDisallowInterceptTouchEvent(false)
                return if (scrubbing) {
                    scrubbing = false
                    seekToMs?.invoke(scrubbedPosMs)
                    onPlayStateChanged()
                    true
                } else {
                    performClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean = super.performClick()

    // map X to ms across the full width, no padding
    private fun xToMs(x: Float, durationMs: Long): Long {
        val left = 0f
        val right = width.toFloat()
        val clamped = x.coerceIn(left, right)
        val pct = if (right > left) (clamped - left) / (right - left) else 0f
        return (durationMs * pct).toLong().coerceIn(0L, durationMs)
    }
}
