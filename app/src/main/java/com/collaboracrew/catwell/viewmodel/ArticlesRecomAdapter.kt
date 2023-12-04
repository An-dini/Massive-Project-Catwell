package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ArticleRecommendationModel

class ArticlesRecomAdapter(private val data: List<ArticleRecommendationModel>) : RecyclerView.Adapter<ArticlesRecomAdapter.ViewHolder>() {

    private var onItemClickListener: ((ArticleRecommendationModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_recomendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    fun setOnItemClickListener(listener: (ArticleRecommendationModel) -> Unit) {
        onItemClickListener = listener
    }
    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val  ivArticle: ImageView = itemView.findViewById(R.id.ivArticle)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        fun bind(item: ArticleRecommendationModel) {
            ivArticle.setImageResource(item.articleImage)
            tvTitle.text = item.title
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(data[position])
                }
            }
        }
    }
}