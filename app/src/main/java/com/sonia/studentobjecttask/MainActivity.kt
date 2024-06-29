package com.sonia.studentobjecttask

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ListAdapter
import com.sonia.studentobjecttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var studentList = arrayListOf<Student>()
    var listAdapter = listadapter(studentList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        studentList.add(Student(rollNo = 1, "Raj", "English"))
        studentList.add(Student(rollNo = 2, "Rohit", "Hindi"))
        studentList.add(Student(rollNo = 3, "Aryan", "Maths"))
        binding?.listview?.adapter = listAdapter

        binding?.listview?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Position $position", Toast.LENGTH_LONG).show()
            Log.e(TAG, "position clicked ${binding?.listview?.getItemAtPosition(position)} ${
                    binding?.listview?.getItemAtPosition(position)
                }"
            )
        }
        binding?.btnflaot?.setOnClickListener {
            studentList.add(Student(4, "Khushi", "Science"))
            studentList.add(Student(5, "Anjali", "Punjabi"))
            studentList.add(Student(6, "Pranav", "Social Science"))

            listAdapter.notifyDataSetChanged()
        }
    }
}
