package com.adyen.android.assignment.ui

sealed class MainIntent
data class Search(val text: String) : MainIntent()
