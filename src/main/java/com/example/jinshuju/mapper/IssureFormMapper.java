package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.IssureForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IssureFormMapper {

    /**
     * 插入模板
     *
     * @param issureForm
     * @param userId
     * @return
     */
    boolean insertFormIssure(@Param("issureForm") IssureForm issureForm, @Param("userId") int userId);

    /**
     * 插入模板组件
     *
     * @param issureForm
     * @return
     */
    boolean insertFormIssureTemplate(IssureForm issureForm);

    /**
     * 删除模板表单
     *
     * @param issureFormId
     * @return
     */
    boolean deleteIssureForm(String issureFormId);

    /**
     * 根据 tag和page和keyWord 查询分页表单
     *
     * @param keyWord
     * @param formTagArray
     * @param offset
     * @param rows
     * @return
     */
    List<IssureForm> getIssureFormListByTagAndPage(@Param("keyWord") String keyWord,
                                                   @Param("formTagArray") String[] formTagArray,
                                                   @Param("offset") int offset,
                                                   @Param("rows") int rows);

    /**
     * 根据 tag 查询分页总数目
     *
     * @param keyWord
     * @param formTagArray
     * @return
     */
    int getFormsCountByTag(@Param("keyWord") String keyWord,
                           @Param("formTagArray") String[] formTagArray);


    /**
     * 检查模板表单是否存在
     *
     * @param issureFormId
     * @return
     */
    Integer checkIssureForm(String issureFormId);

    /**
     * 获取单个模板表单信息
     *
     * @param issureFormId
     * @return
     */
    IssureForm getIssureFormById(String issureFormId);
}
