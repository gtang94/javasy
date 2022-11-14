package io.github.gtang94.finejar.aspose;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import com.aspose.cells.*;
import com.aspose.imaging.imageoptions.*;

import java.io.File;

public class ExcelUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    private static final String defaultWaterMarkFontFamily = "Times New Roman";

    public static void main(String[] args) {
        String excelfile1 = "D:\\tmp\\excel\\2222.xlsx";


//        excel2PDF(excelfile1, "D:\\tmp\\excel\\");

        excel2Images(excelfile1, "D:\\tmp\\excel\\", ImageType.JPEG);

    }

    public static String excel2PDF(String excelFile, String outputPath) {
        license();
        try {
            Workbook workbook = new Workbook(excelFile);

            String filenameSuffix = ".pdf";
            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;

            workbook.save(outputFile, SaveFormat.PDF);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ImageType.WEB_P
    // format 只支持
    // ImageFormat.getEmf();
    // ImageFormat.getWmf();
    // ImageFormat.getJpeg();
    // ImageFormat.getPng();
    // ImageFormat.getBmp();
    // ImageFormat.getGif();
    // ImageFormat.getTiff();
//     ImageFormat.getIcon();
    public static String excel2Images(String excelFile, String outputPath, int format) {
        license();
        try {
            Workbook workbook = new Workbook(excelFile);

            String inputFilename = FileNameUtil.getName(excelFile);
            String filename = FileNameUtil.getPrefix(inputFilename);
            String filenameSuffix = ".jpg";
            String outputTempPath = outputPath + RandomUtil.randomUUID() + File.separator;
            FileUtil.mkdir(outputTempPath);

            ImageOrPrintOptions options = new ImageOrPrintOptions();
            options.setImageType(format);
            if (format == ImageType.SVG) {
                options.setOnePagePerSheet(true);
            }

            WorksheetCollection worksheets = workbook.getWorksheets();
            int count = worksheets.getCount();

            for(int i = 0; i < count; i++) {
                Worksheet worksheet = worksheets.get(i);

                SheetRender render = new SheetRender(worksheet, options);
                for (int j = 0; j < render.getPageCount(); j++) {
                    String tmp = outputTempPath + filename + "-" + j + filenameSuffix;
                    render.toImage(j, tmp);
                }
            }

            String outputZipFile = outputPath + filename + ".zip";
            ZipUtil.zip(outputTempPath, outputZipFile);

//            ImageSaveOptions options = new ImageSaveOptions();


//            switch (format) {
//                case "png":
//                    options = new ImageSaveOptions(SaveFormat.PNG);
//                    break;
//                case "jpg":
//                    options = new ImageSaveOptions(SaveFormat.JPG);
//                    break;
//                case "bmp":
//                    options = new ImageSaveOptions(SaveFormat.BMP);
//                    break;
//                case "gif":
//                    options = new ImageSaveOptions(SaveFormat.GIF);
//                    break;
//                case "svg":
//                    options = new ImageSaveOptions(SaveFormat.SVG);
//                    break;
//                default:
//                    // exception
//                    break;
//            }
//
//            String filenameSuffix = "." + format;
//            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;
//
//            workbook.save(outputFile, options);

            return outputZipFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void excelAddWaterMark(String excelFile, String waterMarkText) {
        license();
        try {
            Workbook workbook = new Workbook(excelFile);
            WorksheetCollection worksheets = workbook.getWorksheets();
            int count = worksheets.getCount();

            for (int i = 0; i < count; i++) {
                Worksheet worksheet = worksheets.get(i);

                Shape shape = worksheet.getShapes().addTextEffect(MsoPresetTextEffect.TEXT_EFFECT_1, waterMarkText, defaultWaterMarkFontFamily,
                        50, false, true, 18, 8, 1, 1, 130, 800);
                FillFormat fillFormat = shape.getFill();
                fillFormat.setOneColorGradient(Color.getRed(), 0.2, GradientStyleType.HORIZONTAL, 2);
                fillFormat.setTransparency(0.9);

                shape.setHasLine(false);
                shape.setLockedProperty(ShapeLockType.SELECTION, true);
                shape.setLockedProperty(ShapeLockType.SHAPE_TYPE, true);
                shape.setLockedProperty(ShapeLockType.MOVE, true);
                shape.setLockedProperty(ShapeLockType.RESIZE, true);
                shape.setLockedProperty(ShapeLockType.TEXT, true);
            }

            workbook.save(excelFile);
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

    private String getImageSuffixByFormat(String format) {
        switch (format) {
            case "png":
                options = new ImageSaveOptions(SaveFormat.PNG);
                break;
            case "jpg":
                options = new ImageSaveOptions(SaveFormat.JPG);
                break;
            case "bmp":
                options = new ImageSaveOptions(SaveFormat.BMP);
                break;
            case "gif":
                options = new ImageSaveOptions(SaveFormat.GIF);
                break;
            case "svg":
                options = new ImageSaveOptions(SaveFormat.SVG);
                break;
            default:
                // exception
                break;
        }
    }
}
