package gab.cdi.teleo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import gab.cdi.teleo.R
import java.util.regex.Pattern

class SignUpViaApp : AppCompatActivity() {
    private lateinit var fullnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var submitSignUp: Button
    private lateinit var termsConditionsCheckbox: CheckBox
    private lateinit var updatesCheckbox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_via_app)
        setupFields()

    }

    private fun setupFields() {
        fullnameEditText = findViewById(R.id.fullnameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        submitSignUp = findViewById(R.id.finalizeSignUpButton)
        termsConditionsCheckbox = findViewById(R.id.termsCheckbox)
        updatesCheckbox = findViewById(R.id.updatesCheckbox)
        termsConditionsCheckbox.setTypeface(ResourcesCompat.getFont(applicationContext, R.font.roboto_light))
        updatesCheckbox.setTypeface(ResourcesCompat.getFont(applicationContext, R.font.roboto_light))

        submitSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val fullname: String = fullnameEditText.text.toString().trim()
        val username: String = usernameEditText.text.toString().trim()
        val phoneNumber: String = phoneNumberEditText.text.toString().trim()
        val email: String = emailEditText.text.toString().trim()

        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(applicationContext, "Please fill in full name", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(applicationContext, "Please fill in username", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(applicationContext, "Please fill in phone number", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(email) || !email.isEmailValid()) {
            Toast.makeText(applicationContext, "Please fill in email with appropriate format", Toast.LENGTH_SHORT).show()
            return
        }
        if (!termsConditionsCheckbox.isChecked) {
            Toast.makeText(applicationContext, "You have not yet agreed to the terms and conditions", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
    }



    fun String.isEmailValid(): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
}
