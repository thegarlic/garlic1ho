package com.example.util.lucy;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.nhncorp.lucy.security.xss.XssFilter;


public class LucyTest {

	@Test
	public void pairQuoteCheckOtherCase() {
	    XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
	    String dirty = "<img src=\"<img src=1\\ onerror=alert(1234)>\" onerror=\"alert('XSS')\">";
	    String expected = "<img src=\"\"><!-- Not Allowed Attribute Filtered ( onerror=alert(1234)) --><img src=1\\>\" onerror=\"alert('XSS')\"&gt;";
	    String clean = filter.doFilter(dirty);
	    Assert.assertEquals(expected, clean);
	    System.out.println("dirty:"+dirty);
	    System.out.println("clean:"+clean);
	    dirty = "<img src='<img src=1\\ onerror=alert(1234)>\" onerror=\"alert('XSS')\">";
	    expected = "<img src=''><!-- Not Allowed Attribute Filtered ( onerror=alert(1234)) --><img src=1\\>\" onerror=\"alert('XSS')\"&gt;";
	    clean = filter.doFilter(dirty);
	    Assert.assertEquals(expected, clean);
	}
}
