package se.oscarb.wallofpics.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Photo {
    int id;
    String name;
    String description;
    boolean nsfw;
    User user;
    List<PhotoImage> images;
    String url;

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

    // TODO: Prepend with API base url
    public String getUrl() {
        return (url == null) ? "" : url;
    }

    // Get URL to thumbnail or larger photo
    public String getImageUrl(int imageSize) {
        String result = images.get(0).getUrl();
        for (PhotoImage image : images) {
            result = (image.getSize() == imageSize) ? image.getUrl() : result;
        }
        return result;
    }
}
