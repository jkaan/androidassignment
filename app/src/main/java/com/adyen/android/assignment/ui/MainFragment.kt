package com.adyen.android.assignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adyen.android.assignment.R
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private val adapter = MainListAdapter()
    private var currentViewState: MainViewState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_search_results.adapter = adapter

        savedInstanceState?.getParcelable<MainViewState>(KEY_STATE)?.run(this::render)
    }

    override fun onStart() {
        super.onStart()

        search_button.setOnClickListener {
            viewModel.handleIntent(Search(view_search_input.text.toString()))
            val imm: InputMethodManager? = getSystemService(
                requireContext(),
                InputMethodManager::class.java
            )
            imm?.hideSoftInputFromWindow(view_search_input.windowToken, 0)
            view_search_input.clearFocus()
        }

        viewModel.viewState.observe(this, Observer(::render))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentViewState?.let {
            outState.putParcelable(KEY_STATE, it)
        }
    }

    private fun render(viewState: MainViewState) {
        currentViewState = viewState
        view_header.text = viewState.header
        view_results_amount.text = getString(R.string.results_text, viewState.totalResults)
        adapter.submitList(viewState.recommendedItems)
    }

    companion object {
        const val KEY_STATE = "KEY_STATE"
    }
}
