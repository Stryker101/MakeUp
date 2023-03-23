package com.henryware.makemeup.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.henryware.makemeup.data.models.ProductColor

class Converters {
    @TypeConverter
    fun restoreListString(listOfString: String?): List<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveListString(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun restoreProductColor(listOfString: String?): List<ProductColor?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<ProductColor?>?>() {}.type)
    }

    @TypeConverter
    fun saveProductColor(listOfProductColor: List<ProductColor?>?): String? {
        return Gson().toJson(listOfProductColor)
    }
}
