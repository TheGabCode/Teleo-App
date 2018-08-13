package gab.cdi.teleo.main.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.VolleyError
import gab.cdi.teleo.R
import gab.cdi.teleo.main.https.API
import gab.cdi.teleo.main.https.ApiRequest
import gab.cdi.teleo.main.session.Session
import kotlinx.android.synthetic.main.activity_sign_in_via_app.*

class SignInViaApp : AppCompatActivity() {
    var mSession : Session? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_via_app)
        mSession = Session(this)
        initUI()
    }

    private fun initUI(){
        teleo_sign_in_button.setOnClickListener {
            postSignInViaApp()
        }


        sign_up_option_textview.setOnClickListener {
            redirectSignUpViaAppOption()
        }
    }

    private fun redirectSignUpViaAppOption(){
        val signUpIntent = Intent(this,SignUpViaApp::class.java)
        this@SignInViaApp.finish()
        startActivity(signUpIntent)
    }

    private fun postSignInViaApp(){
        val username : String = username_edittext.text.toString().trim()
        val password : String = password_edittext.text.toString().trim()
        val params : HashMap<String,String> = HashMap()
        params.put("username",username)
        params.put("password",password)
        ApiRequest.post(this, API.SIGN_IN,params,
                object : ApiRequest.URLCallback{
                    override fun didURLResponse(response: String) {
                        Log.d("ResponseZZZ",response)
                        mSession?.authorizeLogIn(response)
                        val homeIntent = Intent(this@SignInViaApp,TeleoNavigationActivity::class.java)
                        this@SignInViaApp.finish()
                        startActivity(homeIntent)
                    }
                },
                object : ApiRequest.ErrorCallback{
                    override fun didURLError(error: VolleyError) {

                    }

                })

    }
}
