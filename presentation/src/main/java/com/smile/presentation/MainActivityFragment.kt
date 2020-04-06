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
import com.past3.ketro.api.Kobserver
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
    private val characterItems = mutableListOf<CharacterUI>()

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
        characterAdapter =
            CharacterAdapter(characterItems, ::onImageLoaded, ::transitionToDetailView)
        recyclerView.layoutManager = GridLayoutManager(
            context,
            2, GridLayoutManager.VERTICAL,
            false
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = characterAdapter
        populateData()
    }

    private fun populateData() {
        viewModel.characterLiveData.observe(this,
            object : Kobserver<List<CharacterUI>>() {

                override fun onException(exception: Exception) {
                    super.onException(exception)
                    val msg = exception.message ?: getString(R.string.error_generic)
                    Toast.makeText(context, msg, Toast.LENGTH_LONG)
                        .show()
                }

                override fun onSuccess(data: List<CharacterUI>) {
                    characterItems.addAll(data)
                    characterAdapter?.notifyDataSetChanged()
                }

            })

        viewModel.failure.observe(this, Observer {
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
