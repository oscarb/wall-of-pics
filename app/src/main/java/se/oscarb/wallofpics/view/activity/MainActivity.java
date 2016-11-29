package se.oscarb.wallofpics.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import se.oscarb.wallofpics.R;
import se.oscarb.wallofpics.databinding.ActivityMainBinding;
import se.oscarb.wallofpics.model.Photo;
import se.oscarb.wallofpics.view.adapter.ThumbnailsAdapter;
import se.oscarb.wallofpics.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this, this);

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
    public void onDataChanged(List<Photo> photos) {
        // TODO: Update list of photos
        ThumbnailsAdapter adapter = (ThumbnailsAdapter) binding.recyclerViewPhotos.getAdapter();
        adapter.setPhotoList(photos);
        adapter.notifyDataSetChanged();
    }
}
