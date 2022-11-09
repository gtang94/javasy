package io.github.gtang94.finejar.aspose;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.*;

public class WordsUtils {

    public static void main(String[] args) {
        String sourceFile = "D:\\workspace\\lib\\test\\ISMS202202.docx";
        String targerFile = "D:\\workspace\\lib\\test\\ISMS202202.pdf";

        FileOutputStream os = null;
        try {
            InputStream is = new FileInputStream("D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml");
            License license = new License();
            license.setLicense(is);
            
            os = new FileOutputStream(targerFile);
            Document document = new Document(sourceFile);
            document.save(os, SaveFormat.PDF);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
