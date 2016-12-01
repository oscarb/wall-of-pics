package se.oscarb.wallofpics.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;

import org.parceler.Parcels;

import java.util.List;

import se.oscarb.wallofpics.R;
import se.oscarb.wallofpics.databinding.ActivityMainBinding;
import se.oscarb.wallofpics.model.Photo;
import se.oscarb.wallofpics.view.adapter.ThumbnailsAdapter;
import se.oscarb.wallofpics.view.state.MainActivityState;
import se.oscarb.wallofpics.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {

    static final String STATE = "state";
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this, this);
        binding.setViewModel(mainViewModel);

        setupRecyclerView(binding.recyclerViewPhotos);

    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new ThumbnailsAdapter();
        recyclerView.setAdapter(adapter);

        // LayoutManager
        // TODO: Add code to calculate number of spans
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ThumbnailsAdapter thumbnailsAdapter = (ThumbnailsAdapter) binding.recyclerViewPhotos.getAdapter();
        List<Photo> photos = thumbnailsAdapter.getPhotoList();

        MainActivityState state = new MainActivityState();
        state.setPhotoList(photos);

        outState.putParcelable(STATE, Parcels.wrap(state));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        MainActivityState state = Parcels.unwrap(savedInstanceState.getParcelable(STATE));
        onDataChanged(state.getPhotoList());
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != getCurrentFocus()) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 0);
        }
    }

    @Override
    public void onDataChanged(List<Photo> photos) {
        // TODO: Update list of photos
        ThumbnailsAdapter adapter = (ThumbnailsAdapter) binding.recyclerViewPhotos.getAdapter();
        adapter.setPhotoList(photos);
        adapter.notifyDataSetChanged();
/*
        photos.clear();
        photos.addAll(photoListing.getPhotos());
        adapter.notifyDataSetChanged();*/
    }
}
