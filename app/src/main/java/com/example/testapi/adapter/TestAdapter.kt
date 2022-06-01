package com.example.testapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.R
import com.example.testapi.UserItem

class TestAdapter(val context: Context, val userList: List<UserItem>) :
    RecyclerView.Adapter<TestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_list, parent, false)
        return TestViewHolder(view)
    }
    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {

        holder.userId.text = userList[position].id.toString()
        holder.title.text = userList[position].title
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var userId: TextView
    val title: TextView
    init {
        userId = view.findViewById(R.id.user_id_tv)
        title = view.findViewById(R.id.title_user_tv)
    }

}
