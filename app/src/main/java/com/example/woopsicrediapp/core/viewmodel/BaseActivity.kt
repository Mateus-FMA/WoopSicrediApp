package com.example.woopsicrediapp.core.viewmodel

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onDestroy() {
        clearDisposables()
        super.onDestroy()
    }

    fun clearDisposables() {
        disposables.clear()
    }

    fun registerDisposable(d: Disposable) {
        disposables.add(d)
    }
}