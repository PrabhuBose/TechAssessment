package com.senthil.prabhu.mobile.app.android.technologyassessment

import com.google.common.truth.Truth
import com.senthil.prabhu.mobile.app.android.technologyassessment.api.Api
import com.senthil.prabhu.mobile.app.android.technologyassessment.constants.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

class ApiUnitTest {

    @Test
    fun `can getArticle one week`() {
        val api = Api.create()
        GlobalScope.launch {
            val response = api.getArticles(Constants.ONE_WEEK, Constants.NY_API_KEY)
            Truth.assertThat(response.status).isEqualTo(Constants.STATUS_OK)
        }

    }

    @Test
    fun `can getArticle one month`() {
        val api = Api.create()
        GlobalScope.launch {
            val response = api.getArticles(Constants.ONE_MONTH, Constants.NY_API_KEY)
            Truth.assertThat(response.status).isEqualTo(Constants.STATUS_OK)
        }

    }

    @Test
    fun `can getArticle one day`() {
        val api = Api.create()
        GlobalScope.launch {
            val response = api.getArticles(Constants.TODAY, Constants.NY_API_KEY)
            Truth.assertThat(response.status).isEqualTo(Constants.STATUS_OK)
        }
    }

}