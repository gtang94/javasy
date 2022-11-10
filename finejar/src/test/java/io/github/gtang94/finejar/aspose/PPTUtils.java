package io.github.gtang94.finejar.aspose;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.words.License;

public class PPTUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    public static void ppt2PDF(String pptFile, String pdfFile) {
        license();
        try {
            Presentation ppt = new Presentation(pptFile);
            ppt.save(pdfFile, SaveFormat.Pdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ppt2Images(String pptFile, String imagesFile, int saveFormat) {
        license();
        try {
            Presentation ppt = new Presentation(pptFile);
            ppt.save(imagesFile, saveFormat);
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
