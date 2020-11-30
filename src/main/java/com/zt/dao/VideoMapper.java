package com.zt.dao;

import com.zt.entity.Video;
import com.zt.entity.VideoExample;
import java.util.List;

import com.zt.po.VideoPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface VideoMapper  extends Mapper<Video> {

   List<VideoPo>  queryByReleaseTime();
}