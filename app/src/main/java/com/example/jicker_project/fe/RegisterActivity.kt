package com.example.jicker_project.fe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.jicker_project.R
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initUIActions()
    }


    private fun openDialog(int: Int) {
        if (int == 1) {
            val diaName = EmptyInformationDialogError();
            diaName.show(supportFragmentManager, "test")
        } else {
            val diaName = PasswordMatchingDialogError();
            diaName.show(supportFragmentManager, "test")
        }
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
            } else if (!passOneText.text.toString().equals(passTwoText.text.toString())) {
                openDialog(2)
            }
        }
        loginPageButton.setOnClickListener{
            val intentOne = Intent(this, LoginActivity::class.java)
            startActivity(intentOne)
        }

    }
}