package com.ticonsys.baseadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T: Any, VB: ViewBinding>(

): RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VB>>() {

    val differ by lazy {
        AsyncListDiffer(this, initializeDiffItemCallback())
    }

    protected abstract fun initializeViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup): VB

    protected abstract fun initializeDiffItemCallback(): DiffUtil.ItemCallback<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(initializeViewBinding(layoutInflater, parent))
    }

    override fun getItemCount() = differ.currentList.size


    protected var listener: ((view: View?, item: T) -> Unit)? = null

    fun setOnItemClickListener(listener: (view: View?, item: T) -> Unit){
        this.listener = listener
    }

    class BaseViewHolder<VB: ViewBinding>(val binding: VB): RecyclerView.ViewHolder(
        binding.root
    )


}