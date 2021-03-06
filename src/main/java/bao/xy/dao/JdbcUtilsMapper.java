package bao.xy.dao;

import com.mysql.cj.xdevapi.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description:
 * @CreateTime: 2020-09-20-21-26
 */
@Mapper
public interface JdbcUtilsMapper {

    Integer updt (@Param("table") String table, @Param("k") String k, @Param("v") String v, @Param("wherek") String wherek, @Param("wherev") String wherev);

    Integer select(@Param("table") String table, @Param("k1") String k1, @Param("v1") String v1, @Param("k2") String k2, @Param("v2") String v2);

    String selectStr(@Param("table") String table, @Param("k") String k, @Param("k1") String k1, @Param("v1") String v1, @Param("k2") String k2, @Param("v2") String v2);

    Integer delIds(@Param("table") String table, @Param("ids") List<Integer> ids);

    @Update("update ${table} set ${k} = ${v} + ${number})")
    Integer updtall(@Param("table") String table, @Param("k") String k, @Param("v") String v, @Param("number") String number);
}
