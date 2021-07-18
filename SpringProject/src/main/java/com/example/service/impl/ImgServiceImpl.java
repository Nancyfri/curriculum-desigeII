package com.example.service.impl;

import com.example.entity.Img;
import com.example.mapper.ImgMapper;
import com.example.service.ImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzc
 * @since 2021-07-16
 */
@Service("ImgService")
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
    @Autowired
    private ImgMapper imgMapper;

    //获取词书封面
    @Override
    public List<Img> getImg(int id){
        return imgMapper.getImg(id);
    }
}