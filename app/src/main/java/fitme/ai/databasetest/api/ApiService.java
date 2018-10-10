package fitme.ai.databasetest.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    @GET("barcode")
    Observable<ResponseBody> getGoodsDetails(@Header("Authorization") String appcode,
                                            @Query("code") String code);

}
