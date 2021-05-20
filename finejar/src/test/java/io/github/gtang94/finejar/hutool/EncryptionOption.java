package io.github.gtang94.finejar.hutool;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author tanggq
 * @class Encryption
 * @description
 * @date 2021/5/6
 **/
public class EncryptionOption {


    /**
     * @description AES算法加密和解密
     * @return: null
     * @author tanggq
     * @date 2021/5/11
     **/
    @Test
    public void aesEncrypt() throws UnsupportedEncodingException {

        String content = "XJZH2021TJH00002";
        String salt = HexUtil.encodeHexStr("xjzhxjzh", CharsetUtil.CHARSET_UTF_8);

        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), salt.getBytes()).getEncoded();
        // 构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.err.println(encryptHex);

        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.err.println(decryptStr);

    }
}
