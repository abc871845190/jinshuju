package com.example.jinshuju.pojo;

import lombok.Data;

@Data
public class DataDetails {

    private int id;
    private Template template;
    private String dataContent;
    private java.sql.Timestamp dataWriteTime;
}
