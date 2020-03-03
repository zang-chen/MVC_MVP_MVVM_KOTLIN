package com.csd.mvc

import org.json.JSONObject

class ResponseData {

    var ret: String? = null
    var msg: String? = null

    companion object {
        fun paseFromJson(jsonObject: JSONObject): ResponseData {
            var responseData: ResponseData = ResponseData()
            responseData.ret = jsonObject.optString("ret")
            responseData.msg = jsonObject.optString("msg")
            return responseData
        }
    }
}