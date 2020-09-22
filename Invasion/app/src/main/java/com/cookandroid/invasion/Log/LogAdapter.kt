package com.cookandroid.invasion.Log

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.invasion.R
import java.util.*


class CustomAdapter(private val arrayList: ArrayList<LogItem>?, private val context: Context) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder?>() {
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_log, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(arrayList!![position].logPhoto)
            .into(holder.photo)
        holder.info.text = arrayList[position].logInfo
        holder.time.text = arrayList[position].logTime
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photo: ImageView
        var info: TextView
        var time: TextView

        init {
            photo = itemView.findViewById(R.id.logPhoto)
            info = itemView.findViewById(R.id.txtInvasion)
            time = itemView.findViewById(R.id.txtTime)
        }
    }

    override fun getItemCount(): Int {
        return arrayList?.size ?: 0
    }
}