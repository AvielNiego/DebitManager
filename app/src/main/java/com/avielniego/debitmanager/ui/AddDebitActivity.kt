package com.avielniego.debitmanager.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.avielniego.debitmanager.R
import com.avielniego.debitmanager.databaseAccess.ManualAddDebitService

class AddDebitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_debit)
        initViews()
    }

    private fun initViews() {
        (findViewById(R.id.cancel_button) as Button).setOnClickListener { finish() }
        (findViewById(R.id.add_button) as Button).setOnClickListener { onAddButtonClicked() }
    }

    private fun onAddButtonClicked() {
        val sum: String = (findViewById(R.id.debit_sum) as EditText).text.toString()
        if (sum.isBlank()) return

        val description = (findViewById(R.id.debit_description) as EditText).text.toString()
        ManualAddDebitService.startService(this, sum.toDouble(), description)
        finish()
    }

}
