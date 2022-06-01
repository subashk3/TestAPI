package com.example.testapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.R
import com.example.testapi.UserItem

class ApiAdapter(val context: Context, private val userList: List<UserItem>) :
    RecyclerView.Adapter<UserViewHolder>() {

    val TAG = this.javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.addItems(userList[position])
    }

    override fun getItemCount() = userList.size


}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView
    val userId: TextView
    init {
        title = view.findViewById(R.id.title_user_tv)
        userId = view.findViewById(R.id.user_id_tv)
    }
    fun addItems(userItem: UserItem) {
        userId.text = "User Id: "+userItem.id.toString()
        title.text = userItem.title

    }
}