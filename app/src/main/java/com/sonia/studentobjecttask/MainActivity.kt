package com.sonia.studentobjecttask

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ListAdapter
import com.sonia.studentobjecttask.databinding.ActivityMainBinding
import com.sonia.studentobjecttask.databinding.CustomdialogBinding

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
        binding?.listview?.adapter = listAdapter
            binding?.btnfab?.setOnClickListener {
                var dialog = Dialog(this)
                dialog.setContentView(R.layout.customdialog)
                dialog.show()
                var etName = dialog.findViewById<EditText>(R.id.etname)
                var etRollno = dialog.findViewById<EditText>(R.id.etrollno)
                var etSubject = dialog.findViewById<EditText>(R.id.etsubject)
                var btnupdate = dialog.findViewById<Button>(R.id.btnupdate)
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                btnupdate?.setOnClickListener {
                    if (etName?.text?.toString().isNullOrEmpty()) {
                        etName?.error = "Enter Your Name "
                    } else if (etRollno?.text?.toString().isNullOrEmpty()) {
                        etRollno?.error = "Enter Your rollno "
                    } else if (etSubject?.text?.toString().isNullOrEmpty()) {
                        etSubject?.error = "Enter Your subject "
                    } else {
                        studentList.add(
                            Student(
                                etName.text.toString().toInt(),
                                etRollno.text.toString(),
                                etSubject.text.toString()
                            )
                        )
                    }
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            binding?.listview?.setOnItemClickListener { parent, view, position, id ->
                studentList.removeAt(position)
                listAdapter.notifyDataSetChanged()
            }
        }
    }


