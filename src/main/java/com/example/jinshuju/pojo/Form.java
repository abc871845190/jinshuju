package com.example.jinshuju.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Form {

    private String formId;
    private String formName;
    private String formTitle;
    private String formDesc;
    private java.sql.Timestamp formCreateTime;
    private java.sql.Timestamp formUpdateTime;
    private String formTag;
    private int formOpen;
    private String formUrl;
    private int formViewCount;
    private String formImg;
    private String formType;
    private List<Template> templateList; //组件集合
    private java.sql.Timestamp formStopTime;
    private String formQRCode;
    private int formIsFavour;
    private int formDataCount; //表单数据填写量
    private java.sql.Timestamp formDataUpdateTime; //最新一次填写数据的时间
    private int formDataCountByUser; //单用户数据填写量
    private int formIsIssure; //表单是否发布为模版
}
