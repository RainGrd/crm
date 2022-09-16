package com.crm.workbench.mapper;

import com.crm.workbench.entity.Clue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Aug 25 13:05:31 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Aug 25 13:05:31 CST 2022
     */
    int insert(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Aug 25 13:05:31 CST 2022
     */
    int insertSelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Aug 25 13:05:31 CST 2022
     */
    Clue selectByClueId(@Param("clueId") String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Aug 25 13:05:31 CST 2022
     */
    int updateByPrimaryKeySelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Aug 25 13:05:31 CST 2022
     */
    int updateByPrimaryKey(Clue record);

    /**
     * 插入线索
     */
    int insertCreateClue(@Param("clue") Clue clue);

    /**
     * 根据条件分页查询数据
     *
     * @param map
     * @return
     */
    List<Clue> selectClueListByConditionForPage(@Param("map") Map<String, String> map);

    /**
     * 根据条件查询条数
     */
    int selectCountOfClueByCondition(@Param("map") Map<String, String> map);

    /**
     * 根据id查询线索的明细信息
     */
    Clue selectClueForDetailById(@Param("id") String id);
    /**
     * 根据id删除该线索
     */
    int deleteClueByClueId(String clueId);
}