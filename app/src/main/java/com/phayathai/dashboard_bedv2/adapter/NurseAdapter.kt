package com.phayathai.dashboard_bedv2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.phayathai.dashboard_bedv2.R
import com.phayathai.dashboard_bedv2.model.view.response.Nurse


class NurseAdapter(private val items: ArrayList<Nurse>
): RecyclerView.Adapter<NurseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nurse, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {

        val textNurseTitle: TextView = itemView.findViewById(R.id.textNurseTitle)

        fun bind(nurse: Nurse) {
            itemView.apply {
                textNurseTitle.text = nurse.title.toString()
            }
        }
    }

}