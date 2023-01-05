package id.ac.unand.uas_ptb_2007.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.unand.uas_ptb_2007.R
import id.ac.unand.uas_ptb_2007.data.datalogbook
import id.ac.unand.uas_ptb_2007.databinding.ItemListbookBinding
import id.ac.unand.uas_ptb_2007.models.LogbooksItem

class LogBookAdapter ()
    :RecyclerView.Adapter<LogBookAdapter.LogBookViewHolder>(){

    private lateinit var logbokLister : onClickListener

    interface onClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnClickListener(listener: onClickListener){
        logbokLister = listener
    }
    var listLogbook : List<LogbooksItem> = ArrayList()

    fun setlistLogbook(listLogbook:List<LogbooksItem>){
        this.listLogbook = listLogbook
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogBookViewHolder {
        return  LogBookViewHolder(ItemListbookBinding.inflate(LayoutInflater.from(parent.context)
            , parent, false),logbokLister)
    }

    override fun onBindViewHolder(holder: LogBookViewHolder, position: Int) {
        val item : LogbooksItem = listLogbook.get(position)
        holder.itemBinding.kegiatan.text = item.activities
        holder.itemBinding.tanggalLogbook.text = item.date
    }
    override fun getItemCount(): Int {
        return listLogbook.size
    }
    inner class LogBookViewHolder(val itemBinding:ItemListbookBinding,listener: onClickListener):
        RecyclerView.ViewHolder(itemBinding.root) {
            init {
                itemView.setOnClickListener{
                    listener.onItemClick(bindingAdapterPosition)
                }
            }
    }
    }