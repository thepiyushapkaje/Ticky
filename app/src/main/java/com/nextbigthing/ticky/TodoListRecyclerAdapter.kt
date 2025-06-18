package com.nextbigthing.ticky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nextbigthing.ticky.room.AppModel

class TodoListRecyclerAdapter(private val list:List<AppModel>, private val deleteUser: DeleteUser) : RecyclerView.Adapter<TodoListRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        internal val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        internal val deleteImageView = view.findViewById<ImageView>(R.id.deleteImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_view_to_do_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.text = list[position].task
        holder.deleteImageView.setOnClickListener {
            deleteUser.deleteUserFromDb(list[position])
        }
    }
}