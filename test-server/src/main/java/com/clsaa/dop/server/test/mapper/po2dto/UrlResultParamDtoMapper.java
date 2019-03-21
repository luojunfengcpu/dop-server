package com.clsaa.dop.server.test.mapper.po2dto;

import com.clsaa.dop.server.test.mapper.AbstractCommonServiceMapper;
import com.clsaa.dop.server.test.model.dto.UrlResultParamDto;
import com.clsaa.dop.server.test.model.po.UrlResultParam;
import org.springframework.stereotype.Component;

/**
 * @author xihao
 * @version 1.0
 * @since 18/03/2019
 */
@Component
public class UrlResultParamDtoMapper extends AbstractCommonServiceMapper<UrlResultParam, UrlResultParamDto> {

    @Override
    public Class<UrlResultParam> getSourceClass() {
        return UrlResultParam.class;
    }

    @Override
    public Class<UrlResultParamDto> getTargetClass() {
        return UrlResultParamDto.class;
    }
}
