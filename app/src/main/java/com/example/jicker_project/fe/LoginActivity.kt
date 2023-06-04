package com.example.jicker_project.fe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.jicker_project.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUIActions()
    }

    private fun post(url: String, json: String, callback: Callback) {
        val json1 = "application/json; charset=utf-8".toMediaTypeOrNull()
        val client = OkHttpClient()
        val body = json.toRequestBody(json1)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        client.newCall(request).enqueue(callback)
    }

    private fun initUIActions() {
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val textViewBackSymbol = findViewById<TextView>(R.id.login_back_symbol)
        val textViewBackText = findViewById<TextView>(R.id.login_back_text)
        val textViewUsername = findViewById<TextView>(R.id.login_username)
        val textViewPassword = findViewById<TextView>(R.id.login_pass_1)
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
        loginButton.setOnClickListener{
            println("here")
            val json = """
                            {
                              "username": "${textViewUsername.text}",
                              "password": "${textViewPassword.text}"
                            }
                            """.trimIndent()
            println(json)
            val url = "http://10.0.2.2:8000/api/accounts/login/"
            post(url, json, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    println("Response Code: ${response.code}")
                    if (!response.isSuccessful) {
                        println("Unexpected code $response")
                        val diaName = WrongPasswordUsername()
                        diaName.show(supportFragmentManager, "test")
                    } else {
                        println("are we here or above?")
                        println(response.body?.string())
                    }
                }
            })
        }

    }
}