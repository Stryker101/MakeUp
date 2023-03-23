package com.henryware.makemeup.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.henryware.makemeup.data.local.ProductsDao
import com.henryware.makemeup.data.local.ProductsDatabase
import com.henryware.makemeup.data.models.BrandsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class BrandsDaoTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()
    private lateinit var database: ProductsDatabase
    private lateinit var dao: ProductsDao

    @Before
    fun create() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, ProductsDatabase::class.java)
            .build()
        dao = database.getBrandsDao()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    class TestDispatcherRule(
        private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    ) : TestWatcher() {
        override fun starting(description: Description) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description) {
            Dispatchers.resetMain()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addItem_shouldReturn_theItem_inFlow() = runTest {
        val item = BrandsItem(1, "wer", "fbh", "hfj", "saa", "dfd", "sdd")
        val itemList = listOf<BrandsItem>(item)
        dao.addProductsToDb(itemList)

        dao.getProductsFromDb().test {
            val list = awaitItem()
            assert(list.contains(item))
            cancel()
        }
    }
}
