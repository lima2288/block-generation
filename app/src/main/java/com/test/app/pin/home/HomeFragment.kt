package com.test.app.pin.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.test.app.pin.BR
import com.test.app.pin.R
import com.test.app.pin.databinding.FragmentHomeBinding
import com.test.app.pin.utils.Response
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenToLiveData()
    }


    private fun hideKeyboardFrom() {
        val imm: InputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun listenToLiveData(){
        viewModel.response.observe(this, {
            when(it){
                Response.DISMISS_KEYBOARD->{
                    hideKeyboardFrom()
                }
                Response.VALIDATION_ERROR->{
                    Toast.makeText(context, getString(R.string.msg_validation),Toast.LENGTH_LONG).show()
                }
                Response.SUCCESS->{

                }else->{

                }
            }
        })
    }



}