package com.deppan.adapter.app

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.deppan.adapter.BoundAdapter
import com.deppan.adapter.BoundViewHolder
import com.deppan.adapter.R
import com.deppan.adapter.databinding.ActivityMainBinding
import com.deppan.adapter.databinding.PersonItemBinding
import com.deppan.adapter.left2Right
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

            override fun onBindViewHolder(
                holder: BoundViewHolder<PersonItemBinding>,
                position: Int,
                payloads: MutableList<Any>
            ) {
                super.onBindViewHolder(holder, position, payloads)
                left2Right(holder.itemView)
            }
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(list)
    }
}