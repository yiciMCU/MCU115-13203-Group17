package com.example.weekfiveassignment

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator

class ProgressFragment : Fragment(R.layout.frag_progress) {
    private val vm: WorkViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearBar = view.findViewById<LinearProgressIndicator>(R.id.linearProgressBar)
        val linearStatus = view.findViewById<TextView>(R.id.txtLinearStatus)

        val circularFrame = view.findViewById<FrameLayout>(R.id.circularFrame)
        val circularBar = view.findViewById<CircularProgressIndicator>(R.id.circularProgressBar)
        val circularText = view.findViewById<TextView>(R.id.txtCircularProgress)

        vm.status.observe(viewLifecycleOwner) { status ->
            if (status == "Cancelled") {
                // Cancelled: hide linear progress bar and show circular progress bar
                linearBar.visibility = View.GONE
                linearStatus.visibility = View.GONE

                circularFrame.visibility = View.VISIBLE
                val p = vm.progress.value ?: 0
                circularBar.isIndeterminate = false
                circularBar.progress = p
                circularText.text = getString(R.string.progress_percent, p)
            } else {
                // Normal running state: show linear progress bar and hide circular progress bar
                circularFrame.visibility = View.GONE
                linearBar.visibility = View.VISIBLE
                linearStatus.visibility = View.VISIBLE

                when (status) {
                    "Preparing" -> {
                        linearBar.isIndeterminate = true
                        linearStatus.text = status
                    }
                    "Work" -> {
                        linearBar.isIndeterminate = false
                        val p = vm.progress.value ?: 0
                        linearBar.progress = p
                        linearStatus.text = getString(R.string.progress_percent, p)
                    }
                    "背景工作結束" -> {
                        linearBar.isIndeterminate = false
                        linearBar.progress = 100
                        linearStatus.text = status
                    }
                    else -> {
                        linearBar.isIndeterminate = false
                        linearBar.progress = 0
                        linearStatus.text = status
                    }
                }
            }
        }

        vm.progress.observe(viewLifecycleOwner) { p ->
            val status = vm.status.value
            if (status == "Work") {
                linearBar.isIndeterminate = false
                linearBar.progress = p
                linearStatus.text = getString(R.string.progress_percent, p)
            } else if (status == "Cancelled") {
                circularBar.isIndeterminate = false
                circularBar.progress = p
                circularText.text = getString(R.string.progress_percent, p)
            }
        }
    }
}
