package com.jarho.ideasme.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.jarho.ideasme.R
import com.jarho.ideasme.databinding.CardPostsBinding
import com.jarho.ideasme.model.FeedModel

class FeedAdapter(): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    val elementList:MutableList<FeedModel> = mutableListOf()
    private var onFeedItemClickListener: ((feed: FeedModel) -> Unit)? = null

    fun addAll(newElementList:MutableList<FeedModel>){
        elementList.clear()
        elementList.addAll(newElementList)
        notifyDataSetChanged()
    }

    fun setOnFeedItemClickListener(onFeedItemClickListener: ((feed: FeedModel) -> Unit)?) {
        this.onFeedItemClickListener = onFeedItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)

        return ViewHolder(layoutInflater.inflate(R.layout.card_posts, parent, false))
    }

    class ViewHolder( val view: View): RecyclerView.ViewHolder(view){
        val binding = CardPostsBinding.bind(view)



        fun bind(feed: FeedModel) {
            Glide.with(view.context).load(feed.urlImage)
                .transform( CenterCrop(), GranularRoundedCorners(40F, 40F, 0F,0F))
                .placeholder(R.drawable.ic_account_circle_black)
                .into(binding.imgUser)

            binding.tvPost.text = feed.description



        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(elementList[position])
        holder.itemView.setOnClickListener {
            onFeedItemClickListener?.invoke(elementList[position])
        }
    }

    override fun getItemCount(): Int {
        return elementList.size
    }
}