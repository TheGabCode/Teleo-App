package gab.cdi.teleo.main.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import gab.cdi.teleo.R
import android.widget.Toast
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import java.util.*


class SignUpViaFacebook : AppCompatActivity() {
    private lateinit var callbackManager : CallbackManager
    private lateinit var fbLoginButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_via_facebook)

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("Success", "Success fb login")
                        Toast.makeText(this@SignUpViaFacebook,"Success FB",Toast.LENGTH_SHORT).show()
                    }

                    override fun onCancel() {
                        Toast.makeText(this@SignUpViaFacebook, "Login Cancelled", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(exception: FacebookException) {
                        Toast.makeText(this@SignUpViaFacebook, exception.message, Toast.LENGTH_LONG).show()
                    }
                })
        initUI()


    }

    private fun initUI(){
        fbLoginButton = findViewById(R.id.facebookLoginButton)
        fbLoginButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this@SignUpViaFacebook, Arrays.asList("public_profile", "user_friends"))
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         callbackManager.onActivityResult(requestCode,resultCode,data)
    }
}
