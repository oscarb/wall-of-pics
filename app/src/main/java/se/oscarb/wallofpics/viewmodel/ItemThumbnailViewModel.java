package se.oscarb.wallofpics.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import se.oscarb.wallofpics.model.Photo;


public class ItemThumbnailViewModel extends BaseObservable implements ViewModel {

    private Context context;

    private Photo photo;

    public ItemThumbnailViewModel(Context context, Photo photo) {
        this.context = context;
        this.photo = photo;
    }

    @BindingAdapter("imageUri")
    public static void setImageUri(SimpleDraweeView simpleDraweeView, String uri) {
        simpleDraweeView.setImageURI(Uri.parse(uri));
    }


    public String getImageUrl() {
        return photo.getImageUrl(2);
    }

    public boolean isNsfw() {
        return photo.isNsfw();
    }

    public void onItemClick(View view) {
        //context.startActivity();
        Toast.makeText(context, "Clicked " + photo.getName(), Toast.LENGTH_SHORT).show();
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