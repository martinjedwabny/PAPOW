package test.base;

import org.junit.Test;

import junit.framework.Assert;
import main.base.Alternative;

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
