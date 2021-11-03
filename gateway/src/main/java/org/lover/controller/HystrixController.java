package org.lover.controller;

import org.lover.exception.CommonEnum;
import org.lover.exception.ResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {

    @GetMapping("/fallback")
    public ResultBody fallBack()
    {
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/test")
    public  ResultBody test() throws  InterruptedException
    {
        Thread.sleep(8000);
        return ResultBody.success();
    }
}
