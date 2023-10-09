package com.example.work8.model.film

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "films")
data class Film (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val photo: String,
    val name: String,
    val description: String,
)