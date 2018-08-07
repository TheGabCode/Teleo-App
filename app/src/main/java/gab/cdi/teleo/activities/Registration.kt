package gab.cdi.teleo.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import gab.cdi.teleo.R
import android.content.Intent

class Registration : AppCompatActivity() {
    private lateinit var signUpButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)
        signUpButton = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp(){
        var signUpViaAppIntent = Intent(this,SignUpViaApp::class.java)
        startActivity(signUpViaAppIntent)
    }
}
