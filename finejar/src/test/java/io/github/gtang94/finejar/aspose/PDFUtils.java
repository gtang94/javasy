package io.github.gtang94.finejar.aspose;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import com.aspose.pdf.*;
import com.aspose.pdf.facades.FormattedText;
import com.aspose.pdf.facades.PdfFileEditor;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class PDFUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    public static void main(String[] args) {
        String pdffile1 = "D:\\tmp\\pdf\\aaa.pdf";
        String pdffile2 = "D:\\tmp\\pdf\\bbb.pdf";

//        pdf2Word(pdffile2, "D:\\tmp\\pdf\\", SaveFormat.DocX);

//        pdf2Excel(pdffile2, "D:\\tmp\\pdf\\", ExcelSaveOptions.ExcelFormat.XLSX);

//        pdf2PPT(pdffile1, "D:\\tmp\\pdf\\");

//        pdf2Html(pdffile1, "D:\\tmp\\pdf\\");

//        pdfSplit(pdffile2, "D:\\tmp\\pdf\\", Lists.newArrayList(1,3,6));

//        mergePdfFiles("D:\\tmp\\pdf\\", pdffile1, pdffile2);

        pdfEntrypt(pdffile2, "D:\\tmp\\pdf\\", "123456");
    }

    public static String pdf2Word(String pdfFile, String outputPath, int saveFormat) {
        license();
        try {
            Document document = new Document(pdfFile);
            DocSaveOptions saveOptions = new DocSaveOptions();
            saveOptions.setFormat(saveFormat);

            String filenameSuffix = SaveFormat.getName(SaveFormat.class, saveFormat).toLowerCase();
            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;

            document.save(outputFile, saveOptions);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void pdf2Excel(String pdfFile, String outputPath, int saveFormat) {
        license();
        try {
            Document document = new Document(pdfFile);
            ExcelSaveOptions saveOptions = new ExcelSaveOptions();
            saveOptions.setFormat(saveFormat);

            String filenameSuffix = ExcelSaveOptions.ExcelFormat.getName(ExcelSaveOptions.ExcelFormat.class, saveFormat).toLowerCase();
            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;

            document.save(outputFile, saveOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String pdf2PPT(String pdfFile, String outputPath) {
        license();
        try {
            Document document = new Document(pdfFile);
            PptxSaveOptions saveOptions = new PptxSaveOptions();
            saveOptions.setSlidesAsImages(true);

            String outputFile = outputPath + RandomUtil.randomUUID() + ".pptx";

            document.save(outputFile, saveOptions);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // library-server has finished.
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

    public static String pdf2Html(String pdfFile, String outputPath) {
        license();
        try {
            String inputFilename = FileNameUtil.getName(pdfFile);
            String filename = FileNameUtil.getPrefix(inputFilename);

            Document document = new Document(pdfFile);
            HtmlSaveOptions saveOptions = new HtmlSaveOptions();

            String randomName = RandomUtil.randomUUID();
            String outputFile = outputPath + randomName + ".html" ;

            document.save(outputFile, saveOptions);

            String relationFileDirname = outputPath + randomName + "_files";
            String outputZipFile = outputPath + filename + ".zip";
            ZipUtil.zip(FileUtil.file(outputZipFile), true, FileUtil.file(outputFile), FileUtil.file(relationFileDirname));

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

    public static String pdfSplit(String pdfFile, String outputPath, List<Integer> splitPageNoList) {
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

            String outputFile = outputPath + RandomUtil.randomUUID() + ".pdf" ;

            splitDocument.save(outputFile);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String mergePdfFiles(String outputPath, String... pdfFileList) {
        license();
        try {
            PdfFileEditor pdfFileEditor = new PdfFileEditor();

            String outputFile = outputPath + RandomUtil.randomUUID() + ".pdf";
            pdfFileEditor.concatenate(pdfFileList, outputFile);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String pdfEntrypt(String pdfFile, String outputPath, String password) {
        license();
        try {
            Document document = new Document(pdfFile);
            document.encrypt(password, password, Permissions.AssembleDocument, CryptoAlgorithm.AESx128);

            String outputFile = outputPath + RandomUtil.randomUUID() + ".pdf";

            document.save(outputFile);

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
