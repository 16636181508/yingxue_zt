package com.zt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "yx_video")
public class Video implements Serializable {
    @Id
    private String id;

    private String title;

    private String brief;
    @Column(name = "cover_path")
    private String coverPath;
    @Column(name = "video_path")
    private String videoPath;
    @Column(name = "upload_time")
    private Date uploadTime;
    @Column(name = "like_count")
    private String likeCount;
    @Column(name = "play_count")
    private String playCount;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "group_id")
    private String groupId;

}