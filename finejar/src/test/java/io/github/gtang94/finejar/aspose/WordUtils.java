package io.github.gtang94.finejar.aspose;

import com.aspose.imaging.*;
import com.aspose.imaging.brushes.SolidBrush;
import com.aspose.words.*;
import com.aspose.words.License;
import com.aspose.words.Shape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class WordUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    private static final String defaultWaterMarkFontFamily = "Times New Roman";

    public static void main(String[] args) {
        String wordFile1 = "D:\\tmp\\word\\ISMS202202.docx";
        String wordFile2 = "D:\\tmp\\word\\TheBrain_12_TransitionGuide_V8.docx";
        String outPDFFile = "D:\\tmp\\word\\word2PDF.pdf";
//        word2PDF(wordFile2, outPDFFile, 1, -1);

        String outImagesFile = "D:\\tmp\\word\\word2Images.png";
//        word2Images(wordFile2, outImagesFile, SaveFormat.PNG);

//        wordAddWaterMark(wordFile1, "233333333333");

//        wordAddWaterMark(wordFile1, new File(outImagesFile));

    }

    public static void word2PDF(String wordFile, String pdfFile, int startPageNo, int endPageNo) {

        license();

        try {
            Document doc = new Document(wordFile);
            int pageCount = doc.getPageCount();
            if (endPageNo > pageCount || Objects.equals(endPageNo, -1)) {
                endPageNo = pageCount;
            }

            PdfSaveOptions saveOptions = new PdfSaveOptions();
            doc.save(pdfFile, saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void word2Images(String wordFile, String imagesFile, int saveFormat) {
        license();
        try {
            Document doc = new Document(wordFile);
            ImageSaveOptions saveOptions = new ImageSaveOptions(saveFormat);
            doc.save(imagesFile, saveFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wordAddWaterMark(String wordFile, String waterMarkText) {
        license();
        try {
            Document document = new Document(wordFile);
            TextWatermarkOptions options = new TextWatermarkOptions();
            options.setFontSize(36);
            options.setFontFamily(defaultWaterMarkFontFamily);
            options.setColor(java.awt.Color.RED);
            options.setLayout(WatermarkLayout.DIAGONAL);
            options.isSemitrasparent(true);

            document.getWatermark().setText(waterMarkText, options);

            document.save(wordFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wordAddWaterMark(String wordFile, File waterMarkImageFile) {
        license();
        try {
            Document document = new Document(wordFile);

            ImageWatermarkOptions options = new ImageWatermarkOptions();
            options.isWashout(false);

            BufferedImage image = ImageIO.read(waterMarkImageFile);

            document.getWatermark().setImage(image, options);

            document.save(wordFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void images2PDF(List<String> imagesFileList, String pdfFile) {
        license();
        try {
            Document document = new Document();
            DocumentBuilder builder = new DocumentBuilder(document);
            for (String imagesFile : imagesFileList) {
                builder.insertImage(imagesFile);
                builder.writeln();
            }
            document.save(pdfFile, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imageAddWaterMark(String imageFile, String newImageFile, String waterMarkText) {
        Image image = Image.load(imageFile);
        Graphics graphics = new Graphics(image);
        com.aspose.imaging.Font font = new com.aspose.imaging.Font(defaultWaterMarkFontFamily, 16, FontStyle.Bold);
        SolidBrush brush = new SolidBrush();

        StringFormat format = new StringFormat();
        format.setAlignment(StringAlignment.Center);
        format.setFormatFlags(StringFormatFlags.MeasureTrailingSpaces);
        graphics.drawString(waterMarkText, font, brush,
                new RectangleF(0, 0, image.getWidth(), image.getHeight()), format);
        image.save(newImageFile);
    }

    public static void imageFormatConvert(String imageFile, String newImageFile) {
        license();
        try {
            Document document = new Document();
            DocumentBuilder builder = new DocumentBuilder(document);
            Shape shape = builder.insertImage(imageFile);
            ImageData imageData = shape.getImageData();
            imageData.save(newImageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean license() {
        License license = new License();
        try {
            license.setLicense(licenseFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
