package com.study.sender

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Contact(val name: String, val phone: String, val email: String) : Parcelable

class MainActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var nameField: EditText
    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize fields
        nameField = findViewById(R.id.nameInput)
        emailField = findViewById(R.id.emailInput)
        phoneField = findViewById(R.id.phoneInput)

        val sendButton: Button = findViewById(R.id.sendButton)
        val shareTextButton: Button = findViewById(R.id.shareTextButton)

        sendButton.setOnClickListener(this)
        shareTextButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.sendButton -> {
                val contact = Contact(
                    name = nameField.text.toString(),
                    phone = phoneField.text.toString(),
                    email = emailField.text.toString()
                )

                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("contact", contact)  // Passing Contact object
                }
                startActivity(intent)
            }

            R.id.shareTextButton -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hello Humber!")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share Via"))
            }
        }
    }
}