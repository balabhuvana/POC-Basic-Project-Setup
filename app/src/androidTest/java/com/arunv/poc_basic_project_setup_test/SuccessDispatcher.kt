package com.arunv.poc_basic_project_setup_test

import android.content.Context
import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry
import com.adammcneilly.pokedex.utils.APIPaths
import com.adammcneilly.pokedex.utils.MockFiles
import com.arunv.poc_basic_project_setup_test.AssetReaderUtil.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().context
) : Dispatcher() {
    private val responseFilesByPath: Map<String, String> = mapOf(
        APIPaths.LOGIN_API_URL to MockFiles.LOGIN_SUCCESS,
        APIPaths.REGISTER_API_URL to MockFiles.REGISTER_SUCCESS
    )

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse: MockResponse = MockResponse().setResponseCode(404)

        val pathWithoutQueryParams: String = Uri.parse(request.path).path ?: return errorResponse
        val responseFile: String? = responseFilesByPath[pathWithoutQueryParams]
        return if (responseFile != null) {
            val responseBody: String = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        } else {
            errorResponse
        }
    }
}