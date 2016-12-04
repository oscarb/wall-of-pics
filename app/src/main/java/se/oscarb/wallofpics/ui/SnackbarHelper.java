package se.oscarb.wallofpics.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import se.oscarb.wallofpics.viewmodel.MainViewModel;

public class SnackbarHelper implements MainViewModel.ErrorListener {

    Context context;
    CoordinatorLayout coordinatorLayout;


    public SnackbarHelper(Context context, CoordinatorLayout coordinatorLayout) {
        this.context = context;
        this.coordinatorLayout = coordinatorLayout;
    }

    @Override
    public void onError(String errorMessage) {
        showSnackbar(errorMessage);
    }

    @Override
    public void onError(int errorResourceId) {
        showSnackbar(errorResourceId);
    }

    public void showSnackbar(String msg) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void showSnackbar(int msgResourceId) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, msgResourceId, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
