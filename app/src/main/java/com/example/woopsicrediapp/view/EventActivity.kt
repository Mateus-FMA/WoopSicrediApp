package com.example.woopsicrediapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.widget.ShareActionProvider
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.woopsicrediapp.R
import com.example.woopsicrediapp.core.viewmodel.BaseActivity
import com.example.woopsicrediapp.databinding.ActivityEventBinding
import com.example.woopsicrediapp.model.response.Event
import com.example.woopsicrediapp.viewmodel.rx.RxPublishEvent
import kotlinx.android.synthetic.main.activity_event.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.woopsicrediapp.view.adapter.PeopleAdapter
import com.example.woopsicrediapp.view.fragments.CheckInDialogFragment


class EventActivity : BaseActivity(), PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: ActivityEventBinding
    private var shareActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_event)
        binding.viewModel = getViewModel { parametersOf(binding.root) }

        registerDisposable(RxPublishEvent.subscribe { bindEvent(it) })
        binding.viewModel?.getEventById(intent.getIntExtra(EVENT_ID, -1)) {
            binding.progressBar.visibility = View.GONE
        }

        fab.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            val inflater = popup.menuInflater
            inflater.inflate(R.menu.menu_event, popup.menu)

//            popup.menu.findItem(R.id.action_share).also {
//                shareActionProvider = ShareActionProvider(this)
//                MenuItemCompat.setActionProvider(it, shareActionProvider)
//            }

            popup.setOnMenuItemClickListener(this)
            popup.show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.viewModel?.getEventById(intent.getIntExtra(EVENT_ID, -1)) {
                binding.swipeRefresh.isRefreshing = false
            }
        }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_check_in -> {
                CheckInDialogFragment.newInstance(intent.getIntExtra(EVENT_ID, -1))
                    .show(supportFragmentManager, "Check-in")
                true
            }
//            R.id.action_share -> {
//                val sendIntent: Intent = Intent().apply {
//                    action = Intent.ACTION_SEND
//                    putExtra(Intent.EXTRA_TEXT, binding.viewModel?.description.orEmpty())
//                    type = "text/plain"
//                }
//
//                shareActionProvider?.setShareIntent(sendIntent)
//
//                true
//            }
            else -> false
        }
    }

    private fun bindEvent(event: Event) {
        Glide.with(binding.root)
            .load(event.imageUrl)
            .into(binding.ivEvent)

        toolbar.title = event.title.orEmpty()

        binding.viewModel?.description = event.description.orEmpty()
        binding.viewModel?.hasShows = event.people?.isNotEmpty() ?: false

        binding.rvShows.layoutManager = LinearLayoutManager(this)
        binding.rvShows.adapter = PeopleAdapter(event.people ?: emptyList())
    }

    companion object {

        private const val EVENT_ID = "eventId"

        fun launch(context: Context, eventId: Int) {
            val intent = Intent(context, EventActivity::class.java)
            intent.putExtra(EVENT_ID, eventId)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            context.startActivity(intent)
        }

    }

}