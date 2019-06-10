package com.example.woopsicrediapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.woopsicrediapp.R
import com.example.woopsicrediapp.databinding.LayoutPersonItemBinding
import com.example.woopsicrediapp.model.response.Person

class PeopleAdapter(private val people: List<Person>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutPersonItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_person_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(people[position])
    }

    class ViewHolder(private val binding: LayoutPersonItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.title = person.name

            Glide.with(binding.root)
                .load(person.imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivPerson)
        }
    }
}