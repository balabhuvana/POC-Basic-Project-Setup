package com.arunv.poc_basic_project_setup_test

import android.util.Log
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


internal class MockServerDispatcher {
    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            Log.i("----> ", "Path : " + request.path)
            if (request.path == "Register") {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        "{" +
                                "\"data\": \"testworld04@reqres.in\"," + "  \"success\": true," + "  \"message\": \"Fucking User Registered\"\n" + "}"
                    )
            } else if (request.path == "api/codes") {
                return MockResponse().setResponseCode(200).setBody("{codes:FakeCode}")
            } else if (request.path == "api/number") return MockResponse().setResponseCode(200)
                .setBody("number:FakeNumber")
            return MockResponse().setResponseCode(404)
        }
    }

}