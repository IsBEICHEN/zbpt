package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Leibie;

@Repository
public interface LeibieMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Leibie record);

    void insertSelective(Leibie record);

    Leibie selectByPrimaryKey(Integer id);

    List<Leibie> select(Leibie record);

    void updateByPrimaryKeySelective(Leibie record);

    void updateByPrimaryKey(Leibie record);
}