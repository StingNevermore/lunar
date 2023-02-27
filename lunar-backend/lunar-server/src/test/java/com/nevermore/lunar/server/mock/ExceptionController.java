package com.nevermore.lunar.server.mock;

import com.nevermore.lunar.server.vo.CommonResult;
import com.nevermore.lunar.server.vo.CreateEntityVo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nevermore
 */
@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @GetMapping("/internalServerError")
    public ResponseEntity<CommonResult> internalServerError() {
        throw new RuntimeException("for test!");
    }

    @PostMapping("/badRequest")
    public ResponseEntity<CommonResult> badRequest(@SuppressWarnings("unused") @RequestBody @Validated CreateEntityVo vo) {
        throw new RuntimeException("impossible");
    }
}
