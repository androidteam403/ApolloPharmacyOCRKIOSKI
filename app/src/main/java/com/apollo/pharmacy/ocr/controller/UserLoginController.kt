package com.apollo.pharmacy.ocr.controller

import com.apollo.pharmacy.ocr.interfaces.UserLoginListener
import com.apollo.pharmacy.ocr.model.Send_Sms_Request
import com.apollo.pharmacy.ocr.model.Send_Sms_Response
import com.apollo.pharmacy.ocr.network.ApiClient
import com.apollo.pharmacy.ocr.network.CallbackWithRetry
import com.apollo.pharmacy.ocr.utility.Constants
import retrofit2.Call
import retrofit2.Response

class UserLoginController {

    fun handleSendSmsApi(request: Send_Sms_Request, userLoginListener: UserLoginListener) {
        var service_call = ApiClient.getApiService(Constants.Send_Sms_Api)
        val call = service_call.send_sms_api(request)
        call.enqueue(object : CallbackWithRetry<Send_Sms_Response>(call) {
            override fun onFailure(call: Call<Send_Sms_Response>, t: Throwable) {
                userLoginListener.onSendSmsFailure()
            }

            override fun onResponse(call: Call<Send_Sms_Response>, response: Response<Send_Sms_Response>) {
                if (response.isSuccessful) {
                    userLoginListener.onSendSmsSuccess()
                } else {
                    userLoginListener.onSendSmsFailure()
                }
            }
        })
    }
}