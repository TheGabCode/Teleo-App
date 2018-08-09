package gab.cdi.teleo.main.view_models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import gab.cdi.teleo.main.models.TeleoUser
import org.json.JSONObject

/**
 * Created by Default on 08/08/2018.
 */
class TeleoUserViewModel : ViewModel() {
    private lateinit var mutableLoggedUserData : MutableLiveData<TeleoUser>
    fun getUser(sampleUserObject : JSONObject) : LiveData<TeleoUser>{
        mutableLoggedUserData = MutableLiveData<TeleoUser>()
        Log.d("Tag ",sampleUserObject.toString())
        mutableLoggedUserData.postValue(TeleoUser(sampleUserObject))
        return mutableLoggedUserData
    }
}