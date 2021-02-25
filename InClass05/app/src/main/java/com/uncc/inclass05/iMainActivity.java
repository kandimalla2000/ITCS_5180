package com.uncc.inclass05;
/**
 * InClass #05
 * File Name:iMainActivity.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */

/**
 * This interface using to send account to MainActivity.
 */
public interface iMainActivity {
    void setAccountGoToAppCategoriesFragment(String token, DataServices.Account account);
    void deleteAccount(DataServices.Account account);
    void openLoginWindow();
    void openRegisterWindow();
}
