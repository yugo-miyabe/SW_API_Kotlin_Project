package com.sw.sw_api_kotlin_project.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.sw.sw_api_kotlin_project.extnsions.collectIn
import com.sw.sw_api_kotlin_project.screen.dialog.ProgressDialogFragment

abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding> : Fragment() {

    abstract val viewModel: VM
    private var _binding: B? = null
    val binding: B
        get() = checkNotNull(_binding) {
            "bindingはonCreateView - onDestroyView間でのみアクセス可能"
        }

    abstract fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): B

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiEventList.collectIn(viewLifecycleOwner) { uiEvents ->
            uiEvents.forEach { event ->
                when (event) {
                    is BaseViewModel.UiEvent.Navigate -> {
                        navigateTo(event.navDirections)
                        viewModel.consumeEvent(event)
                    }

                    is BaseViewModel.UiEvent.ProgressDialog -> {
                        if (event.isDoShowing) {
                            ProgressDialogFragment.show(this)
                        } else {
                            ProgressDialogFragment.dismiss(this)
                        }
                        viewModel.consumeEvent(event)
                    }

                    is BaseViewModel.UiEvent.ToastMessage -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                        viewModel.consumeEvent(event)
                    }
                }
            }
        }
    }

    private fun navigateTo(actionId: NavDirections) {
        findNavController().navigate(actionId)
    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
