package it.tjeridi.stepview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.get
import it.tjeridi.stepview.extension.isNotNullOrZero
import it.tjeridi.stepview.utils.StepViewDrawableUtils
import it.tjeridi.stepview.utils.StepViewUtils
import kotlinx.android.synthetic.main.item_step_view.view.*
import kotlinx.android.synthetic.main.view_step_progressbar.view.*
import kotlin.math.min


internal class StepViewHelper {

    var totalStep = 0

    private var view: View? = null

    private var context: Context? = null

    private var llStep: LinearLayout? = null

    private var minWidthItemStepProgressBar: Int? = null

    fun attachToView(
        context: Context,
        view: View?,
        llStep: LinearLayout,
        minWidthItemStepProgressBar: Int
    ) {
        this.context = context
        this.view = view
        this.llStep = llStep
        this.minWidthItemStepProgressBar = minWidthItemStepProgressBar
    }

    fun updateStepView(
        maxStep: Float,
        widthStepProgressBar: Float,
        @ColorRes colorItemStrokeSelected: Int = 0,
        @ColorRes colorItemStrokeUnSelected: Int = 0
    ) {
        llStep?.removeAllViews()
        if (widthStepProgressBar != 0F) {
            totalStep = min(totalStep, maxStep.toInt())
            val remainingStepProgressBarWithoutItemStep =
                widthStepProgressBar - (minWidthItemStepProgressBar.isNotNullOrZero() * totalStep)
            val drawableItemStrokeSelected = StepViewDrawableUtils.changeStrokeDrawableColor(
                context,
                R.drawable.item_step_progressbar_circle,
                colorItemStrokeSelected
            )

            val drawableItemStrokeUnSelected = StepViewDrawableUtils.changeStrokeDrawableColor(
                context,
                R.drawable.item_step_solid_progressbar_circle,
                colorItemStrokeUnSelected,
                R.dimen.stroke_unselected_width
            )


            for (i in 0 until totalStep) {
                val x = StepViewUtils.calculateXPositionStepItem(
                    remainingStepProgressBarWithoutItemStep,
                    totalStep,
                    i
                )

                val viewItemStep = View.inflate(context, R.layout.item_step_view, null)

                drawableItemStrokeSelected?.let {
                    viewItemStep?.containerItemStepSeekBar?.background = drawableItemStrokeSelected
                }

                drawableItemStrokeUnSelected?.let {
                    viewItemStep?.itemStepSeekBarSolidView?.background =
                        drawableItemStrokeUnSelected
                }


                viewItemStep?.x = x
                llStep?.addView(
                    viewItemStep,
                    minWidthItemStepProgressBar.isNotNullOrZero(),
                    minWidthItemStepProgressBar.isNotNullOrZero()
                )
            }

            view?.stepProgressBar?.max = totalStep.minus(1)
        }
    }

    fun updateStepViewHeight(viewStepProgressBar: View?, minWidthItemStepProgressBar: Int) {
        val stepProgressBarLayoutParams = viewStepProgressBar?.stepProgressBar?.layoutParams
        stepProgressBarLayoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        stepProgressBarLayoutParams?.height = minWidthItemStepProgressBar / 5
        viewStepProgressBar?.stepProgressBar?.layoutParams = stepProgressBarLayoutParams
    }

    fun setSelectedStep(step: Int, stepProgressBar: ProgressBar, @ColorRes colorSelected: Int) {
        val childCount = llStep?.childCount
        if (step < childCount.isNotNullOrZero() && step >= 0) {
            for (i in 0 until childCount.isNotNullOrZero()) {
                val viewItemStep = llStep?.get(i)
                if (i == step) {
                    viewItemStep?.containerItemStepSeekBar?.visibility = View.VISIBLE
                    context?.let {
                        viewItemStep?.itemStepSeekBarSolidView?.setBackgroundColor(
                            ContextCompat.getColor(
                                it,
                                colorSelected
                            )
                        )
                    }
                } else {
                    viewItemStep?.containerItemStepSeekBar?.visibility = View.INVISIBLE
                }
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                stepProgressBar.setProgress(step, true)
            } else {
                stepProgressBar.progress = step
            }
        }
    }
}