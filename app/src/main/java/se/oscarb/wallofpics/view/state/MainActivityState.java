package se.oscarb.wallofpics.view.state;

import org.parceler.Parcel;

import java.util.List;

import se.oscarb.wallofpics.model.Photo;

@Parcel
public class MainActivityState {
    List<Photo> photoList;

    public MainActivityState() {
    }

    public MainActivityState(List<Photo> photos) {
        this.photoList = photos;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
