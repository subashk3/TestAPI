package com.example.testapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.adapter.ApiAdapter
import com.example.testapi.api.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private lateinit var myAdapter: ApiAdapter
    // latent var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private var tempUserList = ArrayList<UserItem>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        recyclerView.setHasFixedSize(true)

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
        myAdapter = ApiAdapter(this, tempUserList)

        CoroutineScope(Dispatchers.IO).launch {
            service.getDetails().execute().body()?.let {
                tempUserList.clear()
                tempUserList.addAll(it)
            }
            withContext(Dispatchers.Main) {
                if (recyclerView.adapter != null) {
                    (recyclerView.adapter as ApiAdapter).notifyDataSetChanged()
                } else {
                    recyclerView.adapter = myAdapter
                }
            }

        }

    }


}


