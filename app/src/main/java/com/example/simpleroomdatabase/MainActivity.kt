    package com.example.simpleroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleroomdatabase.Room.DataBase
import com.example.simpleroomdatabase.Room.Entity
import com.example.simpleroomdatabase.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoDataBase = DataBase.getInstanceDataBase(applicationContext)
        val databaseall = todoDataBase?.todoDao()?.getall()

        binding =  DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewManager = LinearLayoutManager(this)
        val todoAdapter = TodoMainAdapter(ArrayList(databaseall),todoDataBase)

        binding.mainRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = todoAdapter
        }

        binding.mainButton.setOnClickListener {
            startActivity(Intent(this,TodoActivity::class.java))
        }
    }
    }