package com.tarafi.velolibreapp.common.app.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.tarafi.velolibreapp.R
import com.tarafi.velolibreapp.utils.ABFontUtils

class ATButton : AppCompatButton {

    private var mContext:Context

    constructor(context: Context):super(context){
        mContext=context
        setTypeface(ABFontUtils.getTypeface(mContext, ABFontUtils.LATO,ABFontUtils.REGULAR))
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet) {
        mContext=context
        if(!isInEditMode){
            applyCustomFont(attributeSet)
        }
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle : Int):super(context,attributeSet,defStyle){
        mContext=context
        if(!isInEditMode){
            applyCustomFont(attributeSet)
        }

    }

    private fun applyCustomFont(attributeSet: AttributeSet){
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.View, 0, 0)
        val fontName = typedArray.getInt(R.styleable.View_font_name, ABFontUtils.LATO)
        val fontType = typedArray.getInt(R.styleable.View_font_type, ABFontUtils.REGULAR)
        setTypeface(ABFontUtils.getTypeface(mContext,fontName,fontType))

    }
}