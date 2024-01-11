package com.example.hikmatlar

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hikmatlar.Backend.Api.ApiService.ApiClient
import com.example.hikmatlar.Backend.Api.ApiService.ApiService
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
        recreate_res(call)

        binding.editTextSearch.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrBlank()){
                adapter.filter(text.toString())
                when(adapter.itemCount){
                    0 -> binding.ifEmpty.visibility = View.VISIBLE
                    else ->  binding.ifEmpty.visibility = View.GONE
                }
            }
        }
    }

    private fun checkConnectivity(call: Call<Quotes>) {
        val manager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo

        if (null == activeNetwork) {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            val intent = Intent(requireContext(), MainActivity::class.java)
            // set message of alert dialog
            dialogBuilder.setMessage("Internetingiz yoquv ekanligiga ishonch hosil qiling")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Takrorlash", DialogInterface.OnClickListener { dialog, id ->
                    recreate_res(call.clone())
                })

            // negative button text and action
                .setNegativeButton("Bekor qilish", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                    activity?.finish()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Internet yo'q")
            alert.setIcon(R.mipmap.ic_launcher)
            // show alert dialog
            alert.show()
        }
    }

    fun recreate_res(call: Call<Quotes>){
        call.enqueue(object : Callback<Quotes> {
            override fun onResponse(call: Call<Quotes>, response: Response<Quotes>) {
                binding.progressBar.visibility = View.GONE
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
                    Toast.makeText(
                        context, "Xatolik yuz berdi.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Quotes>, t: Throwable) {
                d("data", t.toString())
                checkConnectivity(call.clone())
                // Handle network errors or other failures
            }
        })
    }

}
