package ru.pomeshikov.controller;

import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import ru.pomeshikov.BaseTest;

public class AuthControllerTest extends BaseTest {

	@Test
	public void test() throws NoSuchAlgorithmException {
		Assert.assertEquals("2095312189753de6ad47dfe20cbe97ec", DigestUtils.md5DigestAsHex("hello-world".getBytes()));
	}

}
