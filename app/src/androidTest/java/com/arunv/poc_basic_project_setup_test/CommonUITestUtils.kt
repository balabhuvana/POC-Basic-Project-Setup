package com.arunv.poc_basic_project_setup_test

import androidx.test.espresso.Espresso

class CommonUITestUtils {
    companion object {

        fun pause(pauseTime: Int) {
            try {
                Thread.sleep(pauseTime.toLong())
            } catch (e1: InterruptedException) {
                // eat it.
            }
        }

        fun showOrHidKeyBoard(showOrHide: Boolean) {
            if (!showOrHide) {
                Espresso.closeSoftKeyboard()
            }
        }
    }
}