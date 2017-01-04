package se.oscarb.wallofpics.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageSizeHelperUnitTest {

    @Test
    public void pixelWidth720returnsCroppedSizeId600() throws Exception {
        ImageSizeHelper imageSizeHelper = new ImageSizeHelper();
        int actual = ImageSizeHelper.getCroppedImageSizeId(720);
        int expected = 600;

        assertEquals(expected, actual);
    }

    @Test
    public void pixelWidth500returnsCroppedSizeId440() throws Exception {
        ImageSizeHelper imageSizeHelper = new ImageSizeHelper();
        int actual = ImageSizeHelper.getCroppedImageSizeId(500);
        int expected = 440;

        assertEquals(expected, actual);
    }
}
