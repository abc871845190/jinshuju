package com.example.jinshuju.utils.EasyExcelUtils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DefaultExcelListener extends AnalysisEventListener<Map<Integer, String>> {

    List<Map<Integer, String>> list = new ArrayList<>();

    /**
     * 每条数据解析都回来调用此方法
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {

    }

    /**
     * 所有解析都完成了回来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
