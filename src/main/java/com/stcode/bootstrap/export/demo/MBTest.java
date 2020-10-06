package com.stcode.bootstrap.export.demo;

import com.alibaba.excel.EasyExcel;
import com.stcode.bootstrap.model.Ylybcx;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;


@Controller
@RequestMapping("/mb")
public class MBTest {


    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        String str =  Class.class.getClass().getResource("/").getPath().replaceFirst("/", "");
        String[] split = str.split("/");
        String path = split[0] +  File.separator +  split[1] + File.separator +split[2];

        String templateFileName =   path+"\\src\\main\\java\\com\\stcode\\bootstrap\\exceltemplate\\yldetail.xlsx";

        Ylybcx fillData = new Ylybcx();
        fillData.setMainTotal("111111");
//        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);




        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
//        EasyExcel.write(response.getOutputStream(), DownloadData.class).sheet("模板").doWrite(data());
        EasyExcel.write(response.getOutputStream(),Ylybcx.class).withTemplate(templateFileName).sheet().doFill(fillData);
    }
}
