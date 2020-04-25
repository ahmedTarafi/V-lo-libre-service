package com.tarafi.velolibreapp.utils

import android.content.Context
import android.graphics.Typeface

class Font{
    var bold: Typeface?=null
    var light: Typeface?=null
    var medium: Typeface?=null
    var regular: Typeface?=null
    var extraBold: Typeface?=null
    var semiBold: Typeface?=null

}

object ABFontUtils  {
    //font name
    val LATO = 0
    val OPEN_SANS = 5
    val NUNITO_SANS = 8

    // font type
    val BOLD = 0
    val LIGHT = 1
    val MEDIUM = 2
    val REGULAR = 3
    val EXTRA_BOLD = 6
    val SEMI_BOLD = 7

    private var lato:Font?
    private var openSans : Font?
    private var nunitoSans : Font?

    init {
        lato = null
        openSans = null
        nunitoSans = null
    }

    private fun getLatoFont(context: Context):Font{
        if(lato == null){
            lato= Font()
            lato!!.bold= Typeface.createFromAsset(context.assets,"fonts/Lato-Bold.ttf")
            lato!!.light= Typeface.createFromAsset(context.assets,"fonts/Lato-Light.ttf")
            lato!!.medium= Typeface.createFromAsset(context.assets,"fonts/Lato-Medium.ttf")
            lato!!.regular= Typeface.createFromAsset(context.assets,"fonts/Lato-Regular.ttf")
            return lato!!
        }

        return lato!!
    }

    private fun getOpenSansFont(context: Context):Font{
        if(openSans==null){
            openSans=Font()
            openSans!!.bold= Typeface.createFromAsset(context.assets,"fonts/OpenSans-Bold.ttf")
            openSans!!.extraBold= Typeface.createFromAsset(context.assets,"fonts/OpenSans-ExtraBold.ttf")
            openSans!!.semiBold= Typeface.createFromAsset(context.assets,"fonts/OpenSans-Semibold.ttf")
            openSans!!.regular= Typeface.createFromAsset(context.assets,"fonts/OpenSans-Regular.ttf")
        }

        return openSans!!
    }

    private fun getNunitoSansFont(context: Context):Font{
        if(nunitoSans==null){
            nunitoSans= Font()
            nunitoSans!!.bold = Typeface.createFromAsset(context.assets,"fonts/NunitoSans-Bold.ttf")
            nunitoSans!!.regular = Typeface.createFromAsset(context.assets,"fonts/NunitoSans-Regular.ttf")
            nunitoSans!!.semiBold = Typeface.createFromAsset(context.assets,"fonts/NunitoSans-SemiBold.ttf")
        }

        return nunitoSans!!
    }

    fun getTypeface(context: Context, fontName:Int, fontType:Int): Typeface?{
        if(fontName== NUNITO_SANS){
            if(fontType== BOLD){
                return getNunitoSansFont(context).bold
            }
            if(fontType== REGULAR){
                return getNunitoSansFont(context).regular
            }
            if(fontType == SEMI_BOLD){
                return getNunitoSansFont(context).semiBold
            }
        }
        if(fontName== LATO){
            if(fontType==BOLD){
                return getLatoFont(context).bold
            }
            if(fontType== LIGHT){
                return getLatoFont(context).light
            }
            if(fontType== MEDIUM){
                return getLatoFont(context).medium
            }
            if(fontName== REGULAR){
                return getLatoFont(context).regular
            }
        }
        if(fontName == OPEN_SANS){
            if(fontType==BOLD){
                return getOpenSansFont(context).bold
            }
            if(fontType== REGULAR){
                return getOpenSansFont(context).regular
            }
            if(fontType== SEMI_BOLD){
                return getOpenSansFont(context).semiBold
            }
            if(fontName== EXTRA_BOLD){
                return getOpenSansFont(context).extraBold
            }
        }
        return getOpenSansFont(context).regular
    }


}