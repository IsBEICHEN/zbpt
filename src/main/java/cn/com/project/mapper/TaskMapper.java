package cn.com.project.mapper;

import java.util.List;

import cn.com.project.domain.Task;

public interface TaskMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Task record);

    void insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    List<Task> select(Task record);

    void updateByPrimaryKeySelective(Task record);

    void updateByPrimaryKey(Task record);
}