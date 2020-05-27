package com.gsir.monitor.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@TableName("devops_projects")
public class Project implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8669940063643075268L;

    @TableId(type=IdType.AUTO)
    private Integer id;

    String name;

    @TableField(value="gitlab_project_id")
    Integer gitlabProjectId;

    @TableField(value="gitlab_project_name")
    String gitlabProjectName;

    @TableField(value="gitlab_ssh_repo_url")
    String gitlabSSHRepoURL;

    @TableField(value="gitlab_default_branch")
    String gitlabDefaultBranch;

    boolean enabled;
    @TableField(value="created_time")
    Long createdTime;
}
