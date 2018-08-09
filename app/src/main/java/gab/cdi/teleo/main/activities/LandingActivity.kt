package gab.cdi.teleo.main.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.VolleyError
import gab.cdi.teleo.R
import gab.cdi.teleo.main.https.API
import gab.cdi.teleo.main.https.ApiRequest
import gab.cdi.teleo.main.models.TeleoUser
import gab.cdi.teleo.main.view_models.TeleoUserViewModel
import org.json.JSONArray
import org.json.JSONObject

class LandingActivity : AppCompatActivity() {

    private lateinit var fullnameTextView : TextView
    private lateinit var usernameTextView : TextView
    private lateinit var emailTextView : TextView
    private lateinit var phoneNumberTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        initUI()
        val userId = "3"
        val header : HashMap<String,String> = HashMap()
        val token = getSharedPreferences("SHARED_PREFERENCE", Context.MODE_PRIVATE)?.getString("TOKEN","")
        header.put("x-access-token",token!!)
        val params : HashMap<String,String> = HashMap()
        params.put("id",userId)

        val url = API.FETCH_USER_BY_ID+userId
        ApiRequest.get(this,url,header,params,
                object : ApiRequest.URLCallback{
                    override fun didURLResponse(response: String) {
                        val jsonArray :JSONArray = JSONObject(response).getJSONObject("data").getJSONArray("items")
                        bindData(jsonArray.getJSONObject(0))
                    }
                },
                object : ApiRequest.ErrorCallback{
                    override fun didURLError(error: VolleyError) {
                        error.printStackTrace()
                    }

                })
    }

    private fun bindData(jsonObject: JSONObject){
        val teleoUserViewModel : TeleoUserViewModel = ViewModelProviders.of(this).get(TeleoUserViewModel::class.java)
        teleoUserViewModel.getUser(jsonObject).observe(this, Observer<TeleoUser> {
            teleo_user ->
                if(teleo_user != null){
                    bindDataToViews(teleo_user)
                }
        })
    }
    private fun initUI(){
        fullnameTextView = findViewById(R.id.teleo_user_fullname)
        usernameTextView = findViewById(R.id.teleo_user_username)
        emailTextView = findViewById(R.id.teleo_user_email)
        phoneNumberTextView = findViewById(R.id.teleo_user_phonenumber)
    }
    private fun bindDataToViews(teleo_user : TeleoUser){
        fullnameTextView.apply {
            text = "${teleo_user.firstName} ${teleo_user.lastName}"
        }
        usernameTextView.text = teleo_user.username
        emailTextView.text = teleo_user.emailAddress
        phoneNumberTextView.text = teleo_user.contactNumber
    }
}
