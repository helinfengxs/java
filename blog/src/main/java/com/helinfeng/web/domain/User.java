package com.helinfeng.web.domain;

public class User {
    private String id;
    private String nickname;
    private String userName;
    private String passWord;
    private String email;
    private int types;
    private String avatar;
    private String create_time;
    private String update_time;

    /**
     * @param id          id
     * @param nickname    昵称
     * @param userName    用户名
     * @param passWord    密码
     * @param email       邮箱
     * @param types        角色类型
     * @param avatar      头像
     * @param create_time 创建时间
     * @param update_time 修改时间
     */
    public User(String id, String nickname, String userName, String passWord,
                String email, int types, String avatar, String create_time, String update_time) {
        this.id = id;
        this.nickname = nickname;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.types = types;
        this.avatar = avatar;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", types=" + types +
                ", avatar='" + avatar + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
