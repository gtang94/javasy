package io.github.gtang94.finejar.aspose;

import com.aspose.words.*;

import java.util.Objects;

public class WordUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

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
