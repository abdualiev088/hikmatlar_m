package com.example.hikmatlar

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hikmatlar.databinding.ActivityAuthorBinding

class AuthorActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAuthorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allQuotes.setOnClickListener {
            Intent(this@AuthorActivity, MainActivity::class.java).apply {
                startActivity(this)
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
            finish()
    }

}