package com.arunv.poc_basic_project_setup

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class RegistrationUnitTest {

    private var myRegistrationFragment: RegistrationFragment? = null

    @Before
    fun start() {
        myRegistrationFragment = RegistrationFragment()
    }

    @After
    fun stop() {
        myRegistrationFragment = null
    }

    @Test
    fun test_validateUserName() {

        Assert.assertNotNull(myRegistrationFragment)
        Assert.assertTrue(myRegistrationFragment?.validateUserNameLength("123456")!!)
        Assert.assertFalse(myRegistrationFragment?.validateUserNameLength("123")!!)

    }

    @Test
    fun test_validatePassword() {
        Assert.assertNotNull(myRegistrationFragment)
        Assert.assertTrue(myRegistrationFragment?.validatePasswordLength("123456")!!)
        Assert.assertFalse(myRegistrationFragment?.validatePasswordLength("123")!!)

    }

    @Test
    fun test_validateConfirmPassword() {

        Assert.assertNotNull(myRegistrationFragment)
        Assert.assertTrue(myRegistrationFragment?.validateConfirmPasswordLength("123456")!!)
        Assert.assertFalse(myRegistrationFragment?.validateConfirmPasswordLength("123")!!)

    }

    @Test
    fun test_validateMatchPassword() {

        Assert.assertNotNull(myRegistrationFragment)
        Assert.assertTrue(
            myRegistrationFragment?.validateMatchPasswordAndConfirmPassword(
                "123456",
                "123456"
            )!!
        )
        Assert.assertFalse(
            myRegistrationFragment?.validateMatchPasswordAndConfirmPassword(
                "23456",
                "12356"
            )!!
        )
    }

}