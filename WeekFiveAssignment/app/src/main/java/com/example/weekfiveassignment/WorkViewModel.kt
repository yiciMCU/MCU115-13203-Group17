package com.example.weekfiveassignment

import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkViewModel : ViewModel() {
    private var handlerThread: HandlerThread? = null
    private var worker: Handler? = null

    private val _progress = MutableLiveData(0)
    val progress: LiveData<Int> = _progress

    private val _status = MutableLiveData("Idle")
    val status: LiveData<String> = _status

    @Volatile
    private var running = false

    fun start() {
        if (running) return
        running = true

        if ((handlerThread == null) || (handlerThread?.isAlive == false)) {
            handlerThread = HandlerThread("VM-Work").apply { start() }
            worker = Handler(handlerThread!!.looper)
        }

        _status.postValue("Preparing")
        _progress.postValue(0)

        worker?.post {
            try {
                // 準備階段
                repeat(15) {
                    if (!running) {
                        _status.postValue("Cancelled")
                        return@post
                    }
                    Thread.sleep(100)
                }

                if (!running) {
                    _status.postValue("Cancelled")
                    return@post
                }

                // 背景工作階段
                _status.postValue("Work")
                for (i in 1..100) {
                    if (!running) {
                        _status.postValue("Cancelled")
                        return@post
                    }
                    Thread.sleep(80)
                    _progress.postValue(i)
                }

                if (running) {
                    _status.postValue("背景工作結束")
                } else {
                    _status.postValue("Cancelled")
                }
            } catch (_: InterruptedException) {
                _status.postValue("Cancelled")
            } finally {
                running = false
            }
        }
    }

    fun cancel() {
        if (running) {
            running = false
            _status.postValue("Cancelled")
        }
    }

    override fun onCleared() {
        running = false
        handlerThread?.quitSafely()
        super.onCleared()
    }
}
