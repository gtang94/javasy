package io.github.gtang94.finejar.aspose;


import com.aspose.imaging.*;
import com.aspose.imaging.fileformats.jpeg.JpegImage;
import com.aspose.imaging.imageoptions.EmfRasterizationOptions;
import com.aspose.imaging.imageoptions.JpegOptions;
import com.aspose.imaging.imageoptions.PngOptions;
import com.aspose.imaging.sources.StreamSource;
import com.aspose.slides.Presentation;
import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

public class ImagingUtils {

    public static void main(String[] args) {
        batchConvertImages();
    }

    public static void batchConvertImages() {
        String sourcePath = "D:\\workspace\\lib\\test\\202203\\";
        String targetPath = "D:\\workspace\\lib\\test\\202203c\\";

        List<String> sourceFileList = Lists.newArrayList();
        List<String> targetFileList = Lists.newArrayList();

        try {
//            InputStream is = new FileInputStream("D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml");
//            License license = new License();
//            license.setLicense(is);

            File file = new File(sourcePath);
            if (!file.exists()) {
                return;
            }
            File[] files = file.listFiles();
            for (int x = 0; x < files.length; x++) {
                File f = files[x];
                sourceFileList.add(sourcePath + f.getName());
                targetFileList.add(targetPath + (x+".png"));
            }

            long old = System.currentTimeMillis();
            for (int i = 0; i < sourceFileList.size(); i++) {
                System.err.println("convert: " + sourceFileList.get(i));
                FileOutputStream os = null;
                Image image = Image.load(sourceFileList.get(i));

                ImageOptionsBase imageOptionsBase = (new PngOptions()).deepClone();
                EmfRasterizationOptions rasterizationOptions = new EmfRasterizationOptions();
                rasterizationOptions.setPageHeight(image.getHeight());
                rasterizationOptions.setPageWidth(image.getWidth());
                imageOptionsBase.setVectorRasterizationOptions(rasterizationOptions);
                image.save(targetFileList.get(i), imageOptionsBase);

            }

            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void mergeImages(String[] args) {
        String sourceFile = "D:\\workspace\\lib\\test\\E3B22127-8F13-4538-990C-BD8CCE75EA27.png";
        String sourceFile2 = "D:\\workspace\\lib\\test\\Mytbatis.png";

        String targetFile = "D:\\workspace\\lib\\test\\E3B22127-8F13-4538-990C-BD8CCE75EA27-merge.jpg";

        FileOutputStream os = null;
        try {
//            InputStream is = new FileInputStream("D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml");
//            License license = new License();
//            license.setLicense(is);

//            long old = System.currentTimeMillis();
//            Image image = Image.load(sourceFile);
//
//            ImageOptionsBase imageOptionsBase = (new JpegOptions()).deepClone();
//            EmfRasterizationOptions rasterizationOptions = new EmfRasterizationOptions();
//            rasterizationOptions.setPageHeight(image.getHeight());
//            rasterizationOptions.setPageWidth(image.getWidth());
//            imageOptionsBase.setVectorRasterizationOptions(rasterizationOptions);
//            image.save(targetFile, imageOptionsBase);
//
//            long now = System.currentTimeMillis();
//            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时

            Image load1 = Image.load(sourceFile);
            Image load2 = Image.load(sourceFile2);
            int h = load1.getHeight() + load2.getHeight();
            int w = load1.getWidth() + load2.getWidth();

            JpegOptions jpegOptions = new JpegOptions();
            jpegOptions.setSource(new StreamSource());
            jpegOptions.setQuality(100);

            JpegImage newImage = (JpegImage) Image.create(jpegOptions, w, h);

            RasterImage rasterImage1 = (RasterImage) Image.load(sourceFile);
            Rectangle bounds1 = new Rectangle(0, 0, rasterImage1.getWidth(), rasterImage1.getHeight());
            newImage.saveArgb32Pixels(bounds1, rasterImage1.loadArgb32Pixels(rasterImage1.getBounds()));

            RasterImage rasterImage2 = (RasterImage) Image.load(sourceFile2);
            Rectangle bounds2 = new Rectangle(0, rasterImage2.getHeight(), rasterImage2.getWidth(), rasterImage2.getHeight());
            newImage.saveArgb32Pixels(bounds2, rasterImage2.loadArgb32Pixels(rasterImage2.getBounds()));


            newImage.save(targetFile);



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
