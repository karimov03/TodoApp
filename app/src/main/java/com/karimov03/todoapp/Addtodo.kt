package com.karimov03.todoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karimov03.todoapp.Adapters.SpinnerAdapter
import com.karimov03.todoapp.Class.myData
import com.karimov03.todoapp.databinding.ActivityAddtodoBinding

class Addtodo : AppCompatActivity() {
    private val binding by lazy { ActivityAddtodoBinding.inflate(layoutInflater) }
    private lateinit var adapter: SpinnerAdapter
    private var data1list=ArrayList<myData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle("Add todo")


        val gson= Gson()
        val list = listOf<String>("Urgent", "Hard", "Normal", "Low")
        adapter = SpinnerAdapter(list)
        binding.spinner.adapter = adapter


        val sharedPreferences = getSharedPreferences("listArray", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("nb", null)
        val type = object : TypeToken<ArrayList<myData>>() {}.type
        val datalist: ArrayList<myData>? = gson.fromJson(json, type)
        if (datalist.isNullOrEmpty()){
        }
        else{
            data1list=datalist
        }
        Toast.makeText(this, "${data1list.size}", Toast.LENGTH_SHORT).show()

        
        
        
        
        
        binding.btnSave.setOnClickListener {

            if (binding.edtName.text.isNotEmpty()) {
                if (binding.edtDescription.text.isNotEmpty()) {
                    if (binding.edtDate.text.isNotEmpty()) {
                        if (binding.edtDedline.text.isNotEmpty()) {
                            val name = binding.edtName.text.toString().trim()
                            val description = binding.edtDescription.text.toString().trim()
                            val date = binding.edtName.text.toString().trim()
                            val dedline = binding.edtDedline.text.toString().trim()
                            val spinner = binding.spinner.selectedItem.toString()

                            data1list.add(myData(name,description,spinner,date,dedline,"Open"))


                            val sharedPreferences = getSharedPreferences("listArray", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()


                            val arrayListJson = gson.toJson(data1list)
                            editor.putString("nb", arrayListJson)
                            editor.apply()



                            //oynani yopish
                            finish()
                        }
                    }
                }
            }
        }
    }
}


