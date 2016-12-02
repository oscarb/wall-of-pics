package se.oscarb.wallofpics.view.activity;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;

import org.parceler.Parcels;

import se.oscarb.wallofpics.R;
import se.oscarb.wallofpics.databinding.ActivityDetailBinding;
import se.oscarb.wallofpics.model.Photo;
import se.oscarb.wallofpics.viewmodel.DetailViewModel;


public class DetailActivity extends AppCompatActivity implements DetailViewModel.SystemUiHandler {

    // Whether or not the system UI should be auto-hidden after X  milliseconds.
    private static final boolean AUTO_HIDE = false;
    // The number of milliseconds to wait after user interaction before hiding the system UI.
    private static final int AUTO_HIDE_DELAY_MILLIS = 4000;

    // Some older devices needs a small delay between UI widget updates and a change of the status and navigation bar.
    private static final int UI_ANIMATION_DELAY = 300;

    private final Handler hideHandler = new Handler();
    private ActivityDetailBinding binding;
    private final Runnable hideStatusAndNavigationBarRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            binding.photo.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private final Runnable showActionBarAndDescriptionRunnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            binding.description.setVisibility(View.VISIBLE);
        }
    };
    private DetailViewModel detailViewModel;
    private boolean visible;

    private final Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            hideSystemUi();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Get Photo from intent and pass it to ViewModel
        Photo photo = Parcels.unwrap(getIntent().getParcelableExtra("PHOTO"));
        detailViewModel = new DetailViewModel(this, photo, this);
        binding.setDetailViewModel(detailViewModel);

        setupActionBar();

        // Set visibility of system ui to true
        visible = true;

        // Add progress bar
        GenericDraweeHierarchy hierarchy = binding.photo.getHierarchy();
        hierarchy.setProgressBarImage(new ProgressBarDrawable());

        // Change UI
        setTitle(photo.getName());

        // Let viewModel know if title in action bar has been truncated
        detailViewModel.setActionBarTitleTruncated(getSupportActionBar().isTitleTruncated());

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(AUTO_HIDE_DELAY_MILLIS);
    }


    public void toggleSystemUi() {
        if (visible) {
            hideSystemUi();
        } else {
            showSystemUi();
        }
    }

    public void hideSystemUi() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        binding.description.setVisibility(View.GONE);
        visible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showActionBarAndDescriptionRunnable);
        hideHandler.postDelayed(hideStatusAndNavigationBarRunnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    public void showSystemUi() {
        // Show the system bar
        binding.photo.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        visible = true;

        // Schedule a runnable to display UI elements after a delay
        hideHandler.removeCallbacks(hideStatusAndNavigationBarRunnable);
        hideHandler.postDelayed(showActionBarAndDescriptionRunnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        hideHandler.removeCallbacks(hideRunnable);
        hideHandler.postDelayed(hideRunnable, delayMillis);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailViewModel.destroy();
    }
}
