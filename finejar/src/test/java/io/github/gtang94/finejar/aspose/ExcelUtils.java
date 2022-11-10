package io.github.gtang94.finejar.aspose;

import com.aspose.cells.ImageSaveOptions;
import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import java.util.Objects;

public class ExcelUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

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
