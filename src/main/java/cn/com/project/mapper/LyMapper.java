package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Ly;

@Repository
public interface LyMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Ly record);

    void insertSelective(Ly record);

    Ly selectByPrimaryKey(Integer id);

    List<Ly> select(Ly record);

    void updateByPrimaryKeySelective(Ly record);

    void updateByPrimaryKey(Ly record);
}