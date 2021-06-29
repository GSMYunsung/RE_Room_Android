package com.example.simpleroomdatabase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleroomdatabase.Room.DataBase
import com.example.simpleroomdatabase.Room.Entity
import org.w3c.dom.Text

class TodoAdapter(private val todoList: ArrayList<Entity>,private val todoDataBase: DataBase?) : RecyclerView.Adapter<TodoAdapter.MyHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.MyHolder {
        var inflate = LayoutInflater.from(parent.context).inflate(R.layout.activity_todo_item,parent,false)
        return MyHolder(inflate)
    }

    override fun onBindViewHolder(holder: TodoAdapter.MyHolder, position: Int) {
        if (selectedPosition == position) holder.itemView.setBackgroundColor(Color.CYAN)
        else holder.itemView.setBackgroundColor(Color.TRANSPARENT)

        holder.title.text = todoList[position].title

        holder.todobutton.setOnClickListener {
            if (holder.todobutton.text.toString() == "High") holder.todobutton.setText("Low")
            else if (holder.todobutton.text.toString() == "Low") holder.todobutton.setText("High")

            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
    }


    fun addItem(todo: Entity) {
        todoList.add(todo)
        notifyDataSetChanged()
    }

    fun getSelectedItem(): Entity? =
        if (selectedPosition > -1) todoList[selectedPosition]
        else null

    fun deleteItem(todo: Entity) {
        selectedPosition = -1
        todoList.remove(todo)
        notifyDataSetChanged()
    }

    fun deleteAllItem() {
        selectedPosition = -1

        todoList.clear()
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return todoList.size
    }

      //커스텀 뷰홀더
    class MyHolder(view : View) : RecyclerView.ViewHolder(view){
        var title = view.findViewById<TextView>(R.id.todo_text)
        var todobutton = view.findViewById<Button>(R.id.low_high_button)
    }
}