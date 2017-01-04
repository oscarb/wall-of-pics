package se.oscarb.wallofpics.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.oscarb.wallofpics.model.PhotoListing;

public interface FiveHundredPxService {

    // Search 500px for pictures
    @GET("photos/search")
    Call<PhotoListing> getListing(@Query("consumer_key") String consumerKey,
                                  @Query("term") String term);

    @GET("photos/search")
    Call<PhotoListing> getListing(@Query("consumer_key") String consumerKey,
                                  @Query("term") String term,
                                  @Query("image_size[]") int[] imageSizeIds);

    @GET("photos/search")
    Call<PhotoListing> getListing(@Query("consumer_key") String consumerKey,
                                  @Query("term") String term,
                                  @Query("image_size[]") int[] imageSizeIds,
                                  @Query("page") int page);
}
