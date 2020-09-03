package com.deppan.adapter.app

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deppan.adapter.BoundAdapter
import com.deppan.adapter.BoundViewHolder
import com.deppan.adapter.R
import com.deppan.adapter.databinding.ActivityMainBinding
import com.deppan.adapter.databinding.PersonItemBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val list = ArrayList<Person>()
        (0 until 50).forEach {
            list.add(Person(it, "abc $it"))
        }
        val adapter = object : BoundAdapter<Person, PersonItemBinding>(Person.diffCallback) {
            var onAttach = true
            val DURATION = 150L

            override fun bindTo(holder: BoundViewHolder<PersonItemBinding>, item: Person) {
                holder.binding.person = item
            }

            override fun bindTo(
                holder: BoundViewHolder<PersonItemBinding>,
                item: Person,
                payloads: MutableList<Any>
            ) {
                holder.binding.person = item
            }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): BoundViewHolder<PersonItemBinding> {
                val binding: PersonItemBinding
                val layoutId = R.layout.person_item
                binding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
                return BoundViewHolder(binding.root)
            }

            override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
                super.onAttachedToRecyclerView(recyclerView)
                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        Log.d("MainActivity", "onScrollStateChanged: Called $newState")
                        onAttach = false
                    }
                })
            }

            override fun onBindViewHolder(
                holder: BoundViewHolder<PersonItemBinding>,
                position: Int,
                payloads: MutableList<Any>
            ) {
                super.onBindViewHolder(holder, position, payloads)
//                setAnimation(holder.itemView, position)
                fromLeft2Right(holder.itemView, position)
//                fromRight2Left(holder.itemView, position)
            }

            private fun setAnimation(itemView: View, position: Int) {
                var index = position
                if (!onAttach) index = -1
                val isNotFirstItem = index == -1
                index++
                itemView.alpha = 0f
                val animatorSet = AnimatorSet()
                val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.0f, 0.5f, 1.0f)
                ObjectAnimator.ofFloat(itemView, "alpha", 0.0f).start()
                animator.startDelay = if (isNotFirstItem) DURATION / 2 else (index * DURATION / 3)
                animator.duration = 500
                animatorSet.play(animator)
                animator.start()
            }

            private fun fromLeft2Right(itemView: View, position: Int) {
                var index = position
                if (!onAttach) index = -1
                val notFirstItem = index == -1
                index++
                itemView.translationX = -400f
                itemView.alpha = 0f
                val animatorSet = AnimatorSet()
                val animatorTranslateY = ObjectAnimator.ofFloat(itemView, "translationX", -400f, 0f)
                val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
                ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
                animatorTranslateY.startDelay = if (notFirstItem) DURATION else index * DURATION
                animatorTranslateY.duration = (if (notFirstItem) 2 else 1) * DURATION
                animatorSet.playTogether(animatorTranslateY, animatorAlpha)
                animatorSet.start()
            }

            private fun fromRight2Left(itemView: View, position: Int) {
                var index = position
                if (!onAttach) index = -1
                val notFirstItem = index == -1
                index++
                itemView.translationX = itemView.x + 400
                itemView.alpha = 0f
                val animatorSet = AnimatorSet()
                val animatorTranslateY: ObjectAnimator =
                    ObjectAnimator.ofFloat(itemView, "translationX", itemView.x + 400, 0f)
                val animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1f)
                ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
                animatorTranslateY.startDelay = if (notFirstItem) DURATION else index * DURATION
                animatorTranslateY.duration = (if (notFirstItem) 2 else 1) * DURATION
                animatorSet.playTogether(animatorTranslateY, animatorAlpha)
                animatorSet.start()
            }
        }
        binding.recyclerView.adapter = adapter

        adapter.submitList(list)
    }
}