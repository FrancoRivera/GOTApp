package pe.wemake.gotapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.wemake.gotapp.model.Book

@Dao
interface BookDao{

    @Query("SELECT * FROM book_table")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    fun deleteAll()

    @Delete
    suspend fun delete(book: Book)

}
