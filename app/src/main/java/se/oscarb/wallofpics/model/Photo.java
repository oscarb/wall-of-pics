package se.oscarb.wallofpics.model;

import org.parceler.Parcel;

import java.util.List;

import se.oscarb.wallofpics.data.FiveHundredPxServiceGenerator;

@Parcel
public class Photo {

    private static int requestedThumbnailWidth;
    private static int requestedImageWidth;

    int id;
    String name;
    String description;
    boolean nsfw;
    User user;
    List<PhotoImage> images;
    String url;

    public static int getRequestedImageWidth() {
        return requestedImageWidth;
    }

    public static void setRequestedImageWidth(int requestedImageWidth) {
        Photo.requestedImageWidth = requestedImageWidth;
    }

    public static int getRequestedThumbnailWidth() {
        return requestedThumbnailWidth;
    }

    public static void setRequestedThumbnailWidth(int requestedThumbnailWidth) {
        Photo.requestedThumbnailWidth = requestedThumbnailWidth;
    }

    public String getName() {
        return (name == null || name.trim().equals("")) ? "Untitled" : name.trim();
    }

    public String getDescription() {
        return (description == null || description.trim().equals("")) ? "" : description.trim();
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public User getUser() {
        return user;
    }

    public List<PhotoImage> getImages() {
        return images;
    }

    public String getUrl() {
        return (url == null) ? "" : FiveHundredPxServiceGenerator.BASE_URL + url;
    }

    public String getImageUrl() {
        int imageSizeId = 0;
        if (requestedImageWidth != 0) {
            imageSizeId = ImageSizeHelper.getUncroppedImageSizeId(requestedImageWidth);
        }
        return getImageUrl(imageSizeId);
    }

    public String getThumbnailUrl() {
        int imageSizeId = 0;
        if (requestedThumbnailWidth != 0) {
            imageSizeId = ImageSizeHelper.getCroppedImageSizeId(requestedThumbnailWidth);
        }
        return getImageUrl(imageSizeId);
    }

    // Get URL to thumbnail or larger photo
    public String getImageUrl(int imageSizeId) {
        String result = images.get(0).getUrl();
        for (PhotoImage image : images) {
            result = (image.getSize() == imageSizeId) ? image.getUrl() : result;
        }
        return result;
    }

}
