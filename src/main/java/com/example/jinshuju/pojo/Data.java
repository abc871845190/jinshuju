package com.example.jinshuju.pojo;

import java.util.List;

@lombok.Data
public class Data {

    private int dataId;
    private Form form;
    private User user;
    private java.sql.Timestamp dataCreateTime;
    private java.sql.Timestamp dataUpdateTime;
    private int dataWriteTime;
    private List<DataDetails> dataDetailsList;
}
