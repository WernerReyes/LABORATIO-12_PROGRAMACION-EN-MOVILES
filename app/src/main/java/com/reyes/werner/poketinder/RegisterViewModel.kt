package com.reyes.werner.poketinder

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel ( context: Context
): ViewModel() {
    val inputsError = MutableLiveData<Boolean>()
    val passwordMatch = MutableLiveData<Boolean>()
    val registerSuccess = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }


    fun validateInputs(email: String, password: String, confirmPassword: String) {
        if (isEmptyInputs(email, password, confirmPassword) || !isEmailValid(email)) {
            inputsError.postValue(true)
            return
        }

        if (!isPasswordMatch(password, confirmPassword)) {
            passwordMatch.postValue(true)
            return
        }

        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)
        registerSuccess.postValue(true)
    }

    private fun isEmptyInputs(email: String, password: String, confirmPassword: String) = email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()

    private fun isEmailValid(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordMatch(password: String, confirmPassword: String) = password == confirmPassword

}