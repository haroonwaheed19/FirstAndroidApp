package com.example.greetingapp

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
        var enteredName = ""

        submitButton.setOnClickListener {
            enteredName = userName.text.toString()
            if(enteredName == ""){
                val message = "Welcome to Android!"
                greetingText.text = message
                Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if(male.isChecked)
                {
                    val message = "Welcome! Mr $enteredName!"
                    greetingText.text = message
                    userName.text = ""
                    male.isChecked = false
                    female.isChecked = false
                }
                else if(female.isChecked)
                {
                    val message = "Welcome! Ms $enteredName!"
                    greetingText.text = message
                    userName.text = ""
                    male.isChecked = false
                    female.isChecked = false
                }
                else
                {
                    val message = "Please Select the Gender first"
                    greetingText.text = message
                    Toast.makeText(this@MainActivity, "Please Select the Gender first", Toast.LENGTH_SHORT).show()
                    userName.text = ""
                    male.isChecked = false
                    female.isChecked = false
                }

            }
        }
    }
}
