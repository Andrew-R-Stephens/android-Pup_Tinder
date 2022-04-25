package com.asteph11.dogtinder.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModels provide data to the UI and survive configuration changes. A ViewModel acts as a
 * communication center between the Repository and the UI.
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val allData: LiveData<List<User>>
    private val repository: UserRepository

    /**
     * Populates the allData variable with the data from within the Database
     */
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allData = repository.allData
    }

    /**
     * Accepts a user and stores it within the repository
     */
    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}