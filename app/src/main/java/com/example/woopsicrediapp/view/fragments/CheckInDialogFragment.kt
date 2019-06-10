package com.example.woopsicrediapp.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.woopsicrediapp.R
import com.example.woopsicrediapp.model.request.CheckIn
import com.example.woopsicrediapp.viewmodel.EventViewModel
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class CheckInDialogFragment : DialogFragment() {

    private lateinit var viewModel: EventViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (!isAdded)
            return super.onCreateDialog(savedInstanceState)

        val root = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_check_in_form, null)
        viewModel = getViewModel { parametersOf(root) }

        root.findViewById<Button>(R.id.btnCheckIn).setOnClickListener {
            val etName = root.findViewById<EditText>(R.id.etName)
            val etEmail = root.findViewById<EditText>(R.id.etEmail)

            val form = CheckIn(arguments?.getInt(EVENT_ID, -1)!!, etName.text.toString(), etEmail.text.toString())
            viewModel.performCheckIn(form, { dismiss() }) { dismiss() }
        }

        return AlertDialog.Builder(requireContext())
            .setView(root)
            .create()
    }

    companion object {
        private const val EVENT_ID = "eventId"

        fun newInstance(eventId: Int): CheckInDialogFragment {
            val args = Bundle().apply { putInt(EVENT_ID, eventId) }
            return CheckInDialogFragment().apply { arguments = args }
        }
    }
}