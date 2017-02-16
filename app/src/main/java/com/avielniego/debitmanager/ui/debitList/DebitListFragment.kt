package com.avielniego.debitmanager.ui.debitList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avielniego.debitmanager.R

class DebitListFragment: Fragment() {
    companion object{
        fun new(): DebitListFragment {
            val fragment = DebitListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadArgs()
    }

    private fun loadArgs() {
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.debit_list_fragment, container, false)
    }
}