package com.example.simpleroomdatabase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleroomdatabase.Room.DataBase
import com.example.simpleroomdatabase.Room.Entity
import org.w3c.dom.Text

class TodoMainAdapter(private val todoList: ArrayList<Entity>,private val todoDataBase: DataBase?) : RecyclerView.Adapter<TodoMainAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoMainAdapter.MyHolder {
        var inflate = LayoutInflater.from(parent.context).inflate(R.layout.activity_todo_item,parent,false)
        return MyHolder(inflate)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    //커스텀 뷰홀더
    class MyHolder(view : View) : RecyclerView.ViewHolder(view){
        var title = view.findViewById<TextView>(R.id.todo_text)
        var todobutton = view.findViewById<Button>(R.id.low_high_button)
    }

    override fun onBindViewHolder(holder: TodoMainAdapter.MyHolder, position: Int) {
        holder.title.text = todoList[position].title
    }
}