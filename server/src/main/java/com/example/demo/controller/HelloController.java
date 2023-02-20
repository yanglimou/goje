package com.example.demo.controller;

import com.example.demo.entity.HelloParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "success";
    }

    //    @RequestMapping("/getBody")
//    public Object getBody(@RequestBody String helloParam) {
//        System.out.println("params:" + helloParam);
//        return "";
//    }
    @RequestMapping("/getBody")
    public Object getBody(@RequestBody HelloParam helloParam) {
        System.out.println("params:" + helloParam);
        System.out.println("name:" + helloParam.getName());
        System.out.println("age:" + helloParam.getAge());
        return "";
    }

//    @RequestMapping("/getParam")
//    public Object getParam(String name, Integer age) {
//        System.out.println("name:" + name);
//        System.out.println("age:" + age);
//        return "";
//    }

    @RequestMapping("/getParam")
    public Object getParam(HelloParam helloParam) {
        System.out.println("name:" + helloParam.getName());
        System.out.println("age:" + helloParam.getAge());
        return "";
    }

    @RequestMapping("/getPath/{name}")
    public Object getPath(@PathVariable String name) {
        System.out.println("name:" + name);
        return "";
    }

//    @RequestMapping("/getForm")
//    public Object getForm(String name, String age) {
//        System.out.println("name:");
//        System.out.println("age:");
//        return "";
//    }

    @RequestMapping("/getForm")
    public Object getForm(HelloParam helloParam) {
        System.out.println("name:" + helloParam.getName());
        System.out.println("age:" + helloParam.getAge());
        return "";
    }


    @RequestMapping("/getHeader")
    public Object getForm(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        return "";
    }
}
