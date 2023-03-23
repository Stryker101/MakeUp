package com.henryware.makemeup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.henryware.makemeup.data.models.BrandsItem
import com.henryware.makemeup.utils.Converters

@Database(entities = [BrandsItem::class], version = 2)

@TypeConverters(Converters::class)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getBrandsDao(): ProductsDao
}
