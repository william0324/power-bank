package com.share.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.device.domain.Cabinet;

import java.util.List;

public interface ICabinetService extends IService<Cabinet> {
    List<Cabinet> selectCabinetList(Cabinet cabinet);

    /**
     * 根据关键字搜索未使用柜机
     */
    List<Cabinet> searchNoUseList(String keyword);
}
