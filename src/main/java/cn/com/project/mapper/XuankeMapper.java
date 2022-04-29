package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Xuanke;

@Repository
public interface XuankeMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Xuanke record);

    void insertSelective(Xuanke record);

    Xuanke selectByPrimaryKey(Integer id);

    List<Xuanke> select(Xuanke record);

    List<Xuanke> selectTj(Xuanke record);

    void updateByPrimaryKeySelective(Xuanke record);

    void updateByPrimaryKey(Xuanke record);
}