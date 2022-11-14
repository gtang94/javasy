package io.github.gtang94.finejar.aspose;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import com.aspose.imaging.*;
import com.aspose.imaging.brushes.SolidBrush;
import com.aspose.imaging.imageoptions.*;
import com.aspose.words.*;
import com.aspose.words.License;
import com.aspose.words.Shape;
import com.google.common.collect.Lists;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

public class WordUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    private static final String defaultWaterMarkFontFamily = "Times New Roman";

    public static void main(String[] args) {
//        String wordFile1 = "D:\\tmp\\word\\ISMS202202.docx";
        String wordFile2 = "D:\\tmp\\word\\TheBrain_12_TransitionGuide_V8.docx";
//        String outPDFFile = "D:\\tmp\\word\\word2PDF.pdf";


//        word2PDF(wordFile2, "D:\\tmp\\word\\", 0, -1);

//        word2Images(wordFile2, "D:\\tmp\\word\\", SaveFormat.PNG, 0, 2);

//        wordAddWaterMark(wordFile2, "D:\\tmp\\word\\", "TEST/TEST");

//        images2PDF(
//                Lists.newArrayList("D:\\tmp\\word\\4b2636b2-5884-4735-ab3d-1649c5a2acfc\\TheBrain_12_TransitionGuide_V8-0.png", "D:\\tmp\\word\\4b2636b2-5884-4735-ab3d-1649c5a2acfc\\TheBrain_12_TransitionGuide_V8-1.png"),
//                "D:\\tmp\\word\\");

//        imageFormatConvert("D:\\tmp\\word\\4b2636b2-5884-4735-ab3d-1649c5a2acfc\\TheBrain_12_TransitionGuide_V8-0.png", "D:\\tmp\\word\\", "svg");

    }

    public static String word2PDF(String wordFile, String outputPath, int startPageNo, int endPageNo) {

        license();

        try {
            Document wordDocument = new Document(wordFile);
            int pageCount = wordDocument.getPageCount();
            if (endPageNo > pageCount || Objects.equals(endPageNo, -1) || endPageNo <0) {
                endPageNo = pageCount;
            }
            if (startPageNo > pageCount || startPageNo < 0) {
                startPageNo = 0;
            }

            PdfSaveOptions saveOptions = new PdfSaveOptions();
            PageSet pageSet = new PageSet(new PageRange(startPageNo, endPageNo));
            saveOptions.setPageSet(pageSet);

            String inputFilename = FileNameUtil.getName(wordFile);
            String filename = FileNameUtil.getPrefix(inputFilename);
            String filenameSuffix = SaveFormat.getName(SaveFormat.PDF).toLowerCase();
            String outputFile = outputPath + filename + "." + filenameSuffix;

            wordDocument.save(outputFile, saveOptions);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String word2Images(String wordFile, String outputPath, int saveFormat, int startPageNo, int endPageNo) {
        license();
        try {
            Document wordDocument = new Document(wordFile);
            Document imgDocument = new Document();

            ImageSaveOptions saveOptions = new ImageSaveOptions(saveFormat);
            int pageCount = wordDocument.getPageCount();
            if (endPageNo > pageCount || Objects.equals(endPageNo, -1) || endPageNo <0) {
                endPageNo = pageCount;
            }
            if (startPageNo > pageCount || startPageNo < 0) {
                startPageNo = 0;
            }

            String inputFilename = FileNameUtil.getName(wordFile);
            String filename = FileNameUtil.getPrefix(inputFilename);
            String filenameSuffix = SaveFormat.getName(saveFormat).toLowerCase();
            String outputTempPath = outputPath + RandomUtil.randomUUID() + File.separator;
            for (int i = startPageNo; i < endPageNo; i++) {
                PageSet pageSet = new PageSet(new PageRange(i, i+1));
                saveOptions.setPageSet(pageSet);

                String tmp = outputTempPath + filename + "-" + i + "." + filenameSuffix;
                wordDocument.save(tmp ,saveOptions);
            }

            String outputZipFile = outputPath + filename + ".zip";
            ZipUtil.zip(outputTempPath, outputZipFile);

            return outputZipFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String wordAddWaterMark(String wordFile, String outputPath, String waterMarkText) {

        return wordAddWaterMark(wordFile, outputPath, waterMarkText, defaultWaterMarkFontFamily, 36, java.awt.Color.ORANGE, WatermarkLayout.DIAGONAL, true);
    }

    public static String wordAddWaterMark(String wordFile, String outputPath, String waterMarkText,
                                          String fontFamily, int fontSize, java.awt.Color color,
                                          int watermarkLayout, boolean isSemitrasparent) {
        license();
        try {
            Document document = new Document(wordFile);
            TextWatermarkOptions options = new TextWatermarkOptions();
            options.setFontSize(fontSize);
            options.setFontFamily(fontFamily);
            options.setColor(color);
            options.setLayout(watermarkLayout);
            options.isSemitrasparent(isSemitrasparent);

            document.getWatermark().setText(waterMarkText, options);

            String inputFilename = FileNameUtil.getName(wordFile);
            String filenameSuffix = FileNameUtil.getSuffix(inputFilename);
            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;

            document.save(outputFile);
            return outputFile;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String wordAddWaterMark(String wordFile, String outputPath, File waterMarkImageFile) {
        return wordAddWaterMark(wordFile, outputPath, waterMarkImageFile, 0.0D, false);
    }

    public static String wordAddWaterMark(String wordFile, String outputPath, File waterMarkImageFile,
                                        double scale, boolean washout) {
        license();
        try {
            Document document = new Document(wordFile);

            ImageWatermarkOptions options = new ImageWatermarkOptions();
            options.setScale(scale);
            options.isWashout(washout);

            BufferedImage image = ImageIO.read(waterMarkImageFile);

            document.getWatermark().setImage(image, options);

            String inputFilename = FileNameUtil.getName(wordFile);
            String filenameSuffix = FileNameUtil.getSuffix(inputFilename);
            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;

            document.save(outputFile);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void images2PDF(List<String> imagesFileList, String outputPath) {
        license();
        try {
            Document document = new Document();
            DocumentBuilder builder = new DocumentBuilder(document);
            for (String imagesFile : imagesFileList) {
                builder.insertImage(imagesFile);
                builder.writeln();
            }

            String filename = RandomUtil.randomUUID();
            String filenameSuffix = SaveFormat.getName(SaveFormat.PDF).toLowerCase();
            String outputFile = outputPath + filename + "." + filenameSuffix;

            document.save(outputFile, SaveFormat.PDF);
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

    public static String imageFormatConvert(String imageFile, String outputPath, String format) {
        license();
        try {
            Image image = Image.load(imageFile);

            String suffix = "." + format;
            ImageOptionsBase options = null;
            switch (format) {
                case "png":
                    options = new PngOptions();
                    break;
                case "jpg":
                    options = new JpegOptions();
                    break;
                case "bmp":
                    options = new BmpOptions();
                    break;
                case "gif":
                    options = new GifOptions();
                    break;
                case "psd":
                    options = new PsdOptions();
                    break;
                case "svg":
                    options = new SvgOptions();
                    break;
                case "webp":
                    options = new WebPOptions();
                    break;
                default:
                    // exception
                    break;
            }

            String outputFile = outputPath + RandomUtil.randomUUID() + suffix;
            image.save(outputFile, options);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
