package es.etg.dam.pmdm10.data


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.etg.dam.pmdm10.R

class DataAdapter (private val mList: List<ItemViewModel>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {



    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Cargo el diseño
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tarjeta_view_design, parent, false)

        return ViewHolder(view)
    }

    // Asociamos un elemento del dataset un viewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemViewModel = mList[position]

        // Cargamos la imagen y descripción de una posición
        holder.imageView.setImageResource(itemViewModel.image)
        holder.textView.text = itemViewModel.descripcion
        holder.textView.text=itemViewModel.opcion
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, itemViewModel)
        }


    }

    // Define objetos sobre los elementos de layout
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val opcion: TextView=itemView.findViewById(R.id.opcion)

    }
    interface OnClickListener {
        fun onClick(position: Int, model: ItemViewModel)
    }
    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener=listener
    }
    override fun getItemCount(): Int {
        return mList.size
    }
}