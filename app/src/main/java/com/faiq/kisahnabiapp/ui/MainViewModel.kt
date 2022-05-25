package com.faiq.kisahnabiapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faiq.kisahnabiapp.data.NabiRespon
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val kisahResponse = MutableLiveData<List<NabiRespon>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getKisahNabi(responHandle: (List<NabiRespon>) -> Unit, errorHandler: (Throwable) -> Unit) {
        ApiClient.getApiService().getKisahNabi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandle(it)
            }, {
                errorHandler(it)
            })
    }

    fun getData(){
        isLoading.value = true
        getKisahNabi({
            isLoading.value = true
            kisahResponse.postValue(it)
        },{
            isLoading.value = true
            isError.postValue(it)
        })
    }
}