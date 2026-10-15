package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel

class DieViewModel : ViewModel() {
    val currentRoll = MutableLiveData<Int>().apply {
        value = 0
    }

    fun setRoll (roll: Int) {
        currentRoll.value = roll
        //Use this to change information.
    }

    fun getRoll () : LiveData<Int> = currentRoll
        //Use this to check that the updates have happened
        // We do not want this to be mutable.

}