package com.technologiyagroup.matrajayotish.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technologiyagroup.matrajayotish.R
import com.technologiyagroup.matrajayotish.model.commonRecyclModel.PujaTipsRecyclModel

class RecyclerViewAdaptor(private val mList: List<PujaTipsRecyclModel>): RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdaptor.ViewHolder, position: Int) {
        var data = mList.get(position)
        holder.pujaTxt.text =data.instruction
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val pujaTxt: TextView = itemView.findViewById(R.id.pujaTxt)
    }
}