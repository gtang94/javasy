package io.github.gtang94.finejar.aspose;

import com.aspose.cells.*;

public class ExcelUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    private static final String defaultWaterMarkFontFamily = "Times New Roman";

    public static void excel2PDF(String excelFile, String pdfFile) {
        license();
        try {
            Workbook workbook = new Workbook(excelFile);
            workbook.save(pdfFile, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void excel2Images(String excelFile, String imagesFile, int saveFormat) {
        license();
        try {
            Workbook workbook = new Workbook(excelFile);
            ImageSaveOptions saveOptions = new ImageSaveOptions(saveFormat);
            workbook.save(imagesFile, saveFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
