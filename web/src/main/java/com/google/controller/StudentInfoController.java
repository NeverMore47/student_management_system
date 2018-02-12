package com.google.controller;

import com.google.base.entity.Result;
import com.google.base.util.PropertiesLoader;
import com.google.entity.dto.StudentInfoDTO;
import com.google.entity.vo.StudentInfoVO;
import com.google.service.StudentInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by zengxiangyuan on 2018/2/9.
 * Description
 */
@Controller
@RequestMapping("/studentInfo")
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    private static final Properties common = PropertiesLoader.loadProperties("common.properties");

    @RequestMapping("/findStudentInfoList")
    @ResponseBody
    public Result findStudentInfoListByDto(StudentInfoDTO studentInfoDTO) {
        Result result = new Result();

        int count = studentInfoService.countStudentInfoListByDto(studentInfoDTO);
        result.addAttribute("count", count);

        if (count != 0) {
            List<StudentInfoVO> infoVOS = studentInfoService.findStudentInfoListByDto(studentInfoDTO);
            result.addAttribute("infoList", infoVOS);
        }

        return result;
    }

    @RequestMapping("/saveStudentInfo")
    @ResponseBody
    public Result saveStudentInfo(StudentInfoDTO infoDTO, @RequestParam(value = "type", defaultValue = "0") int type) {
        Result result = new Result();

        String studentNo = infoDTO.getStudentNo();
        String studentRealName = infoDTO.getStudentRealName();

        if (!StringUtils.isEmpty(studentNo) && !StringUtils.isEmpty(studentRealName) && !Objects.isNull(infoDTO.getStudentClassId()) && type != 0) {
            if (type == 1) {
                result.setSuccess(studentInfoService.saveStudentInfo(infoDTO));
            } else if (type == 2) {
                result.setSuccess(studentInfoService.updateStudentInfo(infoDTO));
            } else {
                result.setSuccessAndMessage(false, "参数错误");
            }

        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }

    @RequestMapping("/deleteStudentInfo")
    @ResponseBody
    public Result deleteStudentInfo(@RequestParam(value = "id", defaultValue = "0") long id) {
        Result result = new Result();

        if (id != 0) {
            result.setSuccess(studentInfoService.deleteStudentInfoById(id));
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }


    @RequestMapping("/uploadStudentInfo")
    @ResponseBody
    public Result uploadStudentInfo(@RequestParam(value = "file", required = false) MultipartFile file) {
        Result result = new Result();
        if (!Objects.isNull(file)) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 判断文件格式
            if (Objects.equals(suffix, "xls") || Objects.equals(suffix, "xlsx")) {
                int num = studentInfoService.importStudentInfoByExcel(file);
                result.setSuccessAndMessage(true, "成功导入" + num + "条数据");
            } else {
                result.setSuccessAndMessage(false, "文件格式错误");
            }
        } else {
            result.setSuccessAndMessage(false, "参数错误");
        }

        return result;
    }

    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response) {

        FileInputStream is = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=student_template.xlsx");

        File file = new File(common.getProperty("templatePath"));
        try {
            is = new FileInputStream(file);
            int len;
            byte buffer[] = new byte[1024];
            OutputStream out = response.getOutputStream();
            while ((len = is.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
