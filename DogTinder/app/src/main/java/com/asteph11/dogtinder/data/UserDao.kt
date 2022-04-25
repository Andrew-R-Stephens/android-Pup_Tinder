package com.asteph11.dogtinder.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAOs are responsible for defining the methods that access the database.
 * This is the place where we write our queries
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM Users ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

}