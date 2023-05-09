package com.example.jicker_project.fe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.jicker_project.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUIActions()
    }

    private fun initUIActions() {
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val textViewBackSymbol = findViewById<TextView>(R.id.login_back_symbol)
        val textViewBackText = findViewById<TextView>(R.id.login_back_text)
        loginButton.setOnClickListener{

        }
        textViewBackSymbol.setOnClickListener{
            val intentOne = Intent(this, RegisterActivity::class.java)
            startActivity(intentOne)
        }
        textViewBackText.setOnClickListener{
            val intentOne = Intent(this, RegisterActivity::class.java)
            startActivity(intentOne)
        }

    }
}