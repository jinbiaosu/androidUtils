package com.vein.vlibs.data.model;

/**
 * Created by htjc on 17/1/17.
 */

public class UserInfoModel extends BaseModel {

    private Long id;
    private String name;
    private int age;

    //保存数据库的时间
    private  Long time;

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String desc;

    @Override
    public String toString() {
        return "{"
                + "id='" + id + "\'"
                + ",name='" + name + "\'"
                + ",age='" + age + "\'"
                + ",desc='" + desc + "\'"
                +",time='"+time+"\'"
                + "}";
    }
}
