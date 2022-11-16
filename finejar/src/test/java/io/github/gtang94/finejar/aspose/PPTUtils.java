package io.github.gtang94.finejar.aspose;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import com.aspose.imaging.internal.aM.P;
import com.aspose.slides.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PPTUtils {

    private static final String licenseFile = "D:\\workspace\\study\\javasy\\finejar\\src\\test\\java\\io\\github\\gtang94\\finejar\\aspose\\words-license.xml";

    public static void main(String[] args) {
        String pptfile1 = "D:\\tmp\\ppt\\aaa.pptx";

//        ppt2PDF(pptfile1, "D:\\tmp\\ppt\\");

//        ppt2Images(pptfile1, "D:\\tmp\\ppt\\", "gif");

//        pptAddWaterMark(pptfile1, "D:\\tmp\\ppt\\", "23333");
    }

    public static String ppt2PDF(String pptFile, String outputPath) {
        license();
        try {
            Presentation ppt = new Presentation(pptFile);

            String outputFile = outputPath + RandomUtil.randomUUID() + ".pdf";
            ppt.save(outputFile, SaveFormat.Pdf);

            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String ppt2Images(String pptFile, String outputPath, String format) {
        license();
        try {
            Presentation ppt = new Presentation(pptFile);

            String inputFilename = FileNameUtil.getName(pptFile);
            String filename = FileNameUtil.getPrefix(inputFilename);
            String outputTempPath = outputPath + RandomUtil.randomUUID() + File.separator;
            FileUtil.mkdir(outputTempPath);
            for (int i = 0; i < ppt.getSlides().size(); i++) {
                ISlide item = ppt.getSlides().get_Item(i);
                int height = (int) (ppt.getSlideSize().getSize().getHeight() - 150);
                int width = (int) (ppt.getSlideSize().getSize().getWidth() - 150);
                BufferedImage image = item.getThumbnail(new Dimension(width, height));

                String tmpPath = outputTempPath + filename + "-" + i + "." + format;
                ImageIO.write(image, format, new File(tmpPath));
            }

            String outputZipFile = outputPath + filename + ".zip";
            ZipUtil.zip(outputTempPath, outputZipFile);

            return outputZipFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String pptAddWaterMark(String pptFile, String outputPath, String waterMarkText) {

        int shapeType = ShapeType.Rectangle;
        float xPosition = 50;
        float yPosition = 50;
        float width = 500;
        float height = 500;
        byte fillType = FillType.Solid;
        Color solidFillColor = Color.BLACK;
        float fontHeight = 25;
        Color lineColor = Color.WHITE;
        int lineStyle = LineStyle.ThinThin;
        byte allFillType = FillType.NoFill;
        float rotation = -45;
        boolean selectedLockedFlag = true;
        boolean sizeLockedFlag = true;
        boolean textLockedFlag = true;
        boolean positionLockedFlag = true;
        boolean groupingLockedFlag = true;

        return pptAddWaterMark(pptFile, outputPath, waterMarkText, shapeType, xPosition, yPosition, width, height,
                fillType, solidFillColor, fontHeight, lineColor, lineStyle, allFillType, rotation, selectedLockedFlag,
                sizeLockedFlag, textLockedFlag, positionLockedFlag, groupingLockedFlag);
    }

    public static String pptAddWaterMark(String pptFile, String outputPath, String waterMarkText,
                                       int shapeType, float xPosition, float yPosition, float width, float height,
                                       byte fillType, Color solidFillColor, float fontHeight, Color lineColor, int lineStyle,
                                       byte allFillType, float rotation, boolean selectedLockedFlag, boolean sizeLockedFlag,
                                       boolean textLockedFlag, boolean positionLockedFlag, boolean groupingLockedFlag) {
        license();
        try {
            Presentation presentation = new Presentation(pptFile);
            ISlideCollection slides = presentation.getSlides();
            for (ISlide slide : slides) {
                IAutoShape autoShape = slide.getShapes().addAutoShape(shapeType, xPosition, yPosition, width, height);
                autoShape.addTextFrame(waterMarkText);

                autoShape.getTextFrame().getParagraphs().get_Item(0).getPortions()
                        .get_Item(0).getPortionFormat().getFillFormat().setFillType(fillType);
                autoShape.getTextFrame().getParagraphs().get_Item(0).getPortions()
                        .get_Item(0).getPortionFormat().getFillFormat().getSolidFillColor().setColor(solidFillColor);
                autoShape.getTextFrame().getParagraphs().get_Item(0).getPortions()
                        .get_Item(0).getPortionFormat().setFontHeight(fontHeight);

                autoShape.getShapeStyle().getLineColor().setColor(lineColor);
                autoShape.getShapeStyle().setLineStyleIndex(lineStyle);

                autoShape.getFillFormat().setFillType(allFillType);

                autoShape.setRotation(rotation);

                autoShape.getAutoShapeLock().setSelectLocked(selectedLockedFlag);
                autoShape.getAutoShapeLock().setSizeLocked(sizeLockedFlag);
                autoShape.getAutoShapeLock().setTextLocked(textLockedFlag);
                autoShape.getAutoShapeLock().setPositionLocked(positionLockedFlag);
                autoShape.getAutoShapeLock().setGroupingLocked(groupingLockedFlag);
            }


            String filenameSuffix = FileUtil.getSuffix(pptFile);
            String outputFile = outputPath + RandomUtil.randomUUID() + "." + filenameSuffix;

            presentation.save(outputFile, SaveFormat.Ppt);

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
