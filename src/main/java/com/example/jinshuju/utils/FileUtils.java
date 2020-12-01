package com.example.jinshuju.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 上传文件工具类
 */
public class FileUtils {

    /**
     * 保存上传的文件到指定路径
     *
     * @param file
     * @param filePath
     * @return
     */
    public static String uploadMultipartFile(MultipartFile file, String filePath) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newFileName = UUIDUtils.getUUID() + "." + fileType;

            //文件夹路径
            File file1 = new File(filePath);
            if (!file1.exists()) {
                file1.mkdirs();
            }

            String newFilePath = filePath + File.separator + newFileName;
            File newFile = new File(newFilePath);

            //检查上级文件夹
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newFileName;
        }
        return null;
    }

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * InputStream 转 File
     *
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>将文件转成base64 字符串</p>
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new Base64().encodeToString(buffer);
    }

    /**
     * 将上传的文件转为base64
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String encodeBase64MutlipartFile(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        byte[] buffer = new byte[(int) file.getSize()];
        is.read(buffer);
        is.close();
        String code = new Base64().encodeToString(buffer);
        return code;
    }

    /**
     * 删除指定路径的图片
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String filePath, String fileName) {
        return FileSystemUtils.deleteRecursively(new File(filePath + File.separator, fileName));
    }
}
