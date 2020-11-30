package com.zt.server.serverImpl;

import com.zt.dao.VideoMapper;
import com.zt.entity.Video;
import com.zt.entity.VideoExample;
import com.zt.po.VideoPo;
import com.zt.server.VideoServer;
import com.zt.util.AliyunOSSUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VideoSernerImpl implements VideoServer {
    @Resource
    private VideoMapper videoMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public  HashMap<String,Object> queryBypage(Integer page, Integer rows) {
        //获取map集合
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //设置条件
        VideoExample example = new VideoExample();
        //设置分页参数
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //分页查询数据
        List<Video> videos = videoMapper.selectByExampleAndRowBounds(example, rowBounds);

        //设置分页数据
        map.put("rows",videos);
        //查询总条数
        int count = videoMapper.selectCountByExample(example);
        //设置总页数
        map.put("records",count);
        //设置总页数
        Integer total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        return map;
    }

    @Override
    public String add(Video video) {
       //复制id
        String uuid = UUID.randomUUID().toString();
        video.setId(uuid);
        video.setUploadTime(new Date());

        //调用添加方法
        videoMapper.insertSelective(video);
        return uuid;

    }
    //文件上传将数据上长传到与服务器
    @Override
    public void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request) {
                //获取文件路径
                String realPath = request.getSession().getServletContext().getRealPath("/upload/video");
                //判断文件夹是否存在
                File file = new File(realPath);
                if (file.exists()){
                    file.mkdir();
        }
        //获取文件名称
        String filename = videoPath.getOriginalFilename();
        //创建一个新的名字
        String newNane = new Date().getTime()+""+filename;

        //文件上传
        try{
            videoPath.transferTo(new File(realPath,newNane));
        }catch (Exception e){e.printStackTrace();}
        System.out.println("cccccccccccccccc");
        //修改文件路径
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(id);

        Video video = new Video();
        //http://zt2005.oss-cn-beijing.aliyuncs.com/video/1606227332589-%E5%A5%BD%E7%9C%8B.mp4
        video.setCoverPath("http://zt2005.oss-cn-beijing.aliyuncs.com/video/"+newNane+"?x-oss-process=video/snapshot,t_0,f_jpg,w_0,h_0,m_fast,ar_auto"); //设置封面
        video.setVideoPath("http://zt2005.oss-cn-beijing.aliyuncs.com/video/"+newNane);//设置视频地址
        //video.setId(id);

        //修改
        //videoMapper.updateByPrimaryKeySelective(video);
        videoMapper.updateByExampleSelective(video, example);

    }

    //删除
    @Override
    public void delete(Video video) {

        //根据id查询数据
        Video videos = videoMapper.selectByPrimaryKey(video);

        String videoPath = videos.getVideoPath().replace("http://zt2005.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = videos.getCoverPath().replace("http://zt2005.oss-cn-beijing.aliyuncs.com/", "");

        //2.删除视频   432425435-xxx.mp4
        AliyunOSSUtil.deleteFile("zt2005",videoPath);
        //3.删除封面
        AliyunOSSUtil.deleteFile("zt2005",coverPath);
        //1.删除数据

        videoMapper.deleteByPrimaryKey(video);

    }

    @Override
    public void update(Video video) {
        if (video.getVideoPath() == "") {
            video.setVideoPath(null);
        }
        System.out.println("修改：" + video);
        videoMapper.updateByPrimaryKeySelective(video);

        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(video.getId());
        Video videos = videoMapper.selectOneByExample(example);
    }

    @Override
    public void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request) {
        //1.视频上传至阿里云   字节数组
        //获取文件名
        String filename = videoPath.getOriginalFilename();
        //拼接时间戳    1606185263426-草原.mp4
        String newName=new Date().getTime()+"-"+filename;

        //拼接视频文件夹
        String videoName="video/"+newName;

        /*
         * 上传视频至阿里云
         * 参数:
         *   videoPath: MultipartFile类型的文件
         *   bucketName:存储空间名
         *   objectName:文件名
         * */
        AliyunOSSUtil.uploadFileByte(videoPath,"zt2005",videoName);


        //截取文件名
        String[] split = newName.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";

        /*
         * 2.截取视频第一帧
         * 参数:
         *   bucketName:存储空间名
         *   videoName:视频名  文件夹
         *   coverName:封面名
         * */
        AliyunOSSUtil.interceptVideoCover("zt2005", videoName,coverName);



        //4.修改视频的信息
        Video video = new Video();
        video.setId(id);
        //http://zt2005.oss-cn-beijing.aliyuncs.com/video/1606227332589-%E5%A5%BD%E7%9C%8B.mp4
        video.setVideoPath("http://zt2005.oss-cn-beijing.aliyuncs.com/"+videoName);
        video.setCoverPath("http://zt2005.oss-cn-beijing.aliyuncs.com/"+coverName);
        videoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public List<VideoPo> queryByReleaseTime() {

        List<VideoPo> videoPos = videoMapper.queryByReleaseTime();
        for (VideoPo videoPo : videoPos) {
            //获取视频id
            String id = videoPo.getId();
            //根据视频id redis查看视频点赞数
            videoPo.setLikeCount(8);
        }
        return videoPos;
    }
}
