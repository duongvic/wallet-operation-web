package vn.mog.ewallet.operation.web.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageOrientationUtil {

  //https://beradrian.wordpress.com/2008/11/14/rotate-exif-images/
  public static final String[] orientationDescription = {
      "Top, left side (Horizontal / normal)",
      "Top, right side (Mirror horizontal)",
      "Bottom, right side (Rotate 180)",
      "Bottom, left side (Mirror vertical)",
      "Left side, top (Mirror horizontal and rotate 270 CW)",
      "Right side, top (Rotate 90 CW)",
      "Right side, bottom (Mirror horizontal and rotate 90 CW)",
      "Left side, bottom (Rotate 270 CW)"};
  private static final Logger LOG = LogManager.getLogger(ImageOrientationUtil.class);
  private static final int NONE = 0;
  private static final int HORIZONTAL = 1;
  private static final int VERTICAL = 2;
  private static final int[][] OPERATIONS = new int[][]{
      new int[]{0, NONE},
      new int[]{0, HORIZONTAL},
      new int[]{180, NONE},
      new int[]{0, VERTICAL},
      new int[]{90, HORIZONTAL},
      new int[]{90, NONE},
      new int[]{-90, HORIZONTAL},
      new int[]{-90, NONE},
  };

  /**
   * Checks the orientation of the image and corrects it if necessary.
   * <p>If the orientation of the image does not need to be corrected, no operation will be performed.</p>
   */
  public static BufferedImage correctOrientation(InputStream inputStream, byte[] bytes) throws ImageProcessingException, IOException, MetadataException {
    Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
    if (metadata != null && metadata.containsDirectoryOfType(ExifIFD0Directory.class)) {
      // Get the current orientation of the image
      Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
      int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);

      // Create a buffered image from the input stream
      ImageIcon icon = new ImageIcon(bytes);

      int bimgWidth = icon.getIconWidth();
      int bimgHeight = icon.getIconHeight();
      if (icon.getIconHeight() > icon.getIconWidth()) {
        bimgWidth = icon.getIconHeight();
      } else {
        bimgHeight = icon.getIconWidth();
      }

      BufferedImage bimg;
      if (orientation == 1) {
        bimg = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_BYTE_INDEXED);
      } else {
        bimg = new BufferedImage(bimgWidth, bimgHeight, BufferedImage.TYPE_BYTE_INDEXED);
      }

      if (icon != null) {
        icon.paintIcon(null, bimg.getGraphics(), 0, 0);
      }

      // Get the current width and height of the image
      int[] imageSize = {icon.getIconWidth(), icon.getIconHeight()};
      int widthOrigin = imageSize[0];
      int heightOrigin = imageSize[1];

      // Determine which correction is needed
      AffineTransform t = new AffineTransform();
      switch (orientation) {
        case 1:
          // no correction necessary skip and return the image
          return bimg;
        case 2: // Flip X
          t.scale(-1.0, 1.0);
          t.translate(-widthOrigin, 0);
          return transform(bimg, t, orientation);
        case 3: // PI rotation
          t.translate(widthOrigin, heightOrigin);
          t.rotate(Math.PI);
          BufferedImage transform3 = transform(bimg, t, orientation);
          javaxt.io.Image image3 = new javaxt.io.Image(transform3);
          image3.crop(0, 0, widthOrigin, heightOrigin);
          return image3.getBufferedImage();
        case 4: // Flip Y
          t.scale(1.0, -1.0);
          t.translate(0, -heightOrigin);
          BufferedImage transform4 = transform(bimg, t, orientation);
          javaxt.io.Image image4 = new javaxt.io.Image(transform4);
          image4.crop(0, 0, widthOrigin, heightOrigin);
          return image4.getBufferedImage();
        case 5: // - PI/2 and Flip X
          t.rotate(-Math.PI / 2);
          t.scale(-1.0, 1.0);
          BufferedImage transform5 = transform(bimg, t, orientation);
          javaxt.io.Image image5 = new javaxt.io.Image(transform5);
          image5.crop(0, 0, heightOrigin, widthOrigin);
          return image5.getBufferedImage();
        case 6: // -PI/2 and -width
          t.translate(heightOrigin, 0);
          t.rotate(Math.PI / 2);
          BufferedImage transform6 = transform(bimg, t, orientation);
          javaxt.io.Image image6 = new javaxt.io.Image(transform6);
          image6.crop(0, 0, heightOrigin, widthOrigin);
          return image6.getBufferedImage();
        case 7: // PI/2 and Flip
          t.scale(-1.0, 1.0);
          t.translate(heightOrigin, 0);
          t.translate(0, widthOrigin);
          t.rotate(3 * Math.PI / 2);
          BufferedImage transform7 = transform(bimg, t, orientation);
          javaxt.io.Image image7 = new javaxt.io.Image(transform7);
          image7.crop(0, 0, heightOrigin, widthOrigin);
          return image7.getBufferedImage();
        case 8: // PI / 2
          t.translate(0, widthOrigin);
          t.rotate(3 * Math.PI / 2);
          BufferedImage transform8 = transform(bimg, t, orientation);
          javaxt.io.Image image8 = new javaxt.io.Image(transform8);
          image8.crop(0, 0, heightOrigin, widthOrigin);
          return image8.getBufferedImage();
        default:
          return bimg;
      }
    }
    return null;
  }

  public static byte[] rotateCorrectImage(final InputStream inputStream, byte[] bytes) {
    try {
      BufferedImage bufferedImage = correctOrientation(inputStream, bytes);
      if (bufferedImage != null) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
      }
    } catch (IOException e) {
      LOG.error("IOException", e);
    } catch (MetadataException | ImageProcessingException e) {
      LOG.error("MetadataException", e);
    }
    return null;
  }

  /**
   * Performs the tranformation
   */
  private static BufferedImage transform(BufferedImage bimage, AffineTransform transform, int orientation) throws IOException {

    // Create an transformation operation
    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);

    // Create an instance of the resulting image, with the same width, height and image type than the referenced one
    BufferedImage destinationImage = new BufferedImage(bimage.getWidth(), bimage.getHeight(), bimage.getType());
    op.filter(bimage, destinationImage);

    return destinationImage;
  }

  public static String resizeImage(String base64Image, int width, int height) {

    byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
    String imageAfter = excuteResizeByteImage(imageBytes, width, height);

    return imageAfter != null ? imageAfter : base64Image;
  }

  public static String resizeImage(byte[] imageBytes, int width, int height) {

    String imageAfter = excuteResizeByteImage(imageBytes, width, height);
    return imageAfter != null ? imageAfter : new String(imageBytes, StandardCharsets.UTF_8);
  }

  private static String excuteResizeByteImage(byte[] imageBytes, int width, int height) {
    try {
      BufferedImage bimg = ImageIO.read(new ByteArrayInputStream(imageBytes));
      javaxt.io.Image image = new javaxt.io.Image(bimg);
      if (width > 0) {
        image.setWidth(width); //set width, adjusts height to maintain aspect ratio
      }
      if (height > 0) {
        image.setHeight(height); //set height, adjusts width to maintain aspect ratio
      }

//      image.resize(75, 150); //set width and height to whatever you want
      BufferedImage resizebimg = image.getBufferedImage();

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(resizebimg, "png", baos);
      return DatatypeConverter.printBase64Binary(baos.toByteArray());
    } catch (IOException e) {
      LOG.error("IOException", e);
    }
    return null;
  }

}
