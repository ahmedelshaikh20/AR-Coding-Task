package com.example.ap

class NativeLib {

    /**
     * A native method that is implemented by the 'ap' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'ap' library on application startup.
        init {
            System.loadLibrary("ap")
        }
    }
}
