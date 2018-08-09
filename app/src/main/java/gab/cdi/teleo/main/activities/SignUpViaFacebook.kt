package gab.cdi.teleo.main.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.facebook.login.LoginManager
import gab.cdi.teleo.R
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_sign_up_via_facebook.*
import org.json.JSONObject
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
                        val facebookProfileToken = loginResult.accessToken
                        val graphRequest = GraphRequest.newMeRequest(facebookProfileToken,
                                GraphRequest.GraphJSONObjectCallback { `object`, response ->

                                    Log.d("FACEBOOK ",`object`.toString())
                                })
                        val parameters = Bundle()
                        parameters.putString("fields", "id,name,email")
                        graphRequest.parameters = parameters
                        graphRequest.executeAsync()
                    }

                    override fun onCancel() {
                        Toast.makeText(this@SignUpViaFacebook, "Login Cancelled", Toast.LENGTH_LONG).show( )
                    }

                    override fun onError(exception: FacebookException) {
                        Toast.makeText(this@SignUpViaFacebook, exception.message, Toast.LENGTH_LONG).show()
                    }
                })
        initUI()


    }

    private fun initUI(){
        facebookLoginButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this@SignUpViaFacebook, Arrays.asList("public_profile", "user_friends","email","user_status"))
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         callbackManager.onActivityResult(requestCode,resultCode,data)
    }
}
