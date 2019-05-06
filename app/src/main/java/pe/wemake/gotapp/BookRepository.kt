package pe.wemake.gotapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import pe.wemake.gotapp.dao.BookDao
import pe.wemake.gotapp.model.Book


class BookRepository(private var bookDao: BookDao){
    var allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book){
        bookDao.insert(book)
    }
}