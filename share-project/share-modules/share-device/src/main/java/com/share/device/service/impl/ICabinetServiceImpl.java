package com.share.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.device.domain.Cabinet;
import com.share.device.mapper.CabinetMapper;
import com.share.device.service.ICabinetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICabinetServiceImpl extends ServiceImpl<CabinetMapper, Cabinet> implements ICabinetService {

    @Resource
    private CabinetMapper cabinetMapper;
    @Override
    public List<Cabinet> selectCabinetList(Cabinet cabinet) {

        return cabinetMapper.selectCabinetList(cabinet);
    }

    @Override
    public List<Cabinet> searchNoUseList(String keyword) {
        LambdaQueryWrapper<Cabinet> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Cabinet::getCabinetNo,keyword);
        wrapper.eq(Cabinet::getStatus,"0");
        return cabinetMapper.selectList(wrapper);
    }
}
