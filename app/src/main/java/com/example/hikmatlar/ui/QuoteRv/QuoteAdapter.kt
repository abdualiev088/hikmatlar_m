package com.example.hikmatlar.ui.QuoteRv

import android.text.TextUtils
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.hikmatlar.Backend.Api.ApiService.Quote
import com.example.hikmatlar.R
import java.util.Locale


class QuoteAdapter(private var quotes: List<Quote>)
    : RecyclerView.Adapter<QuoteAdapter.ViewHolder>(){

    private val originalList = quotes
    private val filteredList = ArrayList(originalList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quote_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = filteredList.get(position)
        holder.title.text = quote.text
        holder.quote.text = quote.translation
        holder.author.text = quote.author

//        val currentItem = quotes?.get(position)
//        if (currentItem != null) {
//            holder.bind(currentItem)
//        }
    }

    override fun getItemCount() = filteredList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val title = view.findViewById<TextView>(R.id.arabicText)
        val quote = view.findViewById<TextView>(R.id.quoteText)
        val author = view.findViewById<TextView>(R.id.authorText)

        val bookmarkImageCard = itemView.findViewById<CardView>(R.id.save)
        val bookmarkImageView = itemView.findViewById<ImageView>(R.id.saveImg)

//        fun bind(item: Quote?) {
//            bookmarkImageView.setImageResource(
//                if (item.isBookmarked) R.drawable.baseline_bookmark
//                else R.drawable.not_filled_bookmark
//            )
//
//            bookmarkImageCard.setOnClickListener {
//                item.isBookmarked = !item.isBookmarked
//                bookmarkImageView.setImageResource(
//                    if (item.isBookmarked) R.drawable.baseline_bookmark
//                    else R.drawable.not_filled_bookmark
//                )
//                if (item.isBookmarked){
//                    val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.bookmark_animation)
//                    bookmarkImageView.startAnimation(animation)
//                }
//            }
//        }
    }

    fun filter(query: String) {
        filteredList.clear()
        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(originalList)
        } else {
            for (quote in originalList) {
                if (quote.translation.lowercase()
                        .contains(query.lowercase())
                    || quote.author.lowercase()
                        .contains(query.lowercase())
                    || quote.text.lowercase()
                        .contains(query.lowercase())
                ) {
                    filteredList.add(quote)
                }
            }
        }
        notifyDataSetChanged()
    }


}