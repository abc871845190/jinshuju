package com.example.jinshuju.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * ZXing 二维码工具类
 */
public class QRCodeUtils {
    /**
     * 默认存储二维码路径
     */
    private static final String FILE_PATH = "D:\\eclipse for ee\\jinshuju\\src\\main\\resources\\static\\img\\QRCode";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;

    /**
     * 生成二维码图片流
     *
     * @param content 二维码内容
     * @return 返回文件存储路径
     */
    public static String createImage(String content) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        //设置二维码字符编码
        hints.put(EncodeHintType.CHARACTER_SET, Constants.Encode.UTF8);
        //设置二维码纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码边距
        hints.put(EncodeHintType.MARGIN, 1);

        //开始生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);

        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
        //生成图片文件
        //判断文件夹是否存在，不存在就创建文件夹
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        //生成随机图片名字
        String fileName = UUIDUtils.getUUID() + "." + Constants.FileType.FILE_TYPE_JPG;
        //存到服务器本地指定文件夹内
        ImageIO.write(image, Constants.FileType.FILE_TYPE_JPG, new File(FILE_PATH + File.separator + fileName));
        return fileName;
    }
}
