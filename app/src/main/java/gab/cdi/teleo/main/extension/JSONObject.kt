package gab.cdi.teleo.main.extension

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Default on 08/08/2018.
 */
fun JSONObject.string(key : String) : String{
    try{
        return this.getString(key)
    }
    catch(e : JSONException){
        e.printStackTrace()
        return ""
    }

}