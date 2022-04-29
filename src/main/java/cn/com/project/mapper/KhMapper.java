package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Kh;

@Repository
public interface KhMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Kh record);

    void insertSelective(Kh record);

    Kh selectByPrimaryKey(Integer id);

    List<Kh> select(Kh record);

    void updateByPrimaryKeySelective(Kh record);

    void updateByPrimaryKey(Kh record);
}