package me.xnikai.counter

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViemModel: ViewModel() {

    // Balance and uppers
    private val _count = mutableStateOf(0)
    var count: State<Int> = _count

    private val _modifier = mutableStateOf(0)
    var modifier: State<Int> = _modifier

    private val _click = mutableStateOf(1)
    var click: State<Int> = _click
    // End balance and uppers

    // Uppers price
    private val _upPrice = mutableStateOf(50)
    var upPrice: State<Int> = _upPrice

    private val _clickPrice = mutableStateOf(100)
    var clickPrice = _clickPrice
    // End uppers price

    private val countFlow = flow<Int> {
        while (true){
            delay(1000L)
            _count.value += modifier.value
        }
    }

    init {
        collectFlow()
    }

    fun addCount(number: Int = click.value){
        _count.value += number
    }

    fun removeCount(number: Int){
        _count.value -= number
    }

    private fun addPrice(){
        _upPrice.value *= 2
    }

    fun addModifier(){
        if(count.value >= upPrice.value){
            removeCount(upPrice.value)
            addPrice()
            _modifier.value += 1
        }
    }

    fun addClick() {
        if(count.value >= clickPrice.value){
            removeCount(clickPrice.value)
            _clickPrice.value *= 2
            _click.value += 1
        }
    }

    private fun collectFlow(){
        viewModelScope.launch {
            countFlow.collect()
        }
    }
}