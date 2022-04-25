package com.asteph11.dogtinder.data

import androidx.lifecycle.LiveData

/**
 * A repository is a class that abstracts access to multiple data sources.
 * The repository is not part of the Architecture Components libraries, but is a suggested best
 * practice for code separation and architecture.
 */
class UserRepository(private val userDao: UserDao) {

    val allData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

}