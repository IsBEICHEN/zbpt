package cn.com.project.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.project.domain.Grade;
import cn.com.project.domain.Kh;
import cn.com.project.domain.Leibie;
import cn.com.project.domain.Ly;
import cn.com.project.domain.Message;
import cn.com.project.domain.Task;
import cn.com.project.domain.Tj;
import cn.com.project.domain.Xuanke;
import cn.com.project.mapper.GradeMapper;
import cn.com.project.mapper.KhMapper;
import cn.com.project.mapper.LeibieMapper;
import cn.com.project.mapper.LyMapper;
import cn.com.project.mapper.MessageMapper;
import cn.com.project.mapper.TaskMapper;
import cn.com.project.mapper.XuankeMapper;
import cn.com.project.service.CommService;
import cn.com.project.utils.Comm;
import cn.com.project.utils.DateUtils;

@Service
public class CommServiceImpl implements CommService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private KhMapper khMapper;

    @Autowired
    private LyMapper lyMapper;

    @Autowired
    private LeibieMapper leibieMapper;

    @Autowired
    private XuankeMapper xuankeMapper;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void deleteByCou(Integer id) {
        taskMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertCou(Task record) {
        taskMapper.insert(record);
    }

    @Override
    public void insertSelectiveCou(Task record) {
        taskMapper.insertSelective(record);
    }

    @Override
    public Task selectByCou(Integer id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Task> select(Task record) {
        return taskMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelectiveCou(Task record) {
        taskMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKeyCou(Task record) {
        taskMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByKh(Integer id) {
        khMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Kh record) {
        khMapper.insertSelective(record);
    }

    @Override
    public Kh selectByKh(Integer id) {
        return khMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Kh> select(Kh record) {
        return khMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Kh record) {
        khMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByLy(Integer id) {
        lyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Ly record) {
        lyMapper.insertSelective(record);
    }

    @Override
    public Ly selectByLy(Integer id) {
        return lyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Ly> select(Ly record) {
        return lyMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Ly record) {
        lyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByLb(Integer id) {
        leibieMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Leibie record) {
        leibieMapper.insertSelective(record);
    }

    @Override
    public Leibie selectByLb(Integer id) {
        return leibieMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Leibie> select(Leibie record) {
        return leibieMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Leibie record) {
        leibieMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void insertSelective(Xuanke record, HttpServletRequest request) {
        record.setBdate(DateUtils.GetNowDate());
        record.setaId(Comm.getUserInfoId(request));
        record.setName(Comm.getUserInfoName(request));
        xuankeMapper.insertSelective(record);
    }

    @Override
    public Xuanke selectByXk(Integer id) {
        return xuankeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Xuanke> select(Xuanke record) {
        return xuankeMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Xuanke record) {
        xuankeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByXk(Integer id) {
        xuankeMapper.deleteByPrimaryKey(id);
    }

    public void deleteGra(Integer id) {
        gradeMapper.deleteByPrimaryKey(id);
    }

    public void insertGra(Grade record) {
        gradeMapper.insert(record);

    }

    public Grade selectGra(Integer id) {
        Grade g = gradeMapper.selectByPrimaryKey(id);
        return g;
    }

    public List<Grade> selectGraList(Grade record) {
        List<Grade> selectGraList = gradeMapper.selectGraList(record);
        return selectGraList;
    }

    public void updateGra(Grade record) {
        gradeMapper.updateByPrimaryKey(record);
    }

    public List<Tj> selectZj(Grade record) {
        return gradeMapper.selectZj(record);
    }

    public List<Tj> selectTj(Grade record) {
        return gradeMapper.selectTj(record);
    }

    @Override
    public void deleteByXx(Integer id) {
        messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Message record) {
        messageMapper.insertSelective(record);
    }

    @Override
    public Message selectByXx(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Message> select(Message record) {
        return messageMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Message record) {
        messageMapper.updateByPrimaryKeySelective(record);
    }
}
