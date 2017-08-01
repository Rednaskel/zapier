package fixtures;

import constants.DockerConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

    public static File getElementPicture(WebElement logoElem, File screenshot) throws IOException {
        BufferedImage fullImg = ImageIO.read(screenshot);
        BufferedImage elementImage = getElementImage(fullImg, logoElem.getLocation(), logoElem.getSize());
        ImageIO.write(elementImage, "png", screenshot);
        File screenshotLocation = new File("./element.png");
        FileUtils.copyFile(screenshot, screenshotLocation);
        return screenshot;
    }

    public static boolean compareScreenshots(File a, File b) throws IOException {

        Raster imageDataA = ImageIO.read(a).getData();
        Raster imageDataB = ImageIO.read(b).getData();
        if (imageDataA.getHeight() != imageDataB.getHeight()
                || imageDataA.getWidth() != imageDataB.getWidth()) {
            System.err.println("Logo element dimensions are incorrect");
            return false;
        }
        DataBuffer DataBufferA = imageDataA.getDataBuffer();
        DataBuffer DataBufferB = imageDataB.getDataBuffer();
        for(int i = 0; i< DataBufferA.getSize(); i++) {
            if (DataBufferA.getElem(i) != DataBufferB.getElem(i)) {
                System.err.println("Images are not the same");
                return false;
            }
        }
        return true;
    }

    private static BufferedImage getElementImage(BufferedImage fullImg, Point location, Dimension size) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        String browserType = System.getProperty("browser");

        int resolutionFactorsHeight = 1;
        int resolutionFactorWidth = 1;
        if(browserType.contains("local")) {
            resolutionFactorsHeight = getResolutionFactorHeight(screenSize);
            resolutionFactorWidth = getResolutionFactorWidth(screenSize);
        }
        return fullImg.getSubimage(
                resolutionFactorWidth * location.getX(),
                resolutionFactorsHeight * location.getY(),
                resolutionFactorWidth * size.getWidth(),
                resolutionFactorsHeight * size.getHeight());
    }

    private static int getResolutionFactorWidth(java.awt.Dimension screenSize) {
        return screenSize.width / DockerConstants.RESOLUTION_WIDTH;
    }

    private static int getResolutionFactorHeight(java.awt.Dimension screenSize) {
        return screenSize.height / DockerConstants.RESOLUTION_HEIGHT;
    }
}
