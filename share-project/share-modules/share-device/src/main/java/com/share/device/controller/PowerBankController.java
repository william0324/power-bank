package com.share.device.controller;

import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.security.utils.SecurityUtils;
import com.share.device.domain.PowerBank;
import com.share.device.service.IPowerBankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Tag(name = "充电宝接口管理")
@RestController
@RequestMapping("/powerBank")
public class PowerBankController extends BaseController {
    @Resource
    private IPowerBankService powerBankService;

    /**
     * 分页查询充电宝
     */
    @Operation(summary = "充电宝分页查询")
    @GetMapping("/list")
    public TableDataInfo list(PowerBank powerBank) {
        startPage();
        return getDataTable(powerBankService.selectPowerBankList(powerBank));
    }

    /**
     * 根据id获取充电宝的详细信息
     */
    @Operation(summary = "根据id获取充电宝的详细信息")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(powerBankService.getById(id));
    }

    /**
     * 添加充电宝
     */
    @Operation(summary = "添加充电宝")
    @PostMapping
    public AjaxResult add(@RequestBody PowerBank powerBank) {
        // 设置相关数据
        powerBank.setCreateBy(SecurityUtils.getUsername());
        powerBank.setCreateTime(new Date());
        return toAjax(powerBankService.savePowerBank(powerBank));
    }

    /**
     * 修改充电宝
     */
    @Operation(summary = "修改充电宝")
    @PutMapping
    public AjaxResult edit(@RequestBody PowerBank powerBank) {
        // 设置相关数据
        powerBank.setUpdateBy(SecurityUtils.getUsername());
        powerBank.setUpdateTime(new Date());
        return toAjax(powerBankService.updatePowerBank(powerBank));
    }

    /**
     * 删除充电宝
     */
    @Operation(summary = "删除充电宝")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(powerBankService.removeByIds(List.of(ids)));
    }
}
