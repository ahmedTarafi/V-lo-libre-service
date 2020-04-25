package com.tarafi.velolibreapp.common.app.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.tarafi.velolibreapp.R
import com.tarafi.velolibreapp.common.app.activity.MainActivity
import kotlinx.android.synthetic.main.view_header.view.*

class ATHeader  : RelativeLayout {

    private var mContext:Context

    constructor(context:Context , attributeSet: AttributeSet) : super(context,attributeSet) {
        mContext=context
        init(attributeSet)
    }

    constructor(context: Context,attributeSet: AttributeSet,defStyle : Int):super(context,attributeSet,defStyle){
        mContext=context
        init(attributeSet)
    }

    private fun init(attributeSet: AttributeSet){
        View.inflate(mContext, R.layout.view_header,this)
        val typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.ATHeader, 0, 0)
        val headerTitle = typedArray.getString(R.styleable.ATHeader_header_title)
        val rightIcnDrw = typedArray.getDrawable(R.styleable.ATHeader_header_right_icn)
        val btnBackVisible = typedArray.getBoolean(R.styleable.ATHeader_header_back,false)
        if(btnBackVisible){
            btnMenu.visibility=View.GONE
            btnBack.visibility=View.VISIBLE
        }
        else{
            //btnMenu.visibility=View.VISIBLE
            btnBack.visibility=View.GONE

        }
        titleTextView.text=headerTitle?:""
        imgRight.setImageDrawable(rightIcnDrw)
        /*btnMenu.setOnClickListener {
            val mainActivity = mContext as MainActivity
            mainActivity.actionMenu()
        }*/

        btnBack.setOnClickListener {
            val mainActivity = mContext as MainActivity
            mainActivity.onBackPressed()
        }

    }

    fun setHeaderTitleText(headerTitle : Int){
        titleTextView.setText(headerTitle)
    }

    fun setOnBackBtnListener(onBackBtnCLickListener : (()->Unit)?){
        btnBack.setOnClickListener {
            onBackBtnCLickListener?.invoke()
        }
    }

    fun setImgRightClickListener(onImgRightBtnCLickListener : (()->Unit)?){
        imgRight.setOnClickListener {
            onImgRightBtnCLickListener?.invoke()
        }
    }

    fun setImgRightVisibility(visibility : Int){
        imgRight.visibility = visibility
    }


}