package com.example.jicker_project.fe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.jicker_project.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initUIActions()
    }


    private fun openDialog(int: Int) {
        when (int) {
            1 -> {
                val diaName = EmptyInformationDialogError()
                diaName.show(supportFragmentManager, "test")
            }
            2 -> {
                val diaName = PasswordMatchingDialogError()
                diaName.show(supportFragmentManager, "test")
            }
            3 -> {
                val diaName = PasswordLengthError()
                diaName.show(supportFragmentManager, "test")
            }
            4 -> {
                val diaName = UsernameLengthError()
                diaName.show(supportFragmentManager, "test")
            }
            5 -> {
                val diaName = UniqueUsernameError()
                diaName.show(supportFragmentManager, "test")
            }
        }
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
        val signUpBtn = findViewById<Button>(R.id.btnSignUp)
        val loginPageButton = findViewById<TextView>(R.id.textView_login_page)
        val usernameText = findViewById<EditText>(R.id.username)
        val passOneText = findViewById<EditText>(R.id.pass_1)
        val passTwoText = findViewById<EditText>(R.id.pass_2)
        signUpBtn.setOnClickListener{
            println(usernameText.text.toString())
            if (usernameText.text.toString().isEmpty() || passOneText.text.toString().isEmpty()
                    || passTwoText.text.toString().isEmpty()) {
                println()
                println("here")
                println()
                openDialog(1)
            } else if (passOneText.text.toString() != passTwoText.text.toString()) {
                openDialog(2)
            } else if (passOneText.text.toString().length <= 8) {
                openDialog(3)
            } else if (usernameText.text.toString().length <= 4 || usernameText.text.toString().length >= 49) {
                openDialog(4)
            } else {
                println("here")
                val json = """
                            {
                              "username": "${usernameText.text}",
                              "password": "${passOneText.text}"
                            }
                            """.trimIndent()
                println(json)
                val url = "http://10.0.2.2:8000/api/accounts/register/"
                post(url, json, object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        println("Response Code: ${response.code}")
                        if (!response.isSuccessful) {
                            println("Unexpected code $response")
                            openDialog(5)
                        } else {
                            println("are we here or above?")
                            println(response.body?.string())
                            callLogin()
                        }
                    }
                })
            }
        }
        loginPageButton.setOnClickListener{
            val intentOne = Intent(this, LoginActivity::class.java)
            startActivity(intentOne)
        }

    }

    private fun callLogin(){
        val intentOne = Intent(this, LoginActivity::class.java)
        startActivity(intentOne)
    }
}