package gab.cdi.teleo.main.models

import gab.cdi.teleo.main.extension.string
import org.json.JSONObject

/**
 * Created by Default on 08/08/2018.
 */
data class TeleoUser(var jsonObject : JSONObject){
//    var fullName : String = userJSONObject.optString("fullName","")
//    var username : String = userJSONObject.optString("username","")
//    var emailAddress : String = userJSONObject.optString("emailAddress","")
//    var contactNumber : String = userJSONObject.optString("contactNumber","")



    var message : String?            = jsonObject.string("message")


    var token : String?              = jsonObject.string("token")


    var id : String?                 = jsonObject.string("id")


    var username : String?           = jsonObject.string("username")


    var firstName : String?          = jsonObject.string("firstName")


    var lastName : String?           = jsonObject.string("lastName")


    var addressLine1 : String?       = jsonObject.string("addressLine1")
    var addressLine2 : String?       = jsonObject.string("addressLine2")


    var addressProvince : String?    = jsonObject.string("addressProvince")


    var addressCity : String?        = jsonObject.string("addressCity")


    var contactNumber : String?      = jsonObject.string("contactNumber")

    var emailAddress : String?       = jsonObject.string("emailAddress")
}