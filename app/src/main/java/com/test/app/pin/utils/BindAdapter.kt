package com.test.app.pin.utils

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("setError")
fun setError(et : EditText, error : String?){
    error?.let {
        et.error = error.toString()
        et.requestFocus()
    }
}