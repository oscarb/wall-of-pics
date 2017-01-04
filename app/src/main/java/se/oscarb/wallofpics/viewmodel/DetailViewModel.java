package se.oscarb.wallofpics.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import se.oscarb.wallofpics.model.Photo;
import se.oscarb.wallofpics.util.HtmlCompat;

public class DetailViewModel implements ViewModel {

    private static final float urlRelativeSize = 0.7f;

    private Context context;
    private SystemUiHandler uiHandler;

    private Photo photo;


    private boolean isActionBarTitleTruncated;


    public DetailViewModel(Context context, final Photo photo) {
        this.context = context;
        this.photo = photo;
    }

    public DetailViewModel(Context context, Photo photo, SystemUiHandler uiHandler) {
        this.photo = photo;
        this.uiHandler = uiHandler;
        this.context = context;
    }

    @BindingAdapter("imageUri")
    public void setImageUri(SimpleDraweeView simpleDraweeView, String uri) {

        simpleDraweeView.setImageURI(uri);
    }

    public String getImageUrl() {
        return photo.getImageUrl();
    }

    @SuppressWarnings("deprecation")
    public SpannableString getDescription() {
        String title = isActionBarTitleTruncated() ? photo.getName() + "\n" : "";

        // Strip HTML from description
        String description = photo.getDescription().equals("") ? "" : HtmlCompat.stripHtml(photo.getDescription() + "\n");
        String information = title + description + "© " + photo.getUser().getName() + " / 500px \n\n";
        information += photo.getUrl();

        SpannableString spannableString = new SpannableString(information);
        spannableString.setSpan(
                new RelativeSizeSpan(urlRelativeSize),
                information.length() - photo.getUrl().length(),
                information.length(),
                0);

        return spannableString;
    }

    public void onPhotoClick(View view) {
        if (uiHandler != null) {
            uiHandler.toggleSystemUi();
        }
    }

    public boolean isActionBarTitleTruncated() {
        return isActionBarTitleTruncated;
    }

    public void setActionBarTitleTruncated(boolean actionBarTitleTruncated) {
        isActionBarTitleTruncated = actionBarTitleTruncated;
    }

    @Override
    public void destroy() {
        // Stop asynchronous stuff
        this.context = null;
    }

    public interface SystemUiHandler {
        void toggleSystemUi();

        void showSystemUi();

        void hideSystemUi();

    }
}
