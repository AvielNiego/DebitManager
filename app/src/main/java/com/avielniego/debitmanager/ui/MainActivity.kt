package com.avielniego.debitmanager.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avielniego.debitmanager.R
import com.avielniego.debitmanager.ui.debitList.DebitListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.debit_list_fragment_container, DebitListFragment.new())
                .commit()
    }
}
