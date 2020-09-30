package com.ticonsys.baseadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T: Any, V: ViewBinding>(
    @LayoutRes private val resId: Int
): RecyclerView.Adapter<BaseAdapter.BaseViewHolder<V>>() {

    val differ by lazy {
        AsyncListDiffer(this, initializeDiffItemCallback())
    }

    protected abstract fun initializeViewBinding(view: View): V

    protected abstract fun initializeDiffItemCallback(): DiffUtil.ItemCallback<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val view = LayoutInflater.from(parent.context).inflate(resId, parent, false)
        return BaseViewHolder(initializeViewBinding(view))
    }

    override fun getItemCount() = differ.currentList.size


    protected var listener: ((view: View?, item: T) -> Unit)? = null

    fun setOnItemClickListener(listener: (view: View?, item: T) -> Unit){
        this.listener = listener
    }

    class BaseViewHolder<V: ViewBinding>(val binding: V): RecyclerView.ViewHolder(
        binding.root
    )


}