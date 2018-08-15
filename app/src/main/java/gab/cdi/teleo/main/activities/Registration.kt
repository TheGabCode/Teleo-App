package gab.cdi.teleo.main.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import gab.cdi.teleo.R
import android.content.Intent
import android.util.Log
import gab.cdi.teleo.main.session.Session


class Registration : AppCompatActivity() {
    private lateinit var signUpButton : Button
    private lateinit var signUpButtonFb : Button
    private lateinit var mSession : Session
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mSession = Session(this)
        Log.d("TagToken",mSession.token())
        if(mSession.isUserLoggedIn() == true){
            finish()
            startActivity(Intent(this,TeleoNavigationActivity::class.java))
            return
        }
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
        finish()
        if(viaApp){
            startActivity(Intent(this, SignInViaApp::class.java))
            return
        }
        else{
            startActivity(Intent(this, SignUpViaFacebook::class.java))
            return
        }
    }
}
