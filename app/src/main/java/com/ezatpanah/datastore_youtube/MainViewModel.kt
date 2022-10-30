package com.ezatpanah.datastore_youtube

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (application: Application) : AndroidViewModel(application) {

/**
when we need to use context inside our Viewmodel we should use AndroidViewModel (AVM),
because it contains the application context. To retrieve the context call getApplication(),
otherwise use the regular ViewModel (VM).
**/
    
    private val dataStore = DataStoreManager(application)

    val getTheme = dataStore.getTheme().asLiveData(Dispatchers.IO)

    fun setTheme(isDarkMode : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.setTheme(isDarkMode)
        }
    }

}
