package cn.dalelegamalek.demo.model;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiinterface_home {

    @FormUrlEncoded
    @POST("Gmalek_service_provider.php")
    Call<ResponseBody> getcontacts(@Field("name") String name, @Field("password") String password, @Field("address") String address, @Field("phone") String phone,
                                   @Field("date") String date, @Field("email") String email, @Field("image") String image,@Field("category")int category
    );
    @FormUrlEncoded
    @POST("Gmalek_login_service_provider.php")
    Call<List<contact_home>>getcontacts_login(@Field("phonee") String phonee, @Field("password") String password);
    @FormUrlEncoded
    @POST("Gmalek_add_order.php")
    Call<ResponseBody> getcontactsadd(@Field("name") String name, @Field("category") int category,
                                      @Field("service_provider_id") int service_provider_id,
                                      @Field("details") String details,
                                   @Field("country") String country, @Field("city") String city, @Field("phone") String phone, @Field("price") Double price,
                                      @Field("points") int points, @Field("address") String address,@Field("image") String image
    );
    @FormUrlEncoded
    @POST("Gmalek_get_provider_orders.php")
    Call<List<contact_order>>getcontacts_order(@Field("id") int id);
    @FormUrlEncoded
    @POST("Gmalek_delete_order.php")
    Call<ResponseBody> delete_category(@Field("id") int id);
    @FormUrlEncoded
    @POST("Gmalek_get_user.php")
    Call<List<contact_home>>getcontacts_getuser(@Field("id") int id);
    @FormUrlEncoded
    @POST("Gamalek_pull_point.php")
    Call<ResponseBody> getcontacts_send(@Field("id") int id,@Field("id_sender") int id_sender , @Field("points")int points);
    @GET("Gmalek_first_category.php")
    Call<List<content_category>> getcontacts_allfirst();
}

