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
import com.example.hikmatlar.QuoteRv.QuoteAdapter
import com.example.hikmatlar.QuoteRv.QuoteItem
import com.example.hikmatlar.databinding.FragmentQuoteBinding


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


    }

}