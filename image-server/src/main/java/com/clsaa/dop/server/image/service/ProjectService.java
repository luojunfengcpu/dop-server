package com.clsaa.dop.server.image.service;

import com.clsaa.dop.server.image.feign.UserFeign;
import com.clsaa.dop.server.image.feign.harborfeign.ProjectFeign;
import com.clsaa.dop.server.image.model.bo.ProjectBO;
import com.clsaa.dop.server.image.model.bo.ProjectMetadataBO;
import com.clsaa.dop.server.image.model.dto.ProjectDto1;
import com.clsaa.dop.server.image.model.dto.ProjectMetadataDto1;
import com.clsaa.dop.server.image.model.dto.UserCredentialDto;
import com.clsaa.dop.server.image.model.enumtype.UserCredentialType;
import com.clsaa.dop.server.image.model.po.Project;
import com.clsaa.dop.server.image.model.po.ProjectMetadata;
import com.clsaa.dop.server.image.model.po.ProjectReq;
import com.clsaa.dop.server.image.util.BasicAuthUtil;
import com.clsaa.dop.server.image.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  项目的业务实现
 * </p>
 * @author xzt
 * @since 2019-3-23
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectFeign projectFeign;
    @Autowired
    private UserFeign userFeign;

    /**
     * 返回对应条件的项目列表
     * @param name 项目名称
     * @param _public 类型
     * @param owner 创建人
     * @param page 页号
     * @param pageSize 页大小
     * @return {@link List<ProjectBO>} 项目信息列表
     */
    public List<ProjectBO> getProjects(String name,Boolean _public,String owner,Integer page,Integer pageSize,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
        List<Project> projects = projectFeign.projectsGet(name,_public,owner,page,pageSize,auth);
        if (projects.size()==0){
            return  null;
        }else {
            return BeanUtils.convertList(projects,ProjectBO.class);
        }
    }

    /**
     * 获取项目id为id的项目的信息
     * @param id 项目id
     * @return {@link ProjectBO} 项目信息
     */
    public ProjectBO getProjectById(Long id,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
        Project project = projectFeign.projectsProjectIdGet(id,auth);
        return BeanUtils.convertType(project,ProjectBO.class);
    }

    /**
     * 创建新项目
     * @param project 项目信息
     */
    public void addProject(ProjectDto1 project,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
        ProjectReq project1 = BeanUtils.convertType(project,ProjectReq.class);
        projectFeign.projectsPost(project1,auth);
    }

    /**
     * 修改项目的基本信息
     * @param projectId 项目id
     * @param projectDto1 项目内容
     */
    public void putProject(Long projectId,ProjectDto1 projectDto1,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
        ProjectReq projectReq = BeanUtils.convertType(projectDto1,ProjectReq.class);
        projectFeign.projectsProjectIdPut(projectId,projectReq,auth);
    }

    /**
     * 通过项目id删除项目
     * @param projectId
     */
    public void deleteProject(Long projectId,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
        projectFeign.projectsProjectIdDelete(projectId,auth);
    }

    /**
     * 通过项目id检索项目信息
     * @param projectId 项目id
     * @return {@link ProjectMetadataBO} 项目基本信息
     */
    public ProjectMetadataBO getProjectMetadata(Long projectId,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
       return BeanUtils.convertType(projectFeign.projectsProjectIdMetadatasGet(projectId,auth),ProjectMetadataBO.class);
    }

    /**
     * 为项目添加基本信息
     * @param projectMetadataDto1 项目的基本信息
     */
    public void addProjectMetadata(Long projectId, ProjectMetadataDto1 projectMetadataDto1,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
        projectFeign.projectsProjectIdMetadatasPost(projectId,BeanUtils.convertType(projectMetadataDto1, ProjectMetadata.class),auth);
    }
    /**
     * 通过项目id和metadata名称删除对应属性
     * @param projectId 项目id
     * @param mataName 项目名称
     */
    public void deleteProjectMetadata(Long projectId, String mataName,Long userId){
        String auth = BasicAuthUtil.createAuth(userId);
        projectFeign.projectsProjectIdMetadatasMetaNameDelete(projectId,mataName,auth);
    }
}
