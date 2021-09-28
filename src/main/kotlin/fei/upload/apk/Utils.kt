package fei.upload.apk

import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 * author : fei
 * date   : 2021/9/28
 * desc   : 上传apk
 **/
private val client = OkHttpClient.Builder()
    .build()
private val gson = Gson()

class HttpResult(
    val code: Int,
    val message: String,
)

fun uploadPgyer(apiKey: String, desc: String, apk: File): Boolean {

    val body = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("_api_key", apiKey)
        .addFormDataPart("buildUpdateDescription", desc)
        .addFormDataPart("file", apk.name, apk.asRequestBody())
        .build()

    val request = Request.Builder()
        .url("https://www.pgyer.com/apiv2/app/upload")
        .post(body)
        .build()

    val call = client.newCall(request)
    val response = call.execute()
    val json = response.body?.string() ?: throw IllegalStateException("网络错误！")
    val result = gson.fromJson(json, HttpResult::class.java) ?: throw IllegalStateException("Json 解析错误！")
    if (result.code != 0) throw IllegalStateException(result.message)
    return true
}