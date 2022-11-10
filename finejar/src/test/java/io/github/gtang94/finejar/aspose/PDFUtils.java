package io.github.gtang94.finejar.aspose;

import com.aspose.pdf.*;
import com.aspose.pdf.facades.FormattedText;
import com.aspose.pdf.facades.PdfFileEditor;
import com.aspose.words.License;

import java.util.List;

public class PDFUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    public static void pdf2Word(String pdfFile, String wordFile, int saveFormat) {
        license();
        try {
            Document document = new Document(pdfFile);
            DocSaveOptions saveOptions = new DocSaveOptions();
            saveOptions.setFormat(saveFormat);
            document.save(wordFile, saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdf2Excel(String pdfFile, String excelFile, int saveFormat) {
        license();
        try {
            Document document = new Document(pdfFile);
            DocSaveOptions saveOptions = new DocSaveOptions();
            saveOptions.setFormat(saveFormat);
            document.save(excelFile, saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdf2PPT(String pdfFile, String pptFile) {
        license();
        try {
            Document document = new Document(pdfFile);
            DocSaveOptions saveOptions = new DocSaveOptions();
            saveOptions.setFormat(SaveFormat.Pptx);
            document.save(pptFile, saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdf2Images(String pdfFile, String imagesFile, int saveFormat) {
        license();
        try {
            Document document = new Document(pdfFile);
            DocSaveOptions saveOptions = new DocSaveOptions();
            saveOptions.setFormat(saveFormat);
            document.save(imagesFile, saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdf2Html(String pdfFile, String imagesFile) {
        license();
        try {
            Document document = new Document(pdfFile);
            DocSaveOptions saveOptions = new DocSaveOptions();
            saveOptions.setFormat(SaveFormat.Html);
            document.save(imagesFile, saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdfAddWaterMark(String pdfFile, String newPDFFile, String waterMarkText) {
        license();
        try {
            Document document = new Document(pdfFile);

            FormattedText formattedText = new FormattedText(waterMarkText);

            WatermarkArtifact artifact = new WatermarkArtifact();
            artifact.setText(formattedText);
            artifact.setArtifactHorizontalAlignment(HorizontalAlignment.Center);
            artifact.setArtifactVerticalAlignment(VerticalAlignment.Center);
            artifact.setRotation(25);
            artifact.setOpacity(0.5);
            artifact.setBackground(false);

            document.getPages().get_Item(1).getArtifacts().add(artifact);

            document.save(newPDFFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdfSplit(String pdfFile, String newPDFFile, List<Integer> splitPageNoList) {
        license();
        try {
            Document splitDocument = new Document();

            Document document = new Document(pdfFile);
            PageCollection pages = document.getPages();
            for (Page page : pages) {
                int number = page.getNumber();
                if (splitPageNoList.contains(number)) {
                    splitDocument.getPages().add(page);
                }
            }

            splitDocument.save(newPDFFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mergePdfFiles(List<String> pdfFileList, String newPdfFile) {
        license();
        try {
            PdfFileEditor pdfFileEditor = new PdfFileEditor();

            String[] pdfFileArray = (String[]) pdfFileList.toArray();
            pdfFileEditor.concatenate(pdfFileArray, newPdfFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdfEntrypt(String pdfFile, String newPdfFile, String password) {
        license();
        try {
            Document document = new Document(pdfFile);
            document.encrypt(password, password, Permissions.AssembleDocument, CryptoAlgorithm.AESx128);
            document.save(newPdfFile);
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
