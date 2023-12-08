package com.example.hikmatlar

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hikmatlar.Backend.Api.ApiService.ApiClient
import com.example.hikmatlar.Backend.Api.ApiService.ApiService
import com.example.hikmatlar.Backend.Api.ApiService.Quote
import com.example.hikmatlar.Backend.Api.ApiService.Quotes
import com.example.hikmatlar.QuoteRv.QuoteAdapter
import com.example.hikmatlar.databinding.FragmentQuoteBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class QuoteFragment : Fragment() {

    private var _binding : FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv : RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter : QuoteAdapter
    private lateinit var apiService: ApiService



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

        apiService = ApiClient.create()
        val call = apiService.getQuotes()
        call.enqueue(object : Callback<Quotes> {
            override fun onResponse(call: Call<Quotes>, response: Response<Quotes>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    val data = response.body()
                    d("data", data.toString())
                    val listData = data!!.quotes
                      adapter = QuoteAdapter(listData)
                      rv.layoutManager = layoutManager
                      rv.adapter = adapter
                    // Do something with the data
                } else {
                    d("data", response.message().toString())
                    // Handle error response
                    // You can get the error details from response.errorBody()
                }
            }

            override fun onFailure(call: Call<Quotes>, t: Throwable) {
                d("data", t.toString())
                // Handle network errors or other failures
            }
        })

        binding.editTextSearch.doOnTextChanged { text, start, before, count ->
            adapter.filter(text.toString())
            when(adapter.itemCount){
                0 -> binding.ifEmpty.visibility = View.VISIBLE
                else ->  binding.ifEmpty.visibility = View.GONE
            }
        }
    }

}
