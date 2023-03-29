package com.example.exoplayer_mvvm_app.util

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.exoplayer_mvvm_app.R

class DividerItemDecoration(
    val context: Context,
    val orientation: Int = VERTICAL,
    @ColorRes val colorRes: Int = R.color.popup_divider_color
) : androidx.recyclerview.widget.DividerItemDecoration(context, orientation) {
    init {
        val color = ContextCompat.getColor(context, colorRes)
        setDrawable(ColorDrawable(color))
    }
}