package com.rakesh.mindbody.viewmodel

import android.app.Application
import android.app.Dialog
import android.content.DialogInterface
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.rakesh.mindbody.R
import com.rakesh.mindbody.api.APIClient
import com.rakesh.mindbody.model.Province
import com.rakesh.mindbody.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val disposable = CompositeDisposable()
    private val apiClient = APIClient()
    val provinceList = MutableLiveData<List<Province?>>()
    val countryList = MutableLiveData<List<User?>>()
    val loading = MutableLiveData<Boolean>()

    fun getProvinces(id: String) {
        loading.value = true
        disposable.add(
            apiClient.getProvinces(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Province?>>() {
                    override fun onSuccess(t: List<Province?>) {
                        provinceList.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Error : ", e.message + " " + e.printStackTrace())
                    }
                })
        )
    }

    fun getCountries() {
        loading.value = true
        disposable.add(
            apiClient.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<User?>>() {
                    override fun onSuccess(t: List<User?>) {
                        countryList.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Error : ", e.message + " " + e.printStackTrace())
                    }
                })
        )
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) { // This methods should not have any return type, = declaration would make it return that object declaration.
            Glide.with(view.context).load(url).into(view)
        }
    }
}