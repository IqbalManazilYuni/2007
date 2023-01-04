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
    :RecyclerView.Adapter<LogBookAdapter.LogbookViewHolder>(){

    var listLogbook : List<LogbooksItem> = ArrayList()
    fun setlistLogbook(listLogbook:List<LogbooksItem>){
        this.listLogbook = listLogbook
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogbookViewHolder {
        return  LogbookViewHolder(ItemListbookBinding.inflate(LayoutInflater.from(parent.context)
            , parent, false))
    }

    override fun onBindViewHolder(holder: LogbookViewHolder, position: Int) {
        val item : LogbooksItem = listLogbook.get(position)
        holder.itemBinding.kegiatan.text = item.activities
        holder.itemBinding.tanggalLogbook.text = item.date
    }
    override fun getItemCount(): Int {
        return listLogbook.size
    }
    inner class LogbookViewHolder(val itemBinding:ItemListbookBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
    }
    }