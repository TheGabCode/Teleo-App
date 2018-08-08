package gab.cdi.teleo.main.view_models

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import gab.cdi.teleo.main.models.TeleoUser

/**
 * Created by Default on 08/08/2018.
 */
class TeleoUserViewModel : ViewModel() {
    private var mutableLoggedUserData : MutableLiveData<TeleoUser>? = null
}