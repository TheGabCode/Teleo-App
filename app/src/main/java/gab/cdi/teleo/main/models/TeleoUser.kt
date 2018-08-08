package gab.cdi.teleo.activities.models

import org.json.JSONObject

/**
 * Created by Default on 08/08/2018.
 */
data class TeleoUser(var userJSONObject : JSONObject){
    var fullname : String = userJSONObject.optString("fullname","")
    var username : String = userJSONObject.optString("username","")
    var email : String = userJSONObject.optString("email","")
    var phoneNumber : String = userJSONObject.optString("phoneNumber")
}