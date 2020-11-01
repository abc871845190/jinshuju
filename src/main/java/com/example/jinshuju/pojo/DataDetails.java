package com.example.jinshuju.pojo;

import lombok.Data;

@Data
public class DataDetails {

    private int id;
    //和form绑定的id
    private int formTemplateId;
    //数据项列内容
    private String dataContent;
}
