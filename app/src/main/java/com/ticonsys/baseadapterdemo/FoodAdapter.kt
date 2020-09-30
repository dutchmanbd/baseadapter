package com.ticonsys.baseadapterdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ticonsys.baseadapter.BaseAdapter
import com.ticonsys.baseadapterdemo.databinding.SimpleFoodItemBinding


class FoodAdapter : BaseAdapter<Food, SimpleFoodItemBinding>() {

    override fun initializeViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ) = SimpleFoodItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Food, newItem: Food) = oldItem == newItem
    }
    override fun onBindViewHolder(holder: BaseViewHolder<SimpleFoodItemBinding>, position: Int) {
        val food = differ.currentList[position]
        with(holder.binding){
            tvFoodName.text = food.name
            root.setOnClickListener {view->
                listener?.let { it(view, food) }
            }
        }
    }
}