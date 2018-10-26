package voting;

import org.junit.Test;

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
