package com.nd.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/7/2.
 */
@Controller
@RequestMapping("/v01")
public class TestController {
    @Resource
    JdbcTemplate jdbcTemplate;
    @ResponseBody
    @RequestMapping("/test")
    public Object get(){
        return "test";
    }
}
