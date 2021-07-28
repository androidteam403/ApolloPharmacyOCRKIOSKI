package com.apollo.pharmacy.ocr.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apollo.pharmacy.ocr.R
import com.apollo.pharmacy.ocr.interfaces.OnRecyclerItemClickListener
import com.apollo.pharmacy.ocr.model.FAQData

class FAQAdapter(val data: ArrayList<FAQData>, val context: Context, val listener: OnRecyclerItemClickListener) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FAQAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_faq_item, parent, false)
        return FAQAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: FAQAdapter.ViewHolder, position: Int) {
        holder.bindItems()

        holder.question.text = data.get(position).question
        holder.answer.text = data.get(position).answer
        holder.count.text = data.get(position).id.toString() + "."
        holder.answerLayout.visibility = View.GONE

        holder.parentLayout.setOnClickListener({
            if (listener != null) {
                if (data.get(position).visible) {
                    data.get(position).visible = false;
                    holder.answerLayout.visibility = View.GONE
                    holder.showAnswer.setImageResource(R.drawable.icon_arrow_open)
                    holder.parentLayout.setBackground(context.resources.getDrawable(R.drawable.faq_bg))
                } else {
                    listener.onViewClick(position)
                }
            }
        })

        if (data.get(position).visible) {
            holder.answerLayout.visibility = View.VISIBLE
            holder.showAnswer.setImageResource(R.drawable.arrow_close)
            holder.parentLayout.setBackground(context.resources.getDrawable(R.drawable.faq_bg1))
        } else {
            holder.answerLayout.visibility = View.GONE
            holder.showAnswer.setImageResource(R.drawable.icon_arrow_open)
            holder.parentLayout.setBackground(context.resources.getDrawable(R.drawable.faq_bg))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var question: TextView
        lateinit var count: TextView
        lateinit var answer: TextView
        lateinit var showAnswer: ImageView
        lateinit var answerLayout: LinearLayout
        lateinit var parentLayout: LinearLayout

        fun bindItems() {
            question = itemView.findViewById(R.id.faqQuestion) as TextView
            count = itemView.findViewById(R.id.count_txt) as TextView
            showAnswer = itemView.findViewById(R.id.showAnswer) as ImageView
            answer = itemView.findViewById(R.id.faqAnswer) as TextView
            answerLayout = itemView.findViewById(R.id.answerLayout) as LinearLayout
            parentLayout = itemView.findViewById(R.id.parent_layout) as LinearLayout
        }
    }
}