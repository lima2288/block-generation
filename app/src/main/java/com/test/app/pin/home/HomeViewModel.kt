package com.test.app.pin.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.app.pin.utils.Response


class HomeViewModel(private val repo : HomeRepo) : ViewModel()  {

    companion object {
        const val pan = "1111222233334444"
    }

    val pin = ObservableField<String>()
    val generatedValue = ObservableField<String>()
    val error = ObservableField<String>()
    private  val _response = MutableLiveData<Response>()
    val response : LiveData<Response> = _response

    fun generatePin(){
        generatedValue.set("")
        _response.value = Response.DISMISS_KEYBOARD;
        if(validate(input = pin.get())){
            val format3 = repo.generateFormat3(pin.get()!!)
            generatedValue.set(format3)
            _response.value = Response.SUCCESS
        }else{
            _response.value = Response.VALIDATION_ERROR;
        }
    }

     fun validate(input : String?) :Boolean{

         input?.let{
             return input.length >= 4
         }
         return false
     }
}