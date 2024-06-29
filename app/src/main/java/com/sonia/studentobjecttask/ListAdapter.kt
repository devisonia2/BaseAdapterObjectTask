package com.sonia.studentobjecttask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class listadapter (var list: ArrayList<Student>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 1L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.activity_list_adapter, parent, false)
        val tvName = view.findViewById<TextView>(R.id.tvname)
        tvName.setText(list[position].name.toString())
        val tvRollno = view.findViewById<TextView>(R.id.tvrollno)
        .setText(list[position].rollNo.toString())
        val tvSubject = view.findViewById<TextView>(R.id.tvsubject)
        tvSubject.setText(list[position].subject.toString())
        return view
    }
}
