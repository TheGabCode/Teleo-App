package gab.cdi.teleo.main.models

import gab.cdi.teleo.main.extension.string
import org.json.JSONObject

/**
 * Created by Default on 15/08/2018.
 */
class Program(var jsonObject: JSONObject){
    var programId = jsonObject.string("programId")
    var programTitle = jsonObject.string("programTitle")
    var programDescription = jsonObject.string("programDescription")
    var programThumbnailUrl = jsonObject.string("programUrl")
    var programNumber = jsonObject.string("programNumber")
}