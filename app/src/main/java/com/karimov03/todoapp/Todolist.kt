package com.karimov03.todoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karimov03.todoapp.Adapters.ExampleAdapter
import com.karimov03.todoapp.Class.myData
import com.karimov03.todoapp.databinding.ActivityTodolistBinding

class Todolist : AppCompatActivity() {
    lateinit var map: HashMap<String, ArrayList<String>>
    lateinit var titleList:ArrayList<String>

    lateinit var openArray:ArrayList<String>
    lateinit var developmentArray:ArrayList<String>
    lateinit var uploadingArray:ArrayList<String>
    lateinit var rejectArray:ArrayList<String>
    lateinit var closedArray:ArrayList<String>
    private val binding by lazy { ActivityTodolistBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setTitle("To do list")

        binding.exp.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val intent = Intent(this, Aboutlist::class.java)
            intent.putExtra("name", map[titleList[groupPosition]]?.get(childPosition))
            startActivity(intent)
            return@setOnChildClickListener true
        }


    }
    private fun keshdanArrayga() {
         map = HashMap()
         titleList = ArrayList()
        titleList.add("Open")
        titleList.add("Development")
        titleList.add("Uploading")
        titleList.add("Reject")
        titleList.add("Close")

        openArray = ArrayList()
        developmentArray = ArrayList()
        uploadingArray = ArrayList()
        rejectArray = ArrayList()
        closedArray = ArrayList()

        val gson= Gson()
        var planArray = ArrayList<myData>()
        val sharedPreferences = getSharedPreferences("listArray", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("nb", null)
        val type = object : TypeToken<ArrayList<myData>>() {}.type
        val datalist: ArrayList<myData>? = gson.fromJson(json, type)
        if (datalist.isNullOrEmpty()){
        }
        else{
            planArray=datalist
        }
        for (todoPlan in planArray) {

            when(todoPlan.parent)
            {
                "Open"->openArray.add(todoPlan.name)
                "Development"->developmentArray.add(todoPlan.name)
                "Uploading"->uploadingArray.add(todoPlan.name)
                "Reject"->rejectArray.add(todoPlan.name)
                "Close"->closedArray.add(todoPlan.name)
            }
        }

        map[titleList[0]] = openArray
        map[titleList[1]] = developmentArray
        map[titleList[2]] = uploadingArray
        map[titleList[3]] = rejectArray
        map[titleList[4]] = closedArray

    }
    override fun onStart() {
        super.onStart()
        keshdanArrayga()
        val spinerAdapter1 = ExampleAdapter(titleList, map)
        binding.exp.setAdapter(spinerAdapter1)

    }
}