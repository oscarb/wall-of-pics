package se.oscarb.wallofpics.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Test the class HtmlCompat
 */
@RunWith(MockitoJUnitRunner.class)
public class HtmlCompatUnitTest {

    @Test
    public void boldTagsAreStripped() throws Exception {
        String input = "<b>something bold</b> and wide";

        String actual = HtmlCompat.stripHtml(input);
        String expected = "something bold and wide";

        assertEquals("<b> tag was not stripped from string", expected, actual);
    }


}
