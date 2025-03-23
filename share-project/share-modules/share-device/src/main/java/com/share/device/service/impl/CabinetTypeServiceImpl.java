package com.share.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.device.domain.CabinetType;
import com.share.device.mapper.CabinetTypeMapper;
import com.share.device.service.ICabinetTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CabinetTypeServiceImpl extends ServiceImpl<CabinetTypeMapper, CabinetType> implements ICabinetTypeService {

    @Resource
    private CabinetTypeMapper cabinetTypeMapper;
    @Override
    public List<CabinetType> selectCabinetTypeList(CabinetType cabinetType) {
        return cabinetTypeMapper.selectCabinetTypeList(cabinetType);
    }
}
