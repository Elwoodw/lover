package org.lover.controller;

import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FallbackController {


    @RequestMapping("/fallback")
    public ResponseEntity fallback()
    {
        log.warn("服务降级");
        return ResponseEntity.status(HttpStatus.HTTP_INTERNAL_ERROR).build();

    }
}
