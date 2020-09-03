package com.amey.pioneerapplication.utils

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.amey.pioneerapplication.R
import java.util.*

class TypeFaceHelper private constructor(private val context: Context) {
    fun getStyleTypeFace(fileName: String): Typeface? {
        var typeface = sTypeFaces[fileName]
        if (typeface == null) {
            typeface = ResourcesCompat.getFont(context, R.font.roboto_medium)
            sTypeFaces[fileName] = typeface
        }
        return typeface
    }

    companion object {
        const val REGULAR = "font/roboto_regular.ttf"
        const val MEDIUM = "font/roboto_medium.ttf"
        const val FONT_AWESOME = "font/fontawesome.otf"

        private var typeFaceHelper: TypeFaceHelper? = null
        fun getInstance(context: Context): TypeFaceHelper? {
            if (typeFaceHelper == null) {
                typeFaceHelper = TypeFaceHelper(context)
            }
            return typeFaceHelper
        }

        private val sTypeFaces =
            HashMap<String, Typeface?>(1)
    }

}