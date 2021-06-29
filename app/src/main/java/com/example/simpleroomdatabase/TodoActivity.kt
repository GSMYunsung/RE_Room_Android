package com.example.simpleroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleroomdatabase.Room.DataBase
import com.example.simpleroomdatabase.Room.Entity
import com.example.simpleroomdatabase.databinding.ActivityTodoRecyclerviewBinding

class TodoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTodoRecyclerviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_recyclerview)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo_recyclerview)


        //Data Base 객체 가져오기!
        val todoDataBase = DataBase.getInstanceDataBase(applicationContext)
        val todolistAll = todoDataBase?.todoDao()?.getall()
        val viewManager = LinearLayoutManager(this)
        val todoAdapter = TodoAdapter(ArrayList(todolistAll),todoDataBase)


        binding.itemViewRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = todoAdapter
        }

        binding.addButton.setOnClickListener {
            if(binding.addEdit.text.toString().isNotEmpty()){
                val todo = Entity(binding.addEdit.text.toString(),false)
                todoDataBase?.todoDao()?.insert(todo)

                binding.addEdit.setText("")

                todoAdapter.addItem(todo)
            }
        }

        binding.deleteAllButton.setOnClickListener {
            todoDataBase?.todoDao()?.deleteAll()

            todoAdapter.deleteAllItem()
        }

        binding.deleteButton.setOnClickListener {
            val todo = todoAdapter.getSelectedItem()
            todo?.let { it1 -> todoDataBase?.todoDao()?.delete(it1) }
            todo?.let { it1 -> todoAdapter.deleteItem(it1) }
        }
    }
}