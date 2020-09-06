package com.stcode.bootstrap.controller;

import com.stcode.bootstrap.domain.User;
import com.stcode.bootstrap.dto.TestDto;
import com.stcode.bootstrap.service.TestService;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class BootstrapTestController {

    @ModelAttribute("PUB_GOODS_TYPE_options")
    public java.util.Map<String, String> PUB_GOODS_TYPE_options() {
        Map<String,String > map = new HashMap<>();
        map.put("1","全市");
        map.put("2","各经（代）办机构");
        map.put("3","北京");
        map.put("4","上海");
//        return getOptionService().load("PUB_GOODS_TYPE");
        return map;
    }

    @RequestMapping("index" )
    public String index() {
        return "index";
    }


    @Resource
    private TestService testService;

    @RequestMapping("/test")
    @ResponseBody
    public List<User> test(){
        return testService.list();
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public R search(TestDto testDto){
        List<TestDto> listT = new ArrayList<>();
        TestDto dto1 = new TestDto();
        dto1.setCompanyName("上海");
        dto1.setJbr("test");
        dto1.setJdbjg("test111");
        dto1.setYl(testDto.getDaiban());

        TestDto dto2 = new TestDto();

        dto2.setCompanyName("北京");
        dto2.setJbr("test2222");
        dto2.setJdbjg("test222");
        dto2.setYl("test222");

        TestDto dto3 = new TestDto();
        dto3.setCompanyName("云南");
        dto3.setJbr("test3333");
        dto3.setJdbjg("test333");
        dto3.setYl("test333");

        TestDto dto4 = new TestDto();
        dto4.setCompanyName("大理");
        dto4.setJbr("test444");
        dto4.setJdbjg("test444");
        dto4.setYl("test1444");
        listT.add(dto1);
        listT.add(dto2);
        listT.add(dto3);
        listT.add(dto4);

        System.out.println(testDto.getDaiban());
        return R.ok().put("data",listT);
    }
}
