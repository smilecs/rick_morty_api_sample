package com.smile.presentation.base


import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

}
