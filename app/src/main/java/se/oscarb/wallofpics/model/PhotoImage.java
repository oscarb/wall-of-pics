package se.oscarb.wallofpics.model;

import org.parceler.Parcel;

@Parcel
public class PhotoImage {
    int size;
    String url;
    String format;

    public int getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }
}
