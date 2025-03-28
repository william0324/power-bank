package com.share.device.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.device.domain.Region;
import com.share.device.mapper.RegionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class IRegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {
    @Resource
    private RegionMapper regionMapper;
    @Override
    public List<Region> treeSelect(String code) {
        LambdaQueryWrapper<Region> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Region::getParentCode,code);
        List<Region> regionList = regionMapper.selectList(queryWrapper);

        if (!CollectionUtils.isEmpty(regionList)) {
            regionList.forEach(region -> {
                LambdaQueryWrapper<Region> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Region::getParentCode,region.getCode());
                Long count = regionMapper.selectCount(queryWrapper1);
                if (count > 0) {
                    region.setHasChildren(true);
                } else {
                    region.setHasChildren(false);
                }
            });
        }

        return regionList;

    }
}
