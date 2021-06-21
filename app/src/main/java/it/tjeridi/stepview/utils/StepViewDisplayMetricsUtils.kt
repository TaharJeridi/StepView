package it.tjeridi.stepview.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

internal object StepViewDisplayMetricsUtils {

    fun calculateDisplayWindowMetricsWidth(context: Context): Int? {
        return try {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val currentWindowMetrics = wm.currentWindowMetrics
            currentWindowMetrics.bounds.width()
        }catch (e:Throwable){
            null
        }
    }

    fun calculateDisplayWindowMetricsHeight(context: Context): Int? {
        return try {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val currentWindowMetrics = wm.currentWindowMetrics
            return currentWindowMetrics.bounds.height()
        }catch (e:Throwable){
            null
        }
    }

    @Suppress("DEPRECATION")
    fun calculateDisplayWidth(context: Context):Int{
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

    @Suppress("DEPRECATION")
    fun calculateDisplayHeight(context: Context):Int{
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

}