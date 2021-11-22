package io.github.gtang94.finejar.hutool;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * @author tanggq
 * @class QROperation
 * @description
 * @date 2021/5/6
 **/
public class QROperation {

    /**
     * @description 简单二维码生成
     * @return: void
     * @author tanggq
     * @date 2021/5/6
     **/
    @Test
    public void createSimpleQR() {
        QrCodeUtil.generate(
                "http://www.baidu.com",
                300,
                300,
                FileUtil.file("D://test/test_qr.jpg"));
    }

    /**
     * @description: 创建包含logo的二维码
     * @return: void
     * @author: tanggq
     * @date: 7/18/21
     **/
    @Test
    public void createSimpleQRWithLogo() {
        QrCodeUtil.generate(
                "http://www.baidu.com",
                QrConfig.create()
                        .setImg("/Users/tangguoqiang/data/work/zhonghe/app/zh_logo.png")
                        .setWidth(300)
                        .setHeight(300),
                FileUtil.file("/tmp/test_qr.jpg"));
    }

    /**
     * @description 识别二维码
     * @return: void
     * @author tanggq
     * @date 2021/5/6
     **/
    @Test
    public void deCodeSimpleQR() {
        String qrString = QrCodeUtil.decode(FileUtil.file("D://test/test_qr.jpg"));
        System.err.println(qrString);
    }

    /**
     * @description 生成base64的二维码
     * @return: null
     * @author tanggq
     * @date 2021/5/11
     **/
    @Test
    public void createBase64SimpleQR() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate(
                "http://www.baidu.com",
                300,
                300,
                ImgUtil.IMAGE_TYPE_PNG,
                outputStream);
        byte[] pngData = outputStream.toByteArray();
        System.err.println(Base64.encode(pngData));
    }

}
