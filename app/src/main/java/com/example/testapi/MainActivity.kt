package com.example.testapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapi.adapter.ApiAdapter
import com.example.testapi.api.ApiInterface
import com.example.testapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: ApiAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()

        val service = retrofit.create(ApiInterface::class.java)

        service.getDetails().enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                if (response.isSuccessful) {
                    myAdapter = ApiAdapter(this@MainActivity, response.body()!!)
                    recyclerView.adapter =myAdapter
                }
            }
            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                d("My test", t.toString())
            }

        })


    }
}


