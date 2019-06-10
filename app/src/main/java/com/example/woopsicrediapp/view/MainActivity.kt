package com.example.woopsicrediapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woopsicrediapp.R
import com.example.woopsicrediapp.core.viewmodel.BaseActivity
import com.google.android.material.snackbar.Snackbar
import com.example.woopsicrediapp.databinding.ActivityMainBinding
import com.example.woopsicrediapp.view.adapter.EventsAdapter

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = getViewModel { parametersOf(binding.root) }
        binding.viewModel?.let { vm ->
            vm.eventsLiveData.observe(this, Observer {
                binding.rvEvents.layoutManager = LinearLayoutManager(this)
                binding.rvEvents.adapter = EventsAdapter(it ?: emptyList())
            })

            vm.getEvents()
            binding.swipeRefresh.setOnRefreshListener { vm.getEvents({ binding.swipeRefresh.isRefreshing = false }) }
        }

        setSupportActionBar(toolbar)

    }

    override fun onDestroy() {
        binding.viewModel?.clearDisposables()
        super.onDestroy()
    }
}
