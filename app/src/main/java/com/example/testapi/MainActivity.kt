package com.example.testapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapi.adapter.ApiAdapter
import com.example.testapi.api.ApiInterface
import com.example.testapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"
    lateinit var myAdapter: ApiAdapter
    lateinit var binding: ActivityMainBinding
     val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        val recyclerView = binding.recyclerView

//        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiInterface::class.java)

/*        service.getDetails().enqueue(object : Callback<List<UserItem>> {
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

        })*/
        service
            .getDetails()
            .enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                if (response.isSuccessful){
                    Log.d(TAG,"API response"+ response.body().toString())
                    myAdapter = ApiAdapter(this@MainActivity, response.body()!!)
                    binding.recyclerView.adapter = myAdapter
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    myAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}


