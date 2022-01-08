package com.smile.presentation

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.smile.presentation.adapter.CharacterAdapter
import com.smile.presentation.base.BaseFragment
import com.smile.presentation.uimodel.CharacterUI
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"

class MainActivityFragment : BaseFragment() {

    private lateinit var viewModel: MainViewModel
    private var characterAdapter: CharacterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory.get()
        ).get(MainViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        characterAdapter = CharacterAdapter(
            viewModel.adapterList,
            ::onImageLoaded,
            ::transitionToDetailView
        )
        recyclerView.layoutManager = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = characterAdapter
        populateData()
        errorHandler()
    }

    private fun populateData() {
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.adapterList.clear()
            viewModel.adapterList.addAll(it)
            characterAdapter?.notifyDataSetChanged()
        })
    }

    private fun errorHandler() {
        viewModel.failureLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, getString(R.string.error_generic), Toast.LENGTH_LONG).show()
        })
    }

    private fun transitionToDetailView(data: CharacterUI) {
        fragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(
                R.id.container, DetailCharacterFragment.getFragment(data),
                DETAIL_CHARACTER_TAG
            )
            ?.commit()
    }

    private fun onImageLoaded(id: Int, drawable: Drawable) {
        viewModel.updateLocalImage(id, drawable)
    }
}
