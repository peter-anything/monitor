package com.gsir.monitor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gsir.monitor.pojo.Project;
import com.gsir.monitor.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {

    @Resource
    private com.gsir.monitor.mapper.ProjectMapper projectMapper;

    @Override
    public List<Project> getAllProject() {
        return projectMapper.selectList(new EntityWrapper<Project>().eq("enabled", 1));
    }

}
