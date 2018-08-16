package gab.cdi.teleo.main.models

import gab.cdi.teleo.main.extension.string
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Default on 15/08/2018.
 */
class Region(var regionObject : JSONObject){
    var regionId = regionObject.optString("regionId","")
    var regionName = regionObject.optString("regionName","")
    var regionCountries = regionObject.optJSONArray("regionCountries")

    class Country(var countryObject : JSONObject){
        var countryId = countryObject.optString("countryId")
        var regionId = countryObject.optString("regionId")
        var countryName = countryObject.optString("countryName")
    }
}