package com.example.jinshuju.pojo;

import java.util.List;

@lombok.Data
public class Data {

  private int dataId;
  private int formId;
  private int userId;
  private java.sql.Timestamp dataCreateTime;
  private List<DataDetails> dataDetailsList;
}
