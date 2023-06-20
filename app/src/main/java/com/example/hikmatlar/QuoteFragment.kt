package com.example.hikmatlar

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hikmatlar.Backend.API_ROUTE
import com.example.hikmatlar.Backend.Api.ApiService.ApiService
import com.example.hikmatlar.Backend.Quotes
import com.example.hikmatlar.Backend.QuotesItem
import com.example.hikmatlar.QuoteRv.QuoteAdapter
import com.example.hikmatlar.QuoteRv.QuoteItem
import com.example.hikmatlar.databinding.FragmentQuoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class QuoteFragment : Fragment() {

    private var _binding : FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv : RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter : QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = binding.rv
        layoutManager =  LinearLayoutManager(binding.root.context)

        adapter = QuoteAdapter(listOf(
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham"),
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham"),
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham"),
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham"),
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham"),
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham"),
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham"),
            QuoteItem("لا يهم كم أنت بطيئ طالما أنك لن تتوقف", "It does not matter how slowly you go as long as you do not stop.", "Imam Xisham")
        ),)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
        getData()

    }

    fun getData(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_ROUTE.API_BASE_URL)
            .build()
            .create(ApiService::class.java)


        val apiService = retrofit.getQuotes()
        apiService.enqueue(object : Callback<List<QuotesItem>>{
            override fun onResponse(
                call: Call<List<QuotesItem>>, response: Response<List<QuotesItem>>) {
                if (response.isSuccessful) {
                    val quoteItems = response.body()
                    d("mytag", "IT IS WORKING")
                    d("mytag", "$quoteItems")
                    // Process the quoteItems here
                } else {
                    d("mytag", "Error: ${response.body()}")
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<QuotesItem>?>, t: Throwable) {
                // Handle network or other errors
                d("mytag", "${t.message}")
            }
        })
    }

}
