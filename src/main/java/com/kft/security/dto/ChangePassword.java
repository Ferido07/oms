package com.kft.security.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


/**
 * Created by Msolomon on 5/18/2018.
 */
public class ChangePassword {
    @NotEmpty
    private String oldPassword;

    @Length(min = 6, message = "Password should have at least 6 characters.")
    private String newPassword;

    @NotEmpty
    private String confirmNewPassword;

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
