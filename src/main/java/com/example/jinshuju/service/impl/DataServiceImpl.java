package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.DataMapper;
import com.example.jinshuju.pojo.Data;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.DataService;
import com.example.jinshuju.utils.Constants;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Autowired(required = false)
    DataMapper dataMapper;

    @Override
    public Result insertData(Data data) {
        data.setDataCreateTime(new Timestamp(System.currentTimeMillis()));
        data.setDataUpdateTime(new Timestamp(System.currentTimeMillis()));
        //第一，先插入data表数据项
        if (dataMapper.insertData(data)) {
            //ture    现dataid为新插入数据项id
            if (dataMapper.insertDataDetails(data)) {
                //ture
                return ResultUtils.success("插入数据项成功");
            } else {
                //false
                return ResultUtils.fail("插入详细数据项失败");
            }
        } else {
            //false
            return ResultUtils.fail("插入数据项失败");
        }
    }

    @Override
    public Result getAllData(int formId) {
        List<Data> dataList = dataMapper.getAllDataByFormId(formId);
        if (dataList != null) {
            return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), dataList);
        }
        return ResultUtils.fail();
    }

    @Override
    public Result getDataCount(int formId) {
        int count = dataMapper.getDataCountByFormId(formId);
        return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), count);
    }

    @Override
    public Result getDataCountDaily(int formId) {
        int count = dataMapper.getDataCountDailyByFormId(formId);
        return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), count);
    }

    @Override
    public Result deleteAllData(int formId) {
        Boolean flag = dataMapper.deleteAllDataByFormId(formId);
        return flag == true ? ResultUtils.success("清除数据成功") : ResultUtils.fail("清除数据失败");
    }

    @Override
    public Result deleteData(int dataId) {
        Boolean flag = dataMapper.deleteDataByDataId(dataId);
        return flag == true ? ResultUtils.success("删除数据项成功") : ResultUtils.fail("删除数据项失败");
    }

    @Override
    public Result updateData(Data data) {
        //补充数据  数据填写时间暂定不写
        data.setDataUpdateTime(new Timestamp(System.currentTimeMillis()));
        //TODO:数据检查
        //更新数据data表的更新时间
        if (dataMapper.updateDataTime(data)) {
            //下一步删除原先绑定data的details表数据
            int dataId = data.getDataId();
            dataMapper.deleteDataDetailsByDataId(dataId);
            //插入details
            dataMapper.insertDataDetails(data);
            return ResultUtils.success("更新数据项成功");
        } else {
            return ResultUtils.fail("更新数据项时间失败");
        }
    }

    @Override
    public Result uploadExcel(MultipartFile excelFile, int formId, User user) {
        //判空
        if (!excelFile.isEmpty()) {
            //文件不为空
//            log.info(excelFile.getName());  //返回参数名字  --excelFile
//            log.info(excelFile.getOriginalFilename());  //返回文件名字  --未命名报名_20201103203506.xlsx
//            log.info(excelFile.getContentType());  //返回文件内容类型  --application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
//            log.info(String.valueOf(excelFile.getSize()));  //返回文件的大小,以字节为单位  --5788
            //判空名字
            String fileName = excelFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            //log.info(fileType);  --xlsx
            if (fileType.equals(Constants.FileType.FILE_TYPE_XLS) || fileType.equals(Constants.FileType.FILE_TYPE_XLSX)) {
                //TODO:解析文件
            } else {
                return ResultUtils.fail("上传文件类型为xls或xlsx，而不是"+fileType);
            }
        }
        return ResultUtils.fail("上传的文件为空");
    }
}
