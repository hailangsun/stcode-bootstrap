package com.stcode.bootstrap.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.stcode.bootstrap.domain.User;
import com.stcode.bootstrap.dto.TestDto;
import com.stcode.bootstrap.export.ComplexHeadData;
import com.stcode.bootstrap.service.TestService;
import com.stcode.bootstrap.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.*;

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
        List<TestDto> listT = getTestLists(testDto);

        System.out.println(testDto.getDaiban());
        return R.ok().put("data",listT);
    }

    private List<TestDto> getTestLists(TestDto testDto) {
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
        return listT;
    }

    //查询检查结果
    @RequestMapping(value = "/checkResult")
    @ResponseBody
    public R checkResult(TestDto testDto){
        List<TestDto> lists = new ArrayList<>();
        TestDto test1 = new TestDto();
        test1.setName("sb");
        test1.setVl("1");
        test1.setMc("通过");

        TestDto test2 = new TestDto();
        test2.setName("sb");
        test2.setVl("2");
        test2.setMc("材料不齐全");
        TestDto test3 = new TestDto();
        test3.setName("sb");
        test3.setVl("3");
        test3.setMc("材料不合规矩");
        TestDto test4 = new TestDto();
        test4.setName("sb");
        test4.setVl("4");
        test4.setMc("没有复核");
        TestDto test5 = new TestDto();
        test5.setName("sb");
        test5.setVl("5");
        test5.setMc("没有审批");
        TestDto test6 = new TestDto();
        test6.setName("sb");
        test6.setVl("6");
        test6.setMc("表示不规范");

        lists.add(test1);
        lists.add(test2);
        lists.add(test3);
        lists.add(test4);
        lists.add(test5);
        lists.add(test6);

        Map<String,String> map = new HashMap<>();
        map.put("1","通过");
        map.put("2","材料不齐全");
        map.put("3","材料不合规矩");
        map.put("4","没有复核");
        map.put("5","没有审批");
        map.put("6","表示不规范");

        return R.ok().put("data",lists);
    }


    //测试下
    @RequestMapping(value = "/downloadTest")
    @ResponseBody
    public R downloadTest(HttpServletResponse response,HttpServletRequest request,TestDto testDto){

        try {
            download(response,request,testDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }


//    导出
    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
//    @GetMapping(value = "/download")
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request, TestDto testDto) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ComplexHeadData.class).sheet("模板").doWrite(data());
    }

    private List<ComplexHeadData> data() {
        List<ComplexHeadData> list = new ArrayList<ComplexHeadData>();
        for (int i = 0; i < 10; i++) {
            ComplexHeadData data = new ComplexHeadData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            data.setTest1("字符串测试" + i);
            data.setTest2("字符串测试" + i);
            data.setTest3("字符串测试" + i);
            data.setTest4("字符串测试" + i);
            list.add(data);
        }
        return list;
    }

    //点击单位信息
    @RequestMapping(value = "/companyInfo")
    @ResponseBody
    public R company(TestDto testDto){
        List<TestDto> listT = getTestLists(testDto);


        return R.ok().put("rows",listT).put("total",listT.size());
    }

}
