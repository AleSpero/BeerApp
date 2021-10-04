package com.sperotti.alessandro.beerapp.utils

sealed class LoadingState(val additionalData : String?) {
    class Loading : LoadingState(null)
    class Idle : LoadingState(null)
    class Error(throwable : Throwable?) : LoadingState(throwable?.localizedMessage)
}