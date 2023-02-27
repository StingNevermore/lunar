package com.nevermore.lunar.server.controller;

import com.nevermore.lunar.component.core.dao.LunarDAO;
import com.nevermore.lunar.server.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nevermore.lunar.server.vo.ResultUtils.successResult;

/**
 * @author nevermore
 */
@Slf4j
@RestController
@RequestMapping("/api/{resourceSymbol}")
public class LunarController {

    private final LunarDAO lunarDAO;

    public LunarController(LunarDAO lunarDAO) {
        this.lunarDAO = lunarDAO;
    }

    @GetMapping("getList")
    public CommonResult getList(@PathVariable String resourceSymbol) {
        log.info("symbol: {}", resourceSymbol);
        lunarDAO.selectOne(1);
        return successResult();
    }
}
