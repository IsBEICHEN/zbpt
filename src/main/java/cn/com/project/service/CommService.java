package cn.com.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.project.domain.Grade;
import cn.com.project.domain.Kh;
import cn.com.project.domain.Leibie;
import cn.com.project.domain.Ly;
import cn.com.project.domain.Message;
import cn.com.project.domain.Task;
import cn.com.project.domain.Tj;
import cn.com.project.domain.Xuanke;

public interface CommService {

    void deleteByCou(Integer id);

    void insertCou(Task record);

    void insertSelectiveCou(Task record);

    Task selectByCou(Integer id);

    List<Task> select(Task record);

    void updateByPrimaryKeySelectiveCou(Task record);

    void updateByPrimaryKeyCou(Task record);


    void deleteByKh(Integer id);

    void insertSelective(Kh record);

    Kh selectByKh(Integer id);

    List<Kh> select(Kh record);

    void updateByPrimaryKeySelective(Kh record);

    void deleteByLy(Integer id);

    void insertSelective(Ly record);

    Ly selectByLy(Integer id);

    List<Ly> select(Ly record);

    void updateByPrimaryKeySelective(Ly record);

    void deleteByLb(Integer id);

    void insertSelective(Leibie record);

    Leibie selectByLb(Integer id);

    List<Leibie> select(Leibie record);

    void updateByPrimaryKeySelective(Leibie record);

    void deleteByXk(Integer id);

    void insertSelective(Xuanke record, HttpServletRequest request);

    Xuanke selectByXk(Integer id);

    List<Xuanke> select(Xuanke record);

    void updateByPrimaryKeySelective(Xuanke record);

    void deleteGra(Integer id);

    void insertGra(Grade record);

    Grade selectGra(Integer id);

    List<Grade> selectGraList(Grade record);

    void updateGra(Grade record);

    List<Tj> selectZj(Grade record);

    List<Tj> selectTj(Grade record);

    void deleteByXx(Integer id);

    void insertSelective(Message record);

    Message selectByXx(Integer id);

    List<Message> select(Message record);

    void updateByPrimaryKeySelective(Message record);
}
