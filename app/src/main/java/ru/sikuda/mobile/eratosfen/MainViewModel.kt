package ru.sikuda.mobile.eratosfen

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private var text = MutableLiveData<String>()
    val settext: LiveData<String>
        get() = text

    //run async routing
    fun RunCalc(){
        viewModelScope.launch {
            text.postValue("Async calc...")
            val result = withContext( Dispatchers.Default ) {
                return@withContext Calculate()
            }
            text.postValue("Async-"+result)
        }
    }

    //run sync routing
    fun RunCalcSync(){
        text.setValue("Sync calc...")
        text.setValue("Sync-"+Calculate())
    }

    //run execution
    private fun Calculate(): String {

        val n = 50_000_000
        val array: Array<Int> = Array(n+1) { 1 }
        array[0] = 0
        array[1] = 0

        val timeBegin = System.currentTimeMillis()
        var i = 2
        while ( i <= n ) {
            if (array[i] == 1) {
                val sq: Long = i.toLong() * i
                if (sq <= n) {
                    var m: Int = sq.toInt()
                    while (m <= n) {
                        array[m] = 0
                        m += i
                    }
                }
            }
            i += 1
        }
        val timeEnd = System.currentTimeMillis()
        val diff: Double = (timeEnd.toDouble() - timeBegin) / 1000
        return diff.toString()
    }
}
