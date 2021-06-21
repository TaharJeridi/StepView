package it.tjeridi.stepview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*
import it.tjeridi.stepview.extension.*
import it.tjeridi.stepview.utils.StepViewDrawableUtils
import it.tjeridi.stepview.utils.StepViewUtils
import kotlinx.android.synthetic.main.view_step_progressbar.view.*

class StepView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val minWidthItemStepProgressBar =
        context.resources.getDimensionPixelSize(Constants.MIN_WIDTH_ITEM_STEP_PROGRESS_BAR)

    private val minWithMarginItemStepProgressBar =
        context.resources.getDimension(Constants.MIN_WIDTH_MARGIN_ITEM_STEP_PROGRESS_BAR)

    private var widthItemStepProgressBarWithMargin: Float? = null

    private var maxStep: Float? = null

    private var widthStepProgressBar: Float? = null

    private var viewStepProgressBar: View? = null

    private val stepViewHelper: StepViewHelper = StepViewHelper()

    init {
        viewStepProgressBar = View.inflate(context, R.layout.view_step_progressbar, this)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.StepView)
            val marginStart =
                typedArray.getDimensionPixelSize(R.styleable.StepView_step_view_margin_start, 0)
            val marginEnd =
                typedArray.getDimensionPixelSize(R.styleable.StepView_step_view_margin_end, 0)
            viewStepProgressBar?.containerStepProgressBar?.updateMargins(
                marginStart,
                viewStepProgressBar?.containerStepProgressBar?.marginTop.isNotNullOrZero(),
                marginEnd,
                viewStepProgressBar?.containerStepProgressBar?.marginBottom.isNotNullOrZero(),
            )
            typedArray.recycle()

        }
        widthStepProgressBar = StepViewUtils.calculateStepViewWidth(context, viewStepProgressBar)
        widthItemStepProgressBarWithMargin =
            minWidthItemStepProgressBar.plus(minWithMarginItemStepProgressBar)
                .isGreaterThanZeroOrDefault(widthStepProgressBar.isNotNullOrDefault(1F))
        maxStep =
            widthStepProgressBar.isNotNullOrZero() / widthItemStepProgressBarWithMargin.isNotNullOrDefault(
                widthItemStepProgressBarWithMargin.isNotNullOrDefault(1F)
            )

        stepViewHelper.updateStepViewHeight(viewStepProgressBar, minWidthItemStepProgressBar)

        stepProgressBar.updatePadding(
            context.resources.getDimensionPixelSize(R.dimen.solid_view_min_width_item_progress_bar),
            stepProgressBar.paddingTop,
            context.resources.getDimensionPixelSize(R.dimen.solid_view_min_width_item_progress_bar),
            stepProgressBar.paddingBottom
        )

        stepViewHelper.attachToView(
            context,
            viewStepProgressBar,
            llStep,
            minWidthItemStepProgressBar
        )


    }

    /*
     * public method
     */

    @Suppress("UNUSED")
    fun setTotalStep(totalStep: Int) {
        stepViewHelper.totalStep = totalStep
        stepViewHelper.updateStepView(
            maxStep.isNotNullOrZero(),
            widthStepProgressBar.isNotNullOrZero()
        )
    }

    @Suppress("UNUSED")
    fun setColorItem(
        @ColorRes colorItemStrokeSelected: Int,
        @ColorRes colorItemStrokeUnSelected: Int
    ) {
        stepViewHelper.updateStepView(
            maxStep.isNotNullOrZero(),
            widthStepProgressBar.isNotNullOrZero(),
            colorItemStrokeSelected,
            colorItemStrokeUnSelected
        )
    }

    @Suppress("UNUSED")
    fun setColor(@ColorRes stepViewColor: Int) {
        StepViewDrawableUtils.changeProgressDrawable(
            context,
            stepProgressBar,
            stepViewColor,
            R.id.stepViewBackground
        )
    }

    @Suppress("UNUSED")
    fun setProgressColor(@ColorRes stepViewProgressColor: Int) {
        StepViewDrawableUtils.changeProgressDrawable(
            context,
            stepProgressBar,
            stepViewProgressColor,
            R.id.stepViewProgressDrawable
        )
    }

    @Suppress("UNUSED")
    fun setSelectedStep(selectedStep: Int,@ColorRes colorStepSelected:Int) {
        stepViewHelper.setSelectedStep(selectedStep, stepProgressBar, colorStepSelected)
    }

}