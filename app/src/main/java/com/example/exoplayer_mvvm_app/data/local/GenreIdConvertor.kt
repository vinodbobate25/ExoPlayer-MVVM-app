package com.example.exoplayer_mvvm_app.data.local

import androidx.room.TypeConverter

class GenreIdConvertor {
    @TypeConverter
    fun fromSource(source: List<Int>): Int {
        return 0
    }

    @TypeConverter
    fun toSource(name: Int): List<Int> {
        return listOf(0)
    }
}