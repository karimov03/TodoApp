package com.karimov03.todoapp


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karimov03.todoapp.Class.myData
import com.karimov03.todoapp.databinding.ActivityAboutlistBinding

class Aboutlist : AppCompatActivity() {
    private val binding by lazy { ActivityAboutlistBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val name=intent.getStringExtra("name")
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
        var index = -1
        var plan1 = myData()


        for (plan in planArray){

            if (plan.name== name.toString()){
                index = planArray.indexOf(plan)
                plan1 = plan

                supportActionBar?.setTitle(plan.name)
                binding.apply {
                    description.text=plan.description
                    spinner.text=plan.spinner
                    date.text=plan.date
                    dedline.text=plan.dedline
                    when(spinner.text){
                        "Urgent"->image.setImageResource(R.drawable.ic_flag1)
                        "Hard"->image.setImageResource(R.drawable.ic_flag2)
                        "Normal"->image.setImageResource(R.drawable.ic_flag3)
                        "Low"->image.setImageResource(R.drawable.ic_flag4)
                    }

                    when(plan.parent){
                        "Open" -> binding.rb1.isChecked = true
                        "Development" -> binding.rb2.isChecked = true
                        "Uploading" -> binding.rb3.isChecked = true
                        "Reject" -> binding.rb4.isChecked = true
                        "Close" -> binding.rb5.isChecked = true
                    }

                }
                break
            }
        }
        binding.btnOk.setOnClickListener {

            var rb=""
            if (binding.rb1.isChecked) rb="Open"
            if (binding.rb2.isChecked) rb="Development"
            if (binding.rb3.isChecked) rb="Uploading"
            if (binding.rb4.isChecked) rb="Reject"
            if (binding.rb5.isChecked) rb="Close"

            plan1.parent = rb
            planArray[index] = plan1
            val sharedPreferences = getSharedPreferences("listArray", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()


            val arrayListJson = gson.toJson(planArray)
            editor.putString("nb", arrayListJson)
            editor.apply()
            finish()
        }




















    }}