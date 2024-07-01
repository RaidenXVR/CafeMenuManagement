package com.keldelapan.CafeMenuManagement.entity;

import jakarta.persistence.*;

@Entity
public class UserCafe {
    @Id
    private String username;

    private String pwd;

    private Boolean is_admin;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profile_image;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public byte[] getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(byte[] profile_image) {
        this.profile_image = profile_image;
    }
}
