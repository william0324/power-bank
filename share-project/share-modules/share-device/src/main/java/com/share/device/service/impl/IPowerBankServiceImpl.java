package com.share.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.common.core.exception.ServiceException;
import com.share.device.domain.PowerBank;
import com.share.device.mapper.PowerBankMapper;
import com.share.device.service.IPowerBankService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPowerBankServiceImpl extends ServiceImpl<PowerBankMapper, PowerBank> implements IPowerBankService {
    @Resource
    private PowerBankMapper powerBankMapper;
    @Override
    public List<PowerBank> selectPowerBankList(PowerBank powerBank) {
        return powerBankMapper.selectPowerBankList(powerBank);
    }

    @Override
    public int savePowerBank(PowerBank powerBank) {
        // 1.判断当前充电宝的编号是否已经存在
        String powerBankNo = powerBank.getPowerBankNo();
        LambdaQueryWrapper<PowerBank> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PowerBank::getPowerBankNo, powerBankNo);
        Long count = powerBankMapper.selectCount(queryWrapper);
        // 如果存在，不添加
        if (count > 0) {
            throw new ServiceException("该充电宝编号已存在");
        }
        return powerBankMapper.insert(powerBank);
    }

    @Override
    public int updatePowerBank(PowerBank powerBank) {
        // 1. 判断状态值是0的时候才能修改
        Long id = powerBank.getId();
        PowerBank oldPowerBank = powerBankMapper.selectById(id);
        if (oldPowerBank != null && "0".equals(oldPowerBank.getStatus())) {
            int rows = powerBankMapper.updateById(powerBank);
            return rows;
        }
        return 0;
    }

}
