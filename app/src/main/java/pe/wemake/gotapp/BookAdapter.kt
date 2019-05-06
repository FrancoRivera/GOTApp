package pe.wemake.gotapp

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import kotlinx.android.synthetic.main.recycler_view_book.view.*
import pe.wemake.gotapp.model.Book

class BookAdapter(context: Context) :  RecyclerView.Adapter<BookAdapter.ViewHolder>(){

    private var books: List<Book> = emptyList()
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout., parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var requestQueue: RequestQueue = GotNetworkSingleton.getInstance(context).requestQueue;
        with(holder){
            var book = books[position]

            var mainImageRequest = ImageRequest("http://placekitten.com/200/300", {
                bookImageView.setImageBitmap(it);
            }, 0,0, null, null,
                {
                    Log.d("stuff", it.toString())
                }
            )
            bookNameTextView.text = book.name

           // itemView.setOnClickListener{
           //    val action = PetListFragmentDirections.getPetDetail(pet.id, pet.name)
           //   itemView.findNavController().navigate(action, null )
           // }
           requestQueue.add(mainImageRequest)
        }
    }
    fun setBooks(books: List<Book>){
        this.books = books
        notifyDataSetChanged()
    }
    override fun getItemCount() = books.size

    inner class ViewHolder(bookListView: View): RecyclerView.ViewHolder(bookListView) {
        var bookImageView: ImageView = bookListView.book_image
        var bookNameTextView: TextView = bookListView.book_name
    }

}

