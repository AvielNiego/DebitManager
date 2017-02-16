package com.avielniego.debitmanager.ui.debitList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.avielniego.debitmanager.R
import com.avielniego.debitmanager.messageParser.Debit
import java.text.NumberFormat

class DebitListAdapter(debits: List<Debit> = emptyList()): RecyclerView.Adapter<DebitListAdapter.ViewHolder>() {

    var debits = debits
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.debitSumTextView.text = NumberFormat.getInstance().format(debits[position].sum)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.debit_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return debits.count()
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val debitSumTextView = view.findViewById(R.id.debit_sum) as TextView
    }
}