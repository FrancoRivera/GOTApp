package pe.wemake.gotapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.wemake.gotapp.dao.BookDao
import pe.wemake.gotapp.model.Book

class BookViewModels(application: Application) : AndroidViewModel(application){

    private val repository: BookRepository
    val allBooks: LiveData<List<Book>>

    init {
        var bookDao: BookDao = DixiDatabase.getDatabase(application, viewModelScope).petDao()
        repository  = BookRepository(bookDao)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(book)

    }
}