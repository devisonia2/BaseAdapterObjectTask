package com.sonia.studentobjecttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

class listadapter (var list: ArrayList<Student>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.list, parent, false)
        val tvRollno = view.findViewById<TextView>(R.id.tvRollNo)
        tvRollno.setText(list[position].rollNo.toString())
        val tvName = view.findViewById<TextView>(R.id.tvName)
        tvName.setText(list[position].name.toString())
        val tvSubject = view.findViewById<TextView>(R.id.tvSubject)
        tvSubject.setText(list[position].subject.toString())
        return view
    }
}
