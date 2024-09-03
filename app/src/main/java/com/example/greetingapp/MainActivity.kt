package com.example.greetingapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var greetingText : TextView
    private lateinit var userName :TextView
    private lateinit var submitButton : Button
    private lateinit var male : RadioButton
    private lateinit var female : RadioButton
    private lateinit var rdGroup : RadioGroup
    private lateinit var sf : SharedPreferences
    private lateinit var  editor : SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        greetingText = findViewById(R.id.tvGreetings)
        userName = findViewById(R.id.etName)
        submitButton = findViewById(R.id.btSubmit)
        male = findViewById(R.id.rdBtn1)
        female = findViewById(R.id.rdbtn2)
        rdGroup = findViewById(R.id.rdGroup)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()

        submitButton.setOnClickListener {
            val enteredName = userName.text.toString().trim()

            if (enteredName.isEmpty()) {
                val greet = "Welcome to Android!"
                greetingText.text = greet
                Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                when {
                    male.isChecked -> {
                        val text = "Welcome! Mr. $enteredName!"
                        greetingText.text = text
                        // Clear fields only if the input is valid
                        rdGroup.clearCheck()
                        userName.text = ""
                    }
                    female.isChecked -> {
                        val text = "Welcome! Ms $enteredName!"
                        greetingText.text = text
                        // Clear fields only if the input is valid
                        rdGroup.clearCheck()
                        userName.text = ""
                    }
                    else -> {
                        val text = "Hello $enteredName, please select your gender"
                        greetingText.text = text
                        Toast.makeText(this@MainActivity, "Please Select the Gender", Toast.LENGTH_SHORT).show()
                        // Do not clear the username field
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val enteredName = userName.text.toString().trim()
        editor.apply {
            putString("sf_name", enteredName)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val savedName = sf.getString("sf_name", null)
        userName.text = savedName
    }
}
