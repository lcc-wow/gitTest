package com.lcc.demo.service.impl;

import com.lcc.demo.mapper.DeptMapper;
import com.lcc.demo.pojo.Dept;
import com.lcc.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper; // 引入mapper

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDate.now());
        dept.setUpdateTime(LocalDate.now());
        deptMapper.add(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDate.now());
        // 先查询数据库中的原始记录
        Dept existingDept = deptMapper.getById(dept.getId());
        if (existingDept != null) {
            // 保留原有的 create_time
            dept.setCreateTime(existingDept.getCreateTime());
        }
        deptMapper.update(dept);
    }
}
