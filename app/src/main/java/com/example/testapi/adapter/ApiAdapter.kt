package com.example.testapi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.R
import com.example.testapi.UserItem
import com.example.testapi.databinding.DetailListBinding

class ApiAdapter(private val context: Context, private val userList: List<UserItem>) :
    RecyclerView.Adapter<UserViewHolder>() {

    //val TAG = this.javaClass.simpleName


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       // val view = LayoutInflater.from(context).inflate(R.layout.detail_list, parent, false)
        val binding = DetailListBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.addItems(userList[position])

    }

    override fun getItemCount() = userList.size

}

class UserViewHolder(binding: DetailListBinding) : RecyclerView.ViewHolder(binding.root) {

    private val title: TextView
    private val userId: TextView

    init {
        title = binding.titleUserTv
        userId = binding.userIdTv
    }

    @SuppressLint("SetTextI18n")
    fun addItems(userItem: UserItem) {
        userId.text = "User Id: " + userItem.id.toString()
        title.text = userItem.title

    }
}