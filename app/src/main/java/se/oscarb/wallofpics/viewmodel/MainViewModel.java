package se.oscarb.wallofpics.viewmodel;

import android.content.Context;

import java.util.List;

import se.oscarb.wallofpics.model.Photo;

public class MainViewModel implements ViewModel {

    private Context context;
    private DataListener dataListener;

    public MainViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
    }

    @Override
    public void destroy() {
        // Unsubscribe from stuff
    }


    public interface DataListener {
        void onDataChanged(List<Photo> photos);
    }

}
