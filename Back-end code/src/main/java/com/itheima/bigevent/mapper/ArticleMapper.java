package com.itheima.bigevent.mapper;

import com.itheima.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime}) ")
    void add(Article article);

    // 获取文章内容
    List<Article> list(Integer userId, Integer categoryId, String state);

    // 更新文章
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);

    // 删除文章
    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
