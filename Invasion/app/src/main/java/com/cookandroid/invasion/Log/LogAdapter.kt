package com.cookandroid.invasion.Log

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.invasion.R
import kotlinx.android.synthetic.main.list_log.view.*
import kotlinx.android.synthetic.main.list_option.view.*

class LogAdapter(val context: Context, val logList: ArrayList<LogItem>) : RecyclerView.Adapter<LogAdapter.Holder>() {

    // 어떤 화면을 반환 할지에 대한 ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_log, parent, false)
        return Holder(view)
    }

    // holder클래스의 bind 메소드를 사용한 데이터 바인딩
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(logList[position],context)
    }

    // logList의 크기를 반환한다.
    override fun getItemCount(): Int {
        return logList.size
    }

    // viewHolder에 담을 Holder클래스를 지정하여 값을 연결한다
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtInfo  =    itemView.txtInfo
        val txtTime  =    itemView.txtTime
        val logPhoto =  itemView.logPhoto

        // logPhoto의 들어가 이미지의 id를 파일명으로 찾고, 없으면 안드로이드 기본 아이콘 표시
        fun bind(loglist: LogItem, context: Context) {
            if (loglist.logPhoto !="") {
                val resourceId = context.resources.getIdentifier(loglist.logPhoto,"drawable",context.packageName)
                logPhoto?.setImageResource(resourceId)
            }
            else {
                logPhoto?.setImageResource(R.mipmap.ic_launcher)
            }

            // 나머지 TextView 연결
            txtInfo?.text = loglist.logInfo
            txtTime?.text = loglist.logTime
        }
    }
}