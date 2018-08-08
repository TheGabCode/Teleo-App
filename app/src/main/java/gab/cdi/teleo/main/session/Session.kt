package gab.cdi.teleo.activities.session

import android.content.Context
import android.content.SharedPreferences

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


    constructor(context: Context?) {
        this.context        = context
        sharedPrefs         = context?.getSharedPreferences(SHARED_PREFERENCE,Context.MODE_PRIVATE)
        sharedPrefsEditor   = sharedPrefs?.edit()
    }

    fun authorize(raw : String){
        sharedPrefsEditor?.putString(TOKEN,raw)?.apply()
        sharedPrefsEditor?.putBoolean(LOGGED,true)
    }

    fun user() : String? {
        return sharedPrefs?.getString(TOKEN,"")
    }

    fun isUserLoggedIn() : Boolean? {
        return sharedPrefs?.getBoolean(TOKEN,false)
    }

    fun deauthorize(){
        sharedPrefsEditor?.clear()
    }

}