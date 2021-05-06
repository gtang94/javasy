package io.github.gtang94.finejar.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.junit.Test;

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
}
