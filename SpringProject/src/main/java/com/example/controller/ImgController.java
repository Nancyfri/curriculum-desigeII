package com.example.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.Dic;
import com.example.entity.Img;
import com.example.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xzc
 * @since 2021-07-16
 */
@RestController
@RequestMapping("/img")
@CrossOrigin
public class ImgController {
    @Autowired
    private ImgService imgService;

    @GetMapping("/getImg")
    public HashMap<String, Object> getImg(@RequestParam(value = "id") int id){
        HashMap<String, Object> m = new HashMap<>();
        List<Img> imgList = imgService.getImg(id);
        if(imgList.size()>0){
            String jsonStr = JSON.toJSONString(imgList.get(0));//将java对象转换为json字符串
            JSONObject map = JSON.parseObject(jsonStr);//将json字符串转换为json对象
            m.put("msg","success!");
            m.put("code","1");
            m.put("result",map);
        }else {
            m.put("msg", "fail!");
            m.put("code", "0");
        }
        return m;
    }

    @PostMapping("/addImg")
    public HashMap<String, Object> addImg(@RequestBody Img img){
        HashMap<String, Object> m = new HashMap<>();
        Integer id = imgService.insertImgAndGetId(img);
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id.toString());
        if(id != -1 ){
            m.put("msg","success!");
            m.put("code","1");
            m.put("result",map);
        }else{
            m.put("msg", "fail!");
            m.put("code", "0");
        }
        return m;
    }

    @GetMapping("/getImgList")
    public HashMap<String, Object> getDicAll(){
        HashMap<String, Object> m = new HashMap<>();
        List<Img> imgList = imgService.getImgList();
        JSONArray jsonArray = new JSONArray();
        for(Img i :imgList){
            String jsonStr = JSON.toJSONString(i);//将每一个img转换为json字符串
            JSONObject jsonObject = JSON.parseObject(jsonStr);//将json字符串转换为json对象
            jsonArray.add(jsonObject);//将JSONObject对象添加到Json数组中
        }
        m.put("msg","success!");
        m.put("code","1");
        m.put("result",jsonArray);  //嵌套json
        return m;
    }

}

