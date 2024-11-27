package com.example.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.Data.data_grid
import com.example.order_app.R

class adapter_choose_gv(context: Context, private val listc: List<data_grid>) : ArrayAdapter<data_grid>(context, R.layout.viewholder_choose_gv) {

    private class ViewHolder(view: View) {
        val images: ImageView = view.findViewById(R.id.imgbeverage)
        val txt_name: TextView = view.findViewById(R.id.txtbeverage)
    }

    override fun getCount(): Int {
        return listc.size
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val rowView: View

        if (convertView == null) {
            val inflater = LayoutInflater.from(context) // Use the passed context
            rowView = inflater.inflate(R.layout.viewholder_choose_gv, parent, false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        // Set image and text for the current item
        viewHolder.images.setImageResource(listc[position].img)
        viewHolder.txt_name.text = listc[position].name_prd

        return rowView
    }
}
