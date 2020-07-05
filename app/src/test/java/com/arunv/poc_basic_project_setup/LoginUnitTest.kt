package com.arunv.poc_basic_project_setup

import fragments.LoginFragment
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class LoginUnitTest {

    private var loginFragment: LoginFragment? = null

    @Before
    fun start() {
        loginFragment = LoginFragment()
    }

    @After
    fun stop() {
        loginFragment = null
    }

    @Test
    fun test_validateUserName() {

        Assert.assertNotNull(loginFragment)
        Assert.assertTrue(loginFragment?.validateUserNameLength("123456")!!)
        Assert.assertFalse(loginFragment?.validateUserNameLength("123")!!)

    }

    @Test
    fun test_validatePassword() {
        Assert.assertNotNull(loginFragment)
        Assert.assertTrue(loginFragment?.validatePasswordLength("123456")!!)
        Assert.assertFalse(loginFragment?.validatePasswordLength("123")!!)

    }

}