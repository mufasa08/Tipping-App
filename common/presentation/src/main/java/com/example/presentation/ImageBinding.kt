package com.example.presentation

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.DraweeView
import com.facebook.drawee.view.SimpleDraweeView

@BindingMethods(
    BindingMethod(
        type = ImageView::class,
        attribute = "srcCompat",
        method = "setImageResource"
    ),
    BindingMethod(
        type = DraweeView::class,
        attribute = "colorFilter",
        method = "setColorFilter"
    ),
    BindingMethod(
        type = SimpleDraweeView::class,
        attribute = "imageUrl",
        method = "setImageURI"
    ),
    BindingMethod(
        type = SimpleDraweeView::class,
        attribute = "viewAspectRatio",
        method = "setAspectRatio"
    )
)
class ImageBinding

@BindingAdapter("tint")
fun ImageView.setImageTint(@ColorInt color: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color))
}

@BindingAdapter("tintColor")
fun ImageView.setImageTintColor(@ColorRes color: Int) {
    ImageViewCompat.setImageTintList(this, ContextCompat.getColorStateList(context, color))
}

@BindingAdapter(
    value = ["failureImage", "failureImageScaleType"],
    requireAll = true
)
fun SimpleDraweeView.setFailureImage(drawable: Drawable, scaleType: ScalingUtils.ScaleType) {
    // Make data binding converter to load failure image drawable instead of directly using
    // GenericDraweeView's `fresco:failureImage` XML attribute. `GenericDraweeView` internally uses
    // no-theming version of `Resources.getDrawable(int)` to load drawable and loaded drawable
    // can't be themed.
    hierarchy.setFailureImage(drawable, scaleType)
}

@BindingConversion
fun convertStringToFrescoScaleType(from: String): ScalingUtils.ScaleType {
    return when (from) {
        "fitXY" -> ScalingUtils.ScaleType.FIT_XY
        "fitStart" -> ScalingUtils.ScaleType.FIT_START
        "fitCenter" -> ScalingUtils.ScaleType.FIT_CENTER
        "fitEnd" -> ScalingUtils.ScaleType.FIT_END
        "center" -> ScalingUtils.ScaleType.CENTER
        "centerInside" -> ScalingUtils.ScaleType.CENTER_INSIDE
        "centerCrop" -> ScalingUtils.ScaleType.CENTER_CROP
        "focusCrop" -> ScalingUtils.ScaleType.FOCUS_CROP
        "fitBottomStart" -> ScalingUtils.ScaleType.FIT_BOTTOM_START
        else -> throw IllegalArgumentException("Unknown scale type: $from")
    }
}
