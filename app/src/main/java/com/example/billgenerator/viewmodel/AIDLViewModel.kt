package com.example.billgenerator.viewmodel

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.innobixaidl.MyAidl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AIDLViewModel @Inject constructor() : ViewModel() {

    val responseText = MutableLiveData<String?>()

    private var aidlService: MyAidl? = null
    private val aidlServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("aidlServiceConnection", "onServiceConnected")
            try {
                aidlService = MyAidl.Stub.asInterface(service)
                val response = aidlService?.string
                responseText.postValue(response)
                Log.i("aidlServiceConnection", "Response: $response")
            } catch (e: Exception) {
                Log.i("aidlServiceConnection", "onServiceConnected error: $e")
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("aidlServiceConnection", "onServiceDisconnected")
            aidlService = null
        }
    }

    fun bindToService(context: Context) {
        try {
            val intent = Intent("com.example.serveraidl.MyAidl")
            val explicitIntent = convertImplicitIntentToExplicitIntent(context, intent)
            if (explicitIntent != null) {
                context.bindService(explicitIntent, aidlServiceConnection, Context.BIND_AUTO_CREATE)
            }
        } catch (e: Exception) {
            Log.i("bindToFiscalService", "e: $e")
        }
    }

    fun unbindService(context: Context) {
        context.unbindService(aidlServiceConnection)
    }

    private fun convertImplicitIntentToExplicitIntent(
        context: Context,
        implicitIntent: Intent
    ): Intent? {
        val packageManager = context.packageManager
        val resolveInfoList = packageManager.queryIntentServices(implicitIntent, 0)
        if (resolveInfoList == null || resolveInfoList.size != 1) {
            return null
        }
        val serviceInfo = resolveInfoList[0]
        val component =
            ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name)
        val explicitIntent = Intent(implicitIntent)
        explicitIntent.component = component
        return explicitIntent
    }

}