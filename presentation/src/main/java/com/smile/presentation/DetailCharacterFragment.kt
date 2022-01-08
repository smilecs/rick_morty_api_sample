package com.smile.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.smile.presentation.base.BaseFragment
import com.smile.presentation.uimodel.CharacterUI
import com.smile.presentation.util.getErrorOrCacheDrawable
import kotlinx.android.synthetic.main.detail_view_fragment.*

private const val DATA_UI_KEY = "DATA_UI_KEY_CHARACTER"
const val DETAIL_CHARACTER_TAG = "DETAIL_CHARACTER_TAG"

class DetailCharacterFragment : BaseFragment() {

    companion object {
        fun getFragment(characterUI: CharacterUI) = DetailCharacterFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DATA_UI_KEY, characterUI)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<CharacterUI>(DATA_UI_KEY)?.let {
            initViews(it)
        } ?: Toast.makeText(context, getString(R.string.error_generic), Toast.LENGTH_LONG).show()
    }

    private fun initViews(characterUI: CharacterUI) {
        bannerImgView.load(characterUI.image) {
            crossfade(true)
            error(
                getErrorOrCacheDrawable(
                    characterUI.base64,
                    resources
                )
            )
            placeholder(R.drawable.ic_directions_run_black_24dp)
            transformations(CircleCropTransformation())
            scale(Scale.FIT)
        }
        nameTxtView.text = getString(R.string.name_label, characterUI.name)
        genderTxtView.text = getString(R.string.gender_label, characterUI.gender)
        typeTxtView.text = getString(R.string.type_label, characterUI.type)
        statusTxtView.text = getString(R.string.status_label, characterUI.status)
    }
}
