package com.indisp.securiti.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T> : ViewModel(){

    private val viewState = MutableStateFlow(getInitialState())
    val state: StateFlow<T> = viewState

    abstract fun getInitialState(): T

    protected fun update(newState: T.() -> T) {
        viewState.value = viewState.value.newState()
    }
}