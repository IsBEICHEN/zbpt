package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Grade;
import cn.com.project.domain.Tj;

@Repository
public interface GradeMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Grade record);

    Grade selectByPrimaryKey(Integer id);

    List<Grade> selectGraList(Grade record);

    void updateByPrimaryKey(Grade record);

    List<Tj> selectZj(Grade record);

    List<Tj> selectTj(Grade record);

}