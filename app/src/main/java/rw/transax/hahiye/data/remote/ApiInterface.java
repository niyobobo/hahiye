package rw.transax.hahiye.data.remote;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rw.transax.hahiye.model.UserModel;

public interface ApiInterface {

    @POST
    Call<UserModel> getLoginInfo(@Query("email") String email, @Query("password") String password);





    /**
     * Factory class for convenient creation of the Api Service interface
     * and initializing retrofit instance.
     */
    class Factory {

        private static final String BASE_URL = "https://www.transax.rw/api/v1/";

        public static ApiInterface create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
            return retrofit.create(ApiInterface.class);
        }
    }
}
