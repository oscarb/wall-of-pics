package se.oscarb.wallofpics.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.oscarb.wallofpics.BuildConfig;
import se.oscarb.wallofpics.R;
import se.oscarb.wallofpics.data.FiveHundredPxService;
import se.oscarb.wallofpics.data.FiveHundredPxServiceGenerator;
import se.oscarb.wallofpics.model.Photo;
import se.oscarb.wallofpics.model.PhotoListing;

public class MainViewModel implements ViewModel {

    public ObservableInt progressBarVisibility;
    public ObservableInt emptyStateVisibility;
    public ObservableField<String> emptyStateText;
    private Context context;
    private DataListener dataListener;

    public MainViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;

        progressBarVisibility = new ObservableInt(View.GONE);
        emptyStateVisibility = new ObservableInt(View.GONE);
        emptyStateText = new ObservableField<>("");

    }


    public boolean onSearchAction(TextView view, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            String query = view.getText().toString();

            if (!query.trim().isEmpty()) {
                searchServiceForPictures(query);
            }

            view.clearFocus();
            handled = true;
        }
        return handled;
    }

    private void searchServiceForPictures(String query) {
        // Set query
        int[] imageSizes = {1, 4};
        // TODO: Set imageSizes automatically

        FiveHundredPxService service = FiveHundredPxServiceGenerator.getService();

        progressBarVisibility.set(View.VISIBLE);
        emptyStateVisibility.set(View.GONE);

        // Talk to API
        Call<PhotoListing> call = service.getListing(BuildConfig.CONSUMER_KEY, query, imageSizes);

        // Run request asynchronously
        call.enqueue(new PhotoSearchCallback());

    }






    @Override
    public void destroy() {
        // Unsubscribe from stuff
    }


    public interface DataListener {
        void onDataChanged(List<Photo> photos);
    }

    private class PhotoSearchCallback implements Callback<PhotoListing> {
        @Override
        public void onResponse(Call<PhotoListing> call, Response<PhotoListing> response) {
            // Hide progressbar
            progressBarVisibility.set(View.GONE);

            if (!response.isSuccessful()) {
                /*
                Snackbar snackbar = Snackbar.make(binding.coordinatorLayout, R.string.error_message_response_unsuccessful, Snackbar.LENGTH_LONG);
                snackbar.show();
                */
                return;
            }

            // Get list of photos and save them for configuration changes
            PhotoListing photoListing = response.body();
            //DataHolder.getInstance().setPhotoList(photoListing.getPhotos());

            if (photoListing.getTotalItems() == 0) {
                // TODO: Get and display the term that was searched for (No results for "monkeys")
                emptyStateText.set(context.getString(R.string.no_results));
            }

            if (dataListener != null) {
                dataListener.onDataChanged(photoListing.getPhotos());
            }

        }

        @Override
        public void onFailure(Call<PhotoListing> call, Throwable t) {
            progressBarVisibility.set(View.GONE);
/*
            Snackbar snackbar = Snackbar.make(binding.coordinatorLayout, R.string.error_message_call_failure, Snackbar.LENGTH_LONG);
            snackbar.show();*/
            t.printStackTrace();
        }
    }

}
