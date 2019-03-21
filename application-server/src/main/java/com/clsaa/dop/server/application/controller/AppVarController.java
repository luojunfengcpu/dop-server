package com.clsaa.dop.server.application.controller;


import com.clsaa.dop.server.application.config.HttpHeadersConfig;
import com.clsaa.dop.server.application.model.bo.AppVarBoV1;
import com.clsaa.dop.server.application.model.vo.AppVarCreateV1;
import com.clsaa.dop.server.application.model.vo.AppVarV1;
import com.clsaa.dop.server.application.service.AppVarService;
import com.clsaa.dop.server.application.util.BeanUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 应用变量API接口实现类
 * </p>
 *
 * @author Bowen
 * @since 2019-3-12
 */
@RestController
@CrossOrigin
@EnableSwagger2WebFlux
public class AppVarController {
    @Autowired
    private AppVarService appVarService;

    @ApiOperation(value = "查询变量", notes = "查询变量")
    @GetMapping("/application/{appId}/variable")
    public List<AppVarV1> findAppVarByAppId(
            @ApiParam(name = "appId", value = "应用Id", required = true) @PathVariable(value = "appId") Long appId) {
        return this.appVarService.findAppVarByAppIdOrderByKey(appId).stream().map(l -> BeanUtils.convertType(l, AppVarV1.class)).collect(Collectors.toList());

    }

    @ApiOperation(value = "查询变量", notes = "查询变量")
    @GetMapping("/application/{appId}/values/{key}")
    public String findValueByAppIdAndKey(
            @ApiParam(name = "appId", value = "应用Id", required = true) @PathVariable(value = "appId") Long appId,
            @ApiParam(name = "key", value = "键", required = true) @PathVariable(value = "key") String key
    ) {
        return this.appVarService.findValueByAppIdAndKey(appId, key);
    }

    @ApiOperation(value = "创建变量", notes = "创建变量")
    @PostMapping("/application/{appId}/variable")
    public void createAppVarByAppId(
            @RequestHeader(HttpHeadersConfig.HttpHeaders.X_LOGIN_USER) Long cuser,
            @ApiParam(name = "appId", value = "应用Id", required = true) @PathVariable(value = "appId") Long appId,
            @ApiParam(name = "var", value = "变量", required = true) @RequestBody AppVarCreateV1 appVarCreateV1) {
        this.appVarService.createAppVarByAppId(cuser, appId, appVarCreateV1.getVarKey(), appVarCreateV1.getVarValue());

    }

    @ApiOperation(value = "删除变量", notes = "删除变量")
    @DeleteMapping("/application/variable/{appVarId}")
    public void deleteAppVarById(
            @ApiParam(name = "appVarId", value = "应用变量Id", required = true) @PathVariable(value = "appVarId") Long appVarId) {
        this.appVarService.delteAppVarById(appVarId);

    }

    @ApiOperation(value = "更新变量", notes = "更新变量")
    @PutMapping("/application/variable/{appVarId}")
    public void updateAppVarById(
            @RequestHeader(HttpHeadersConfig.HttpHeaders.X_LOGIN_USER) Long muser,
            @ApiParam(name = "appVarId", value = "应用变量Id", required = true) @PathVariable(value = "appVarId") Long appVarId,
            @ApiParam(name = "var", value = "变量", required = true) @RequestBody AppVarCreateV1 appVarCreateV1) {
        this.appVarService.updateAppVarById(appVarId, muser, appVarCreateV1.getVarValue());
    }


}
