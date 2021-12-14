package com.interview.qpony.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.interview.qpony.R
import com.interview.qpony.event.Event
import com.interview.qpony.model.CurrencyItemWrapper
import com.interview.qpony.repository.FixerRepository
import com.interview.qpony.util.DateUtils
import com.interview.qpony.util.convertCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    application: Application,
    private val fixerRepository: FixerRepository,
    private val dateUtils: DateUtils
) : AndroidViewModel(application) {
    init {
        getFirstExchangeRateData()
    }

    private val exchangeRate: MutableList<CurrencyItemWrapper> = mutableListOf()

    private var _exchangeRateList = MutableLiveData<List<CurrencyItemWrapper>>()
    val exchangeRateList: LiveData<List<CurrencyItemWrapper>>
        get() = _exchangeRateList

    private var _errorApi = MutableLiveData<Event<Int>>()
    val errorApi: LiveData<Event<Int>>
        get() = _errorApi

    val currencyItemWrapper = MutableLiveData<CurrencyItemWrapper>()

    fun getExchangeRateList() : List<CurrencyItemWrapper>{
        return exchangeRate
    }

    private fun getFirstExchangeRateData() {
        getExchangeRateData(Date())
    }

    fun getNextExchangeRateData(){
        val lastExchangeRateDate = exchangeRate.lastOrNull()?.date
        lastExchangeRateDate?.let{
            if(dateUtils.isStringValidDate(it)){
                val lastDate = dateUtils.convertStringDateToFormat(it)
                val nextDate = dateUtils.decreaseOneDay(lastDate)
                getExchangeRateData(nextDate)
            }

        }

    }

    private fun getExchangeRateData(date: Date) {
        val compositeDisposable = CompositeDisposable()
        val dateParameter = dateUtils.convertDateFormatToString(date)
        val disposable = fixerRepository.getCurrencyByDate(dateParameter).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { responseData ->
                    if(responseData.isSuccessful){
                        responseData.body()?.let{
                            if(it.success){
                                val convertedCurrency = convertCurrency(responseData.body())
                                exchangeRate.addAll(convertedCurrency)
                                _exchangeRateList.value = convertedCurrency
                            }else{
                                _errorApi.value = Event(R.string.api_load_error)
                            }
                        } ?: run{
                            _errorApi.value = Event(R.string.api_load_error)
                        }
                    }else{
                        _errorApi.value = Event(R.string.api_load_error)
                    }
                    compositeDisposable.dispose()
                }
        compositeDisposable.add(disposable)
    }
}