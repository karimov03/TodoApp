package com.karimov03.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karimov03.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.addtodo.setOnClickListener {
            val intent = Intent(this@MainActivity,Addtodo::class.java)
            startActivity(intent)
        }

        binding.todolist.setOnClickListener {
            val intent = Intent(this@MainActivity,Todolist::class.java)
            startActivity(intent)
        }
    }
}