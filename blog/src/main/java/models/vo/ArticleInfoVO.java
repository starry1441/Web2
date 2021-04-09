package models.vo;

import models.ArticleInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -04 -08
 * Time: 19:56
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
