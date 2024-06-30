package com.sonia.studentobjecttask

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog.show
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
        binding?.listview?.setOnItemClickListener { parent, view, position, id ->
            var dialogBinding = CustomdialogBinding.inflate(layoutInflater)
            var dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                show()
            }
            val update: String = "update"
            dialogBinding.btnsubmit.setText(update)
            val rollno: String = studentList[position].rollNo.toString()
            dialogBinding.etrollno.setText(rollno)
            val name: String = studentList[position].name.toString()
            dialogBinding.etname.setText(name)
            val subject: String = studentList[position].subject.toString()
            dialogBinding.etsubject.setText(subject)
            if (dialogBinding.etrollno.text.trim().toString().isNullOrEmpty()) {
                dialogBinding.etrollno.error = "Enter your RollNo"
            } else if (dialogBinding.etname.text.trim().toString().isNullOrEmpty()) {
                dialogBinding.etname.error = "Enter your Name"
            } else if (dialogBinding.etsubject.text.trim().toString().isNullOrEmpty()) {
                dialogBinding.etsubject.error = "Enter your Subject"
            } else {
                studentList[position] = Student(
                    dialogBinding.etrollno.text.toString().toInt(),
                    dialogBinding.etname.text.trim().toString(),
                    dialogBinding.etsubject.text.trim().toString()
                )
                listAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }
        binding?.listview?.setOnItemLongClickListener { parent, view, position, id ->
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("Delete Item")
                setMessage("Do you want to delete the item?")
                setCancelable(false)
                setNegativeButton("No") { _, _ ->
                    setCancelable(true)
                }
                setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this@MainActivity, "The item is  deleted", Toast.LENGTH_SHORT)
                        .show()
                    studentList.removeAt(position)
                    listAdapter.notifyDataSetChanged()
                }
                show()
            }
            return@setOnItemLongClickListener true
        }
        binding?.listview?.adapter = listAdapter

        binding?.btnfab?.setOnClickListener {
            var dialogBinding= CustomdialogBinding.inflate(layoutInflater)
            var dialog= Dialog(this).apply{
                setContentView(dialogBinding.root)
                show()
            }
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.btnsubmit.setOnClickListener {
                if (dialogBinding.etrollno.text?.toString().isNullOrEmpty()) {
                    dialogBinding.etrollno.error = "Enter Your rollno "
                } else if (  dialogBinding.etname.text?.toString().isNullOrEmpty()) {
                    dialogBinding.etname.error = "Enter Your Name "
                } else if (  dialogBinding.etsubject.text?.toString().isNullOrEmpty()) {
                    dialogBinding.etsubject.error = "Enter Your subject "
                } else {
                    studentList.add(
                        Student(
                            dialogBinding. etrollno.text.toString().trim().toInt(),
                            dialogBinding.etname.text.toString(),
                            dialogBinding.etsubject.text.toString()
                        )
                    )
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }
}




