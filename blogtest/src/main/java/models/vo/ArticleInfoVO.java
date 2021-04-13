package models.vo;

import models.ArticleInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:增加作者属性
 * User: starry
 * Date: 2021 -04 -13
 * Time: 14:51
 */
public class ArticleInfoVO extends ArticleInfo {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
