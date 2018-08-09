package gab.cdi.teleo.main.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import gab.cdi.teleo.R
import android.content.Intent


class Registration : AppCompatActivity() {
    private lateinit var signUpButton : Button
    private lateinit var signUpButtonFb : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity_2)
        signUpButton = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            signUp(true)
        }

        signUpButtonFb = findViewById(R.id.signUpButtonFacebook)
        signUpButtonFb.setOnClickListener {
            signUp(false)
        }

    }

    private fun signUp(viaApp : Boolean){
        if(viaApp){
            startActivity(Intent(this, SignUpViaApp::class.java))
            return
        }
        else{
            startActivity(Intent(this, SignUpViaFacebook::class.java))
            return
        }
    }
}
