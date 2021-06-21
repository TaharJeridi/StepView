package it.tjeridi.stepview.utils

import android.content.Context
import android.view.View
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import it.tjeridi.stepview.extension.isGreaterThanOneOrZeroOrDefault
import kotlinx.android.synthetic.main.view_step_progressbar.view.*

internal object StepViewUtils {

    @Suppress("SimpleRedundantLet")
    fun calculateStepViewWidth(context: Context, view: View?): Float {
        view?.let {
            val widthScreen =
                StepViewDisplayMetricsUtils.calculateDisplayWindowMetricsWidth(context)?.let {
                    it
                } ?: kotlin.run {
                    StepViewDisplayMetricsUtils.calculateDisplayWidth(context)
                }
            val heightScreen =
                StepViewDisplayMetricsUtils.calculateDisplayWindowMetricsHeight(context)?.let {
                    it
                } ?: kotlin.run {
                    StepViewDisplayMetricsUtils.calculateDisplayHeight(context)
                }
            val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                widthScreen,
                View.MeasureSpec.EXACTLY
            )
            val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                heightScreen,
                View.MeasureSpec.EXACTLY
            )
            view.containerStepProgressBar.measure(widthMeasureSpec, heightMeasureSpec)
            return (view.containerStepProgressBar.measuredWidth - (view.containerStepProgressBar.marginStart + view.containerStepProgressBar.marginEnd)).toFloat()
        }
        return 0F
    }

    fun calculateXPositionStepItem(
        remainingStepProgressBarWithoutItemStep: Float,
        totalStep: Int,
        position: Int
    ): Float {
        return ((remainingStepProgressBarWithoutItemStep / (totalStep.isGreaterThanOneOrZeroOrDefault(
            totalStep.plus(1)
        ) - 1)) * position)
    }
}