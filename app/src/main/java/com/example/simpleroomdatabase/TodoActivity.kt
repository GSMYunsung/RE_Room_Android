package com.example.simpleroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleroomdatabase.Room.DataBase
import com.example.simpleroomdatabase.Room.Entity
import com.example.simpleroomdatabase.databinding.ActivityTodoRecyclerviewBinding

class TodoActivity : AppCompatActivity(), TodoAdapter.RowClickListener  {
    private lateinit var binding : ActivityTodoRecyclerviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_recyclerview)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo_recyclerview)


        //Data Base 객체 가져오기!
        val todoDataBase = DataBase.getInstanceDataBase(applicationContext)
        val todolistAll = todoDataBase?.todoDao()?.getall()
        val viewManager = LinearLayoutManager(this)
        val todoAdapter = TodoAdapter(ArrayList(todolistAll),todoDataBase,this@TodoActivity)


        binding.itemViewRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = todoAdapter
        }

        binding.addButton.setOnClickListener {
            if(binding.addButton.text.equals("글 등록하기")) {
                val todo = Entity(0,binding.addEdit.text.toString(),false)
                todoDataBase?.todoDao()?.insert(todo)
                binding.addEdit.setText("")
                todoAdapter.addItem(todo)
            }
            else if(binding.addButton.text.equals("글 수정하기")){
                val todo = Entity(binding.addEdit.getTag(binding.addEdit.id).toString().toInt(),binding.addEdit.text.toString(),false)
                todoDataBase?.todoDao()?.updater(todo)
                binding.addButton.setText("글 등록하기")
                binding.addEdit.setText("")
            }

        }

        binding.deleteAllButton.setOnClickListener {
            todoDataBase?.todoDao()?.deleteAll()
            todoAdapter.deleteAllItem()
        }

        binding.deleteButton.setOnClickListener {
            val todo = todoAdapter.getSelectedItem()
            if (todo != null) {
                todoDataBase?.todoDao()?.delete(todo)
            }
            if (todo != null) {
                todoAdapter.deleteItem(todo)
            }
        }
    }

    override fun onItemClikListener(user: Entity) {
        binding.addButton.setText("글 수정하기")
        binding.addEdit.setText(user.title)
        binding.addEdit.setTag(binding.addEdit.id,user.id)
    }
}