package com.example.calculator.screens.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private var evaluator: ExpressionEvaluator = ExpressionEvaluator()

    private val _expression = MutableLiveData<String>()
    val expression: LiveData<String>
        get() = _expression

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    init {
        _expression.value = ""
        _result.value = ""
    }

    fun onInput(input: Char) {
        _result.value += input
    }

    fun onGetResult() {
        _expression.value = _result.value
        _result.value?.let { _result.value = evaluator.evaluate(_result.value ?: "") }
    }

    fun onClearEntry() {
        if (! _result.value.isNullOrEmpty()) {
            _result.value?.let {
                var sb = StringBuilder(_result.value)
                sb.deleteCharAt(sb.length - 1)
                _result.value = sb.toString()
            }
        }
    }

    fun onAllClear() {
        _expression.value = ""
        _result.value = ""
    }
}