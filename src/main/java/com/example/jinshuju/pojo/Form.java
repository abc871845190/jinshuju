package com.example.jinshuju.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Form {

    private int formId;
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
    private List<Template> templateList;
    private java.sql.Timestamp formStopTime;
    private String formQRCode;
    private int formIsFavour;
    private int formDataCount; //表单数据填写量
}
