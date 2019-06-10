package com.example.woopsicrediapp.core.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel(), Observable {

    private val registry = PropertyChangeRegistry()
    private val disposables = CompositeDisposable()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    fun notifyPropertyChange(resId: Int) {
        registry.notifyChange(this, resId)
    }

    fun registerDisposable(d: Disposable) {
        disposables.add(d)
    }

    fun clearDisposables() {
        disposables.clear()
    }

}