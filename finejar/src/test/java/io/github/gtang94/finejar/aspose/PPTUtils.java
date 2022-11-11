package io.github.gtang94.finejar.aspose;

import com.aspose.imaging.internal.aM.P;
import com.aspose.slides.*;
import com.aspose.words.License;

import java.awt.*;

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

    public static void pptAddWaterMark(String pptFile, String newPPTFile, String waterMarkText) {
        license();
        try {
            Presentation presentation = new Presentation(pptFile);
            ISlideCollection slides = presentation.getSlides();
            for (ISlide slide : slides) {
                IAutoShape autoShape = slide.getShapes().addAutoShape(ShapeType.Rectangle, 50, 50, 500, 500);
                autoShape.addTextFrame(waterMarkText);

                autoShape.getTextFrame().getParagraphs().get_Item(0).getPortions()
                        .get_Item(0).getPortionFormat().getFillFormat().setFillType(FillType.Solid);
                autoShape.getTextFrame().getParagraphs().get_Item(0).getPortions()
                        .get_Item(0).getPortionFormat().getFillFormat().getSolidFillColor().setColor(Color.BLACK);
                autoShape.getTextFrame().getParagraphs().get_Item(0).getPortions()
                        .get_Item(0).getPortionFormat().setFontHeight(25);

                autoShape.getShapeStyle().getLineColor().setColor(Color.WHITE);
                autoShape.getShapeStyle().setLineStyleIndex(LineStyle.ThinThin);

                autoShape.getFillFormat().setFillType(FillType.NoFill);

                autoShape.setRotation(-45);

                autoShape.getAutoShapeLock().setSelectLocked(true);
                autoShape.getAutoShapeLock().setSizeLocked(true);
                autoShape.getAutoShapeLock().setTextLocked(true);
                autoShape.getAutoShapeLock().setPositionLocked(true);
                autoShape.getAutoShapeLock().setGroupingLocked(true);
            }

            presentation.save(newPPTFile, SaveFormat.Ppt);
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
