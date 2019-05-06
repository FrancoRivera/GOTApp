package pe.wemake.gotapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey
    var url: String = "",
    var name: String = "",
    var isbn: String = "",
    var authors: Array<String> = emptyArray(),
    var numberOfPages: Int = 0,
    var plublisher: String =  "",
    var country: String = "",
    var mediaType: String = "",
    var released: Date = Date(0),
    var characters: Array<String> = emptyArray(),
    var povCharacters: Array<String> = emptyArray()
    )
