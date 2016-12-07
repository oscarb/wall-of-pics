package se.oscarb.wallofpics.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import org.parceler.Parcels;

import se.oscarb.wallofpics.model.Photo;
import se.oscarb.wallofpics.view.activity.DetailActivity;


public class ItemThumbnailViewModel extends BaseObservable implements ViewModel {

    private Context context;
    private Photo photo;

    public ItemThumbnailViewModel(Context context, Photo photo) {
        this.context = context;
        this.photo = photo;

        int visibility = photo.isNsfw() ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter("imageUri")
    public static void setImageUri(SimpleDraweeView simpleDraweeView, String uri) {
        simpleDraweeView.setImageURI(Uri.parse(uri));
    }


    public String getImageUrl() {
        return photo.getThumbnailUrl();
    }

    public boolean isNsfw() {
        return photo.isNsfw();
    }

    public int getBackgroundColor() {
        // Set each items background to a random shade of black to create the checker pattern
        return Color.argb((int) Math.round(Math.random() * 255), 0, 0, 0);
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("PHOTO", Parcels.wrap(photo));

        context.startActivity(intent);
    }

    // Recycle viewModel within adapter
    public void setPhoto(Photo photo) {
        this.photo = photo;
        notifyChange();
    }

    @Override
    public void destroy() {
        // No asynchronous calls - no need to do anything
    }
}
