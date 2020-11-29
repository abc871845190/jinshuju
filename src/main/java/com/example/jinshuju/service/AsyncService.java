package com.example.jinshuju.service;

import com.example.jinshuju.utils.ResultUtils.Result;

public interface AsyncService {
    void sendEmail(String subject, String content, String[] toWho);

    /**
     * 删除单张图片
     *
     * @param fileUrl
     * @return
     */
    Result deleteImg(String fileUrl);


}
