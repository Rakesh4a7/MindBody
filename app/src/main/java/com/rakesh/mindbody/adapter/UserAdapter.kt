package com.rakesh.mindbody.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakesh.mindbody.R
import com.rakesh.mindbody.adapter.UserAdapter.UserViewHolder
import com.rakesh.mindbody.databinding.ItemUserBinding
import com.rakesh.mindbody.model.User
import java.util.*

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private var users: ArrayList<User?>? = null
    var onItemClick: ((User?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemUserBinding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = users!![position]
        holder.itemUserBinding.user = currentUser
    }

    override fun getItemCount(): Int {
        return if (users != null) {
            users!!.size
        } else {
            0
        }
    }

    fun setUserList(users: ArrayList<User?>?) {
        this.users = users
        notifyDataSetChanged()
    }

    fun getCurrentItemAt(position: Int): User? {
        return users!![position]
    }

    inner class UserViewHolder(val itemUserBinding: ItemUserBinding) : RecyclerView.ViewHolder(
        itemUserBinding.root
    ) {
        init {
            itemView.setOnClickListener { v: View? ->
                onItemClick?.invoke(getCurrentItemAt(adapterPosition))
            }
        }
    }
}