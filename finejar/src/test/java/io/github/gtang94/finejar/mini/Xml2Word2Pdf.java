package io.github.gtang94.finejar.mini;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.google.common.base.Strings;
import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanggq
 * @class Xml2Word2Pdf
 * @description
 * @date 2021/5/24
 **/
public class Xml2Word2Pdf {
    private static Configuration configuration = null;

    /**
     * 初始化配置并设置默认编码UTF-8
     */
    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    /**
     * 通过模板文件创建word文档(模板文件的格式可到网站进行在线格式化,网址推荐:https://tool.oschina.net/codeformat/xml/)
     *
     * @param tpmplateFilePath 模板文件路径(完整路径,不包含文件如:D:/templates)
     * @param tpmplateFileName 模板文件名称
     * @param outFilePath      输出文件路径(完整路径,包含文件名称 如:D:/templates/order.doc)
     * @param dataMap          需要动态添加的数据
     */
    public static void createWord(String tpmplateFilePath, String tpmplateFileName, String outFilePath, Map<String, Object> dataMap) {
        try {
            //如果不传模板文件路径就默认取resources下的templates文件夹中的模板文件
            if (Strings.isNullOrEmpty(tpmplateFilePath)) {
                configuration.setClassForTemplateLoading(Xml2Word2Pdf.class, "/templates");
            } else {
                configuration.setDirectoryForTemplateLoading(new File(tpmplateFilePath)); // XML文件所存在的位置
            }
            //获取文档XML模板
            Template template = configuration.getTemplate(tpmplateFileName);
            //设置输出文件位置和文件名
            File outFile = new File(outFilePath);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            template.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 验证aspose.word组件是否授权：无授权的文件有水印标记
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes());
            //InputStream inputStream = Xml2Word2Pdf.class.getClassLoader().getResourceAsStream("\\license.xml");
            com.aspose.words.License license = new com.aspose.words.License();
            license.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用aspose.word把word文档转为pdf文档
     *
     * @param sourceFile word文档绝对路径(如:D:/templates/order.doc)
     * @param destFile   pdf文档绝对路径(如:D:/templates/order.pdf)
     */
    public static String word2Pdf(String sourceFile, String destFile) throws Exception {
        destFile = Strings.isNullOrEmpty(destFile) ? sourceFile.replace(".doc", ".pdf") : destFile;
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            throw new Exception("生成PDF文档,验证License失败!");
        }
        try {
            File file = new File(destFile);  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(sourceFile);//通过sourceFile创建word文档对象
            doc.save(os, SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("生成PDF文档失败!");
        }
        return destFile;
    }

    /**
     * 图片转base64编码
     *
     * @param imgFilePath 图片路径
     * @return 返回base64编码字符串
     */
    public static String ima2Base64(String imgFilePath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 简单的测试
     *
     * @remark 需要在D盘创建templates文件夹并准备相应的的xml和图片
     */
    public static void main(String[] args) throws Exception {

        word2Pdf("D:\\25f6233536f1457985f9bf59081f6832.doc", null);
        System.exit(0);

        long start = System.currentTimeMillis();
        //1.基础数据定义
        String templatePath = "D:/templates/";//模板路径/生成文档存放路径
        String sourceFile = templatePath+"order.doc";//生成的word文档路径和名称
        String templateName = "word_2003.ftl";//模板名称
        String sealImgPath = "D:/templates/liangzai.png";//盖章图片路径
        String nameImgPath = "D:/templates/zhangjie.jpg";//名字图片路径
        //2. dataMap的赋值
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("orderNo", "ZRP202001150004");
        dataMap.put("sealImgBase64", ima2Base64(sealImgPath));
        dataMap.put("nameImgBase64", ima2Base64(nameImgPath));
        //生成word文档
        createWord(templatePath, templateName, sourceFile, dataMap);
        //word文档转pdf文档
        word2Pdf(sourceFile, null);
        long end = System.currentTimeMillis();
        System.out.println("文档生成耗时：" + ((end - start) / 1000.0) + "秒");
    }
}
