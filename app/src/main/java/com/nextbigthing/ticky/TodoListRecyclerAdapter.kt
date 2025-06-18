package com.nextbigthing.ticky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nextbigthing.ticky.room.AppModel

class TodoListRecyclerAdapter(
    private val list: MutableList<AppModel>,
    private val deleteUser: DeleteUser,
    private val onCheckChanged: () -> Unit // new callback
) : RecyclerView.Adapter<TodoListRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
        val deleteImageView: ImageView = view.findViewById(R.id.deleteImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_to_do_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.checkBox.text = item.task
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = item.isChecked == true

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            onCheckChanged() // notify activity
        }

        holder.deleteImageView.setOnClickListener {
            deleteUser.deleteUserFromDb(item)
        }
    }

    fun getCheckedItemCount(): Int = list.count { it.isChecked == true }

    fun getUncheckedItemCount(): Int = list.count { it.isChecked != true }
}
