package com.example.greetingapp

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val greetingText = findViewById<TextView>(R.id.tvGreetings)
        val userName = findViewById<TextView>(R.id.etName)
        val submitButton = findViewById<Button>(R.id.btSubmit)
        val male = findViewById<RadioButton>(R.id.rdBtn1)
        val female = findViewById<RadioButton>(R.id.rdbtn2)
        val rdGroup = findViewById<RadioGroup>(R.id.rdGroup)

        submitButton.setOnClickListener {
            val enteredName = userName.text.toString().trim()

            if (enteredName.isEmpty()) {
                greetingText.text = "Welcome to Android!"
                Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                when {
                    male.isChecked -> {
                        greetingText.text = "Welcome! Mr $enteredName!"
                        // Clear fields only if the input is valid
                        rdGroup.clearCheck()
                        userName.text = ""
                    }
                    female.isChecked -> {
                        greetingText.text = "Welcome! Ms $enteredName!"
                        // Clear fields only if the input is valid
                        rdGroup.clearCheck()
                        userName.text = ""
                    }
                    else -> {
                        greetingText.text = "Hello $enteredName, please select your gender"
                        Toast.makeText(this@MainActivity, "Please Select the Gender", Toast.LENGTH_SHORT).show()
                        // Do not clear the username field
                    }
                }
            }
        }
    }
}

