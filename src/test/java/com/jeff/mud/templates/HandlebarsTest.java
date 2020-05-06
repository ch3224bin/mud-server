package com.jeff.mud.templates;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

public class HandlebarsTest {
	
	@Test
	public void testWho() throws IOException {
		Handlebars handlebars = new Handlebars();
		Template compile = handlebars.compile("/templates/who");
		System.out.println(compile.apply(""));
	}
}
