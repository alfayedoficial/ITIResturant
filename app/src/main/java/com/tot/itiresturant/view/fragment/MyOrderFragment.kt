package com.tot.itiresturant.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tot.itiresturant.viewmodel.MyOrderViewModel

import com.tot.itiresturant.R

class MyOrderFragment : Fragment() {

    companion object {
        fun newInstance() = MyOrderFragment()
    }

    private lateinit var viewModel: MyOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
