package com.example.testapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.R
import com.example.testapi.UserItem

class ApiAdapter(private val context: Context, private val userList: List<UserItem>) :
    RecyclerView.Adapter<ApiAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val userId: TextView

        init {
            title = view.findViewById(R.id.title_user)
            userId = view.findViewById(R.id.user_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userId.text = userList[position].id.toString()
        holder.title.text = userList[position].title
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}