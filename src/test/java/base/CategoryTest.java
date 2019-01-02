package test.java.base;

import org.junit.Test;

import junit.framework.Assert;
import main.java.base.Category;

public class CategoryTest {
	
	Category c = new Category("France");

	@Test
	public void shouldGetDescription() throws Exception {
		Assert.assertEquals(c.getDescription(), "France");
	}

	@Test
	public void shouldSetDescription() throws Exception {
		c.setDescription("Algeria");
		Assert.assertEquals(c.getDescription(), "Algeria");
		c.setDescription("France");
	}

}
