# BaseAdapter
[![](https://jitpack.io/v/dutchmanbd/baseadapter.svg)](https://jitpack.io/#dutchmanbd/baseadapter)

Base Adapter Project For Kotlin

## Get Started
Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }	
	}
}
```
Add the dependency

```
android {
    buildFeatures{
        viewBinding = true
    }
}
```

```
dependencies {
	implementation 'com.github.dutchmanbd:baseadapter:1.0.1'
}
```

### How to use

Below code snippet is simple_food_item.xml

```
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <androidx.appcompat.widget.AppCompatImageView
        android:id="@+id/ivFoodImage"
        android:layout_width="0dp"
        android:layout_height="220dp"
        android:scaleType="centerCrop"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/ic_food" />

    <TextView
        android:id="@+id/tvFoodName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="@+id/ivFoodImage"
        app:layout_constraintTop_toBottomOf="@+id/ivFoodImage"
        tools:text="Salad" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

Below code is FoodAapter.kt

```
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
```

Use adapter from Fragment or Activity

```
    binding.rvFood.apply {
        adapter = foodAdapter
    }

    foodAdapter.setOnItemClickListener { view, item ->
        Toast.makeText(this, "${item.name}", Toast.LENGTH_SHORT).show()
    }
    
    foodAdapter.differ.submitList(
        listOf(
            Food(1, "Biriani"),
            Food(2, "Salad"),
            Food(3, "Rice"),
            Food(4, "Sweet")
        )
    )
```

