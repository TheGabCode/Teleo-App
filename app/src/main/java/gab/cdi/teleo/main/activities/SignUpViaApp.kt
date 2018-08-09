package gab.cdi.teleo.main.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.android.volley.VolleyError
import com.dealoka.sales.models.ModelCreator
import gab.cdi.teleo.R
import gab.cdi.teleo.main.https.API
import gab.cdi.teleo.main.https.ApiRequest
import gab.cdi.teleo.main.session.Session
import org.json.JSONObject
import java.util.regex.Pattern

class SignUpViaApp : AppCompatActivity() {
    private lateinit var fullnameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var submitSignUp: Button
    private lateinit var termsConditionsCheckbox: CheckBox
    private lateinit var updatesCheckbox: CheckBox


    var mSession : Session? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up_via_app)

        mSession = Session(this)

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
        termsConditionsCheckbox.typeface = ResourcesCompat.getFont(applicationContext, R.font.roboto_light)
        updatesCheckbox.typeface = ResourcesCompat.getFont(applicationContext, R.font.roboto_light)

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

        postSignUp()

    }

    private fun postSignUp(){
        val params : HashMap<String,String> = HashMap()
        params.put("username",usernameEditText.text.toString().trim())
        params.put("password","111")
        params.put("firstName","LLoyd")
        params.put("lastName","Verastig!!!")
        params.put("addressLine1","Calauan")
        params.put("addressLine2","Los Banos")
        params.put("addressProvince","Laguna")
        params.put("addressCity","Pasay")
        params.put("contactNumber",phoneNumberEditText.text.toString().trim())
        params.put("emailAddress",emailEditText.text.toString().trim())

        ApiRequest.post(this, API.SIGN_UP,params,
                object : ApiRequest.URLCallback {
                    override fun didURLResponse(response: String) {
                        Log.d("Response ",response + " " + params.toString())
                        val json : Boolean = JSONObject(response).getBoolean("success")
                        if(json){
                            mSession?.authorize(response)
                            Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignUpViaApp,LandingActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                        }

                    }

                },
                object : ApiRequest.ErrorCallback{
                    override fun didURLError(error: VolleyError) {
                        Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
                        Log.d("Error ", error.toString())
                    }

                })

        var sharedPrefs = getSharedPreferences(mSession?.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        Log.d("Token ",sharedPrefs.getString(mSession?.TOKEN,""))
    }


    private fun String.isEmailValid(): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
}
