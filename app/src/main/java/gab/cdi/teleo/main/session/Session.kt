package gab.cdi.teleo.main.session

import android.content.Context
import android.content.SharedPreferences
import gab.cdi.teleo.main.extension.string
import gab.cdi.teleo.main.models.TeleoUser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Default on 08/08/2018.
 */
class Session {
    var context : Context? = null
    var sharedPrefs : SharedPreferences? = null
    var sharedPrefsEditor : SharedPreferences.Editor? = null
    var userToken : String? = ""

    var SHARED_PREFERENCE = "SHARED_PREFERENCE"
    var TOKEN = "TOKEN"
    var LOGGED = "LOGGED"
    var USER_ID = "USER_ID"
    var USER_DATA = "USER_DATA"


    constructor(context: Context?) {
        this.context        = context
        sharedPrefs         = context?.getSharedPreferences(SHARED_PREFERENCE,Context.MODE_PRIVATE)
        sharedPrefsEditor   = sharedPrefs?.edit()
    }

    fun authorize(raw : String){
        var token : String? = null
        try {
            token = JSONObject(raw).getJSONObject("data").string("token")
            sharedPrefsEditor?.putString(USER_DATA,JSONObject(raw).getJSONObject("data").toString())
        }
        catch (e : JSONException){
            e.printStackTrace()
        }


        sharedPrefsEditor?.putString(TOKEN,token)?.apply()
        sharedPrefsEditor?.putBoolean(LOGGED,true)?.apply()

    }

    fun authorizeLogIn(raw : String){
        var token : String? = null
        try{
            token = JSONObject(raw).getJSONObject("data").getJSONArray("items").getJSONObject(0).string("token")
        }
        catch (e : JSONException){
            e.printStackTrace()
        }

        sharedPrefsEditor?.putString(TOKEN,token)?.apply()
        sharedPrefsEditor?.putBoolean(LOGGED,true)?.apply()
    }

    fun token() : String? {
        return sharedPrefs?.getString(TOKEN,"")
    }

    fun user() : TeleoUser? {
        var json   = JSONObject(sharedPrefs?.getString(USER_DATA,""))
        return TeleoUser(json)
    }

    fun isUserLoggedIn() : Boolean? {
        return sharedPrefs?.getBoolean(LOGGED,false)
    }

    fun deauthorize(){
        sharedPrefsEditor?.clear()?.commit()
    }

}