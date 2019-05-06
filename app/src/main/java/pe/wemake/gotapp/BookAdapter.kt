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
import pe.wemake.gotapp.model.Book

class BookAdapter(context: Context) :  RecyclerView.Adapter<BookAdapter.ViewHolder>(){

    private var books: List<Book> = emptyList()
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter {
        val view: View = inflater.inflate(R.layout., parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookAdapter, position: Int) {
        var requestQueue: RequestQueue = GotNetworkSingleton.getInstance(context).requestQueue;
        with(holder){
            var book = books[position]

            var mainImageRequest = ImageRequest(book.picture, {
                bookImageView.setImageBitmap(it);
            }, 0,0, null, null,
                {
                    Log.d("stuff", it.toString())
                }
            )
            bookNameTextView.text = pet.name

            itemView.setOnClickListener{
                val action = PetListFragmentDirections.getPetDetail(pet.id, pet.name)
                itemView.findNavController().navigate(action, null )
            }
            requestQueue.add(mainImageRequest)

        }

    }

    override fun getItemCount() = allBooks.size


    inner class ViewHolder(bookListView: View): RecyclerView.ViewHolder(bookListView) {
        var bookImageView: ImageView = bookListView.book_image
        var bookNameTextView: TextView = bookListView.book_name
    }

}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
    override fun getItemCount() = pets.size

    fun setPets(pets: List<Pet>){
        this.pets = pets
        notifyDataSetChanged()
    }

}
