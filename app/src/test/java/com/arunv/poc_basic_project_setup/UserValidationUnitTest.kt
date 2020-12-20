package com.arunv.poc_basic_project_setup

import org.junit.Assert
import org.junit.Test
import util.CommonUtils


class UserValidationUnitTest {

    @Test
    fun test_validateUserName_valid_email() {
        Assert.assertTrue(CommonUtils.isValidUserName("arunv@gmail.com", 6))
    }

    @Test
    fun test_validatePassword_valid_password() {
        Assert.assertTrue(CommonUtils.isValidPasswordValid("123456", 6))
    }

    @Test
    fun test_validateUserName_length() {
        Assert.assertFalse(CommonUtils.isValidUserName("abcd", 6))
    }

    @Test
    fun test_validateUserName_in_valid_email_fail() {
        Assert.assertFalse(CommonUtils.isValidUserName("abcd", 6))
    }

    @Test
    fun test_validatePassword_length_fail() {
        Assert.assertFalse(CommonUtils.isValidPasswordValid("1234", 6))
    }

}