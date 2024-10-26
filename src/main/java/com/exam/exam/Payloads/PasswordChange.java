package com.exam.exam.Payloads;

public record PasswordChange(String oldPassword, String newPassword, String repeatPassword) {

}
