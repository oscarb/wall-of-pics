package se.oscarb.wallofpics.model;

import org.parceler.Parcel;

@Parcel
public class User {
    String username;
    String firstname;
    String lastname;
    String fullname;
    public String getName() {
        return (fullname == null || fullname.trim().equals("")) ? "Unknown" : fullname.trim();
    }

}
