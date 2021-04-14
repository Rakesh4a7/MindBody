package com.rakesh.mindbody.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rakesh.mindbody.R
import com.rakesh.mindbody.databinding.ItemProvinceBinding
import com.rakesh.mindbody.model.Province
import java.util.*

class ProvinceAdapter : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {
    private var users: ArrayList<Province?>? = null
    var onItemClick: ((Province?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val itemProvinceBinding: ItemProvinceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_province,
            parent,
            false
        )
        return ProvinceViewHolder(itemProvinceBinding)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
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

    fun setUserList(users: ArrayList<Province?>?) {
        this.users = users
        notifyDataSetChanged()
    }

    fun getCurrentItemAt(position: Int): Province? {
        return users!![position]
    }

    inner class ProvinceViewHolder(val itemUserBinding: ItemProvinceBinding) : RecyclerView.ViewHolder(
        itemUserBinding.root
    ) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getCurrentItemAt(adapterPosition))
            }
        }
    }

}