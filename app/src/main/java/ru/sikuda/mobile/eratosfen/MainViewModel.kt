package ru.sikuda.mobile.eratosfen

import android.os.SystemClock.sleep
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var text = MutableLiveData<String>()
    val settext: LiveData<String>
        get() = text

    fun RunCalc(){

        viewModelScope.launch {

            delay(3000)
            text.postValue("3s emulate")
            //calculateSuspend()
        }

    }

    fun RunCalcSync(){
        Calculate()
    }

//    private suspend fun calculateSuspend(){
//        val n: Int = 50_000_000
//        val array: Array<Int> = Array(n+1) { 1 }
//        array[0] = 0
//        array[1] = 0
//
//        println("Start")
//        val timeBegin = System.currentTimeMillis()
//        var i: Int = 2
//        while ( i <= n ) {
//            if (array[i] == 1) {
//                val sq: Long = i.toLong() * i
//                if (sq <= n) {
//                    var m: Int = sq.toInt()
//                    while (m <= n) {
//                        array[m] = 0
//                        m += i
//                    }
//                }
//            }
//            i += 1
//        }
//        val timeEnd = System.currentTimeMillis()
//        val diff: Double = (timeEnd.toDouble() - timeBegin) / 1000
//        text.postValue(diff.toString())
//    }

    private fun Calculate() {

        val n = 50_000_000
        val array: Array<Int> = Array(n+1) { 1 }
        array[0] = 0
        array[1] = 0

        //println("Start")
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
        text.postValue(diff.toString())
    }
}
