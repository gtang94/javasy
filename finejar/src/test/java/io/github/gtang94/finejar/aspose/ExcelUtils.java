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

//        excel2Images(excelfile1, "D:\\tmp\\excel\\", ImageType.WEB_P);

//        excelAddWaterMark(excelfile1,"D:\\tmp\\excel\\", "233333333333");

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

    public static String excel2Images(String excelFile, String outputPath, int format) {
        license();
        try {
            Workbook workbook = new Workbook(excelFile);

            String inputFilename = FileNameUtil.getName(excelFile);
            String filename = FileNameUtil.getPrefix(inputFilename);
            String filenameSuffix = getImageSuffixByFormat(format);
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

            return outputZipFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String excelAddWaterMark(String excelFile, String outputPath, String waterMarkText) {

        int fontSize = 18;
        boolean fontBlod = false;
        boolean fontltalic = true;
        int upperLeftRow = 18;
        int top = 8;
        int upperLeftColumn = 1;
        int left = 1;
        int height = 130;
        int width = 800;
        Color color = Color.getRed();
        double degree = 0.2;
        int gradientStyleType = GradientStyleType.HORIZONTAL;
        int variant = 2;
        double transparency = 0.9;
        boolean shapeLineFlag = true;
        boolean selectionFlag = true;
        boolean shapeTypeFlag = true;
        boolean moveFlag = true;
        boolean resizeFlag = true;
        boolean textFlag = true;
        return excelAddWaterMark(excelFile, outputPath, waterMarkText, defaultWaterMarkFontFamily, fontSize, fontBlod,
                fontltalic, upperLeftRow, top, upperLeftColumn, left, height, width, color, degree, gradientStyleType, variant,
                transparency, shapeLineFlag, selectionFlag, shapeTypeFlag, moveFlag, resizeFlag, textFlag);
    }

    public static String excelAddWaterMark(String excelFile, String outputPath, String waterMarkText,
                                         String fontFamily, int fontSize, boolean fontBlod, boolean fontltalic,
                                         int upperLeftRow, int top, int upperLeftColumn, int left, int height, int width,
                                         Color color, double degree, int gradientStyleType, int variant, double transparency,
                                         boolean shapeLineFlag, boolean selectionFlag, boolean shapeTypeFlag, boolean moveFlag,
                                         boolean resizeFlag, boolean textFlag) {
        license();
        try {
            Workbook workbook = new Workbook(excelFile);
            WorksheetCollection worksheets = workbook.getWorksheets();
            int count = worksheets.getCount();

            for (int i = 0; i < count; i++) {
                Worksheet worksheet = worksheets.get(i);

                Shape shape = worksheet.getShapes().addTextEffect(MsoPresetTextEffect.TEXT_EFFECT_1, waterMarkText, fontFamily,
                        fontSize, fontBlod, fontltalic, upperLeftRow, top, upperLeftColumn, left, height, width);
                FillFormat fillFormat = shape.getFill();
                fillFormat.setOneColorGradient(color, degree, gradientStyleType, variant);
                fillFormat.setTransparency(transparency);

                shape.setHasLine(shapeLineFlag);
                shape.setLockedProperty(ShapeLockType.SELECTION, selectionFlag);
                shape.setLockedProperty(ShapeLockType.SHAPE_TYPE, shapeTypeFlag);
                shape.setLockedProperty(ShapeLockType.MOVE, moveFlag);
                shape.setLockedProperty(ShapeLockType.RESIZE, resizeFlag);
                shape.setLockedProperty(ShapeLockType.TEXT, textFlag);
            }

            String filenameSuffix = FileUtil.getSuffix(excelFile);
            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;

            workbook.save(outputFile);

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

    private static String getImageSuffixByFormat(int imageType) {
        String suffix = ".png";
        switch (imageType) {
            case ImageType.EMF:
                suffix = ".emf";
                break;
            case ImageType.WMF:
                suffix = ".wmf";
                break;
            case ImageType.JPEG:
                suffix = ".jpg";
                break;
            case ImageType.PNG:
                suffix = ".png";
                break;
            case ImageType.BMP:
                suffix = ".bmp";
                break;
            case ImageType.GIF:
                suffix = ".gif";
                break;
            case ImageType.TIFF:
                suffix = ".tiff";
                break;
            case ImageType.SVG:
                suffix = ".svg";
                break;
            default:
                // exception
                break;
        }
        return suffix;
    }
}
