package se.oscarb.wallofpics.util;

import android.os.Build;
import android.text.Html;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class HtmlCompat {

    @SuppressWarnings("deprecation")
    public static String stripHtml(String source) {
        String stripped = "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stripped = Html.fromHtml(source, FROM_HTML_MODE_LEGACY).toString();
        } else {
            stripped = Html.fromHtml(source).toString();
        }

        return stripped;
    }

}
