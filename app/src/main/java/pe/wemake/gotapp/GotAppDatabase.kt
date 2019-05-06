package pe.wemake.gotapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.wemake.gotapp.dao.BookDao
import pe.wemake.gotapp.model.Book

@Database(entities = [Book::class], version = 1)
abstract class DixiDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object{
        @Volatile
        private var INSTANCE: DixiDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): DixiDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DixiDatabase::class.java,
                    "goat_database"
                ).addCallback(DixiDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
        private class DixiDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.bookDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            bookDao.deleteAll()

        }
    }
}
