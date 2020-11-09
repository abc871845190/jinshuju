package com.example.jinshuju.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

/**
 * ZXing 二维码工具类
 */
public class QRCodeUtils {
    // 二维码颜色==黑色
    private static final int BLACK = 0xFF000000;
    // 二维码颜色==白色
    private static final int WHITE = 0xFFFFFFFF;
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;

    /**
     * 生成二维码图片流
     *
     * @param content
     * @param needCompress
     * @return
     */
    public static BufferedImage createImage(String content, boolean needCompress) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        //设置二维码字符编码
        hints.put(EncodeHintType.CHARACTER_SET, Constants.Encode.UTF8);
        //设置二维码纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码边距
        hints.put(EncodeHintType.MARGIN, 1);

        //开始生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        //获取二维码宽高
        int codeWidth = bitMatrix.getWidth();
        int codeHeight = bitMatrix.getHeight();
        //将二维码放入缓存流
        BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < codeWidth; x++) {
            for (int y = 0; y < codeHeight; y++) {
                //循环将二维码导入图片
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }


}
