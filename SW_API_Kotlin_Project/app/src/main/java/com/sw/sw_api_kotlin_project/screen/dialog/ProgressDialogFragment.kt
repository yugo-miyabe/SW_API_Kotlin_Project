package com.sw.sw_api_kotlin_project.screen.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.sw.sw_api_kotlin_project.R

class ProgressDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.Theme_SW_API_Kotlin_Project).apply {
            setContentView(R.layout.dialog_progress)
            window?.setBackgroundDrawableResource(R.color.dialog_background)
        }
        isCancelable = false

        return dialog
    }

    companion object {
        private const val TAG = "ProgressDialogFragment"

        fun show(targetFragment: Fragment) {
            val fragmentManager = targetFragment.childFragmentManager
            (fragmentManager.findFragmentByTag(TAG) as? DialogFragment)?.dismiss()

            ProgressDialogFragment().showNow(fragmentManager, TAG)
        }

        fun dismiss(targetFragment: Fragment) {
            val fragmentManager = targetFragment.childFragmentManager
            (fragmentManager.findFragmentByTag(TAG) as? ProgressDialogFragment)?.dismiss()
        }


    }


}