package com.ambrella.criptoapp.api

import com.ambrella.criptoapp.pojo.CoinInfoListOfData
import com.ambrella.criptoapp.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey:String = "2862ca69ac2063a406adccf3c147ae8442b1b5a597a874e533299f5a95973cc5",
        @Query(QUERY_PARAM_LIMIT) limit:Int = 10,
        @Query(QUERY_PARAM_TO_SIMVOL) tSym:String = CURRENCY

    ): Single<CoinInfoListOfData>
    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey:String = "2862ca69ac2063a406adccf3c147ae8442b1b5a597a874e533299f5a95973cc5",
        @Query(QUERY_PARAM_FROM_SIMVOLS) fSyms:String,
        @Query(QUERY_PARAM_TO_SIMVOLS) tSyms:String = CURRENCY

    ): Single<CoinPriceInfoRawData>

    companion object{
        private const val QUERY_PARAM_API_KEY="api_key"
        private const val QUERY_PARAM_LIMIT="limit"
        private const val QUERY_PARAM_TO_SIMVOL="tsym"
        private const val QUERY_PARAM_TO_SIMVOLS="tsyms"
        private const val QUERY_PARAM_FROM_SIMVOLS="fsyms"

        private const val CURRENCY="USD"
    }

}