package com.asteph11.dogtinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a table within the database.
 * Room creates a table for each class that has @Entity annotation, the fields in the class
 * correspond to columns in the table.
 * Therefore, the entity classes tend to be small model classes that don't contain any logic.
 */
@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var age: Int,
    var distance: Int,
    var bio: String,
    var img: Int
)