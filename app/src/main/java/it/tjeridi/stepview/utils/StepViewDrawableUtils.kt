package it.tjeridi.stepview.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import it.tjeridi.stepview.R


internal object StepViewDrawableUtils {

    fun changeProgressDrawable(
        context: Context?,
        view: ProgressBar,
        @ColorRes colors: Int,
        @IdRes layerListId: Int
    ): Drawable? {
        return try {
            var drawableResult: Drawable? = null
            context?.let {
                if (colors != 0) {
                    val unwrappedDrawable = view.currentDrawable
                    unwrappedDrawable?.let {
                        drawableResult = DrawableCompat.wrap(unwrappedDrawable)
                        drawableResult?.let {
                            if (drawableResult is LayerDrawable) {
                                val drawable =
                                    (drawableResult as LayerDrawable).findDrawableByLayerId(
                                        layerListId
                                    )
                                DrawableCompat.setTint(
                                    drawable,
                                    ContextCompat.getColor(context, colors)
                                )
                            }
                        }
                    }
                }
            }
            drawableResult
        } catch (e: Throwable) {
            null
        }
    }

    fun changeStrokeDrawableColor(
        context: Context?,
        @DrawableRes drawableRes: Int,
        @ColorRes colors: Int,
        @DimenRes dimenStroke:Int = R.dimen.default_drawable_stroke
    ): Drawable? {
        var drawableResult: Drawable? = null
        return try {
            context?.let {
                if (drawableRes != 0 && colors != 0) {
                    val unwrappedDrawable = AppCompatResources.getDrawable(context, drawableRes)
                    unwrappedDrawable?.let {
                        drawableResult = DrawableCompat.wrap(unwrappedDrawable)
                        if (drawableResult is GradientDrawable) {
                            (drawableResult as GradientDrawable).setStroke(
                                context.resources.getDimensionPixelSize(dimenStroke),
                                ContextCompat.getColor(context, colors)
                            )
                        }
                    }
                }
            }
            drawableResult
        } catch (e: Throwable) {
            null
        }
    }
}