package com.example.columnlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ListViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(UiStateList())
    val uiState: StateFlow<UiStateList> = _uiState.asStateFlow()
    val itemList: MutableList<Int> = mutableListOf(0)
    var number by mutableStateOf(0)
        private set

    fun increases() {
        number = 10
        val sai = _uiState.value.number.plus(number)
        itemList.add(sai)
        _uiState.update {
            it.copy(
                number = sai,
                numberList = itemList
            )
        }
    }

    init {
        refresh()
    }
    fun refresh(){
        itemList.clear()
        _uiState.update {
            it.copy(
                number = 0,
                numberList = itemList
            )
        }
    }

    fun remove(index:Int){
        itemList.removeAt(index)
        val sai = _uiState.value.number.minus(10)
        _uiState.update {
            it.copy(
                number = sai,
                numberList = itemList
            )
        }
    }
}