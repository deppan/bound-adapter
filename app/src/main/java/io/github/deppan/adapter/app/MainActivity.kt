package io.github.deppan.adapter.app

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.github.deppan.adapter.BoundAdapter
import io.github.deppan.adapter.BoundViewHolder
import io.github.deppan.adapter.app.databinding.ActivityMainBinding
import io.github.deppan.adapter.app.databinding.PersonItemBinding
import io.github.deppan.adapter.left2Right
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val list = ArrayList<Person>()
        (0 until 50).forEach {
            list.add(Person(it, "abc $it"))
        }
        val adapter = object : BoundAdapter<Person>(Person.diffCallback) {

            override fun bindTo(holder: BoundViewHolder, item: Person) {
                (holder.ui as PersonItemBinding).person = item
            }

            override fun bindTo(holder: BoundViewHolder, item: Person, payloads: MutableList<Any>) {
                (holder.ui as PersonItemBinding).person = item
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoundViewHolder {
                val binding = PersonItemBinding.inflate(layoutInflater, parent, false)
                return BoundViewHolder(binding)
            }

            override fun onBindViewHolder(
                holder: BoundViewHolder,
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