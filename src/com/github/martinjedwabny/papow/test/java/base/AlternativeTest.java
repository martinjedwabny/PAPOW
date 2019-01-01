package com.github.martinjedwabny.papow.test.java.base;

import org.junit.Test;

import com.github.martinjedwabny.papow.main.java.base.Alternative;

import junit.framework.Assert;

public class AlternativeTest {

	@Test
	public void shouldGetName() throws Exception {
		Assert.assertEquals(new Alternative("name").getName(), "name");
	}

	@Test
	public void shouldGetNameWithToString() throws Exception {
		Assert.assertEquals(new Alternative("name").toString(), "name");
	}

}
