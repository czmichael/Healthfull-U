package com.healthfullu.util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TimeUtilTest {
	
	@Test
	public void testGetDaysSinceUnixEpoch() {
		Integer dateInt = TimeUtil.getDaysSinceUnixEpoch("2009-02-14");
		Assert.assertEquals(14289, dateInt.intValue());
	}
}
