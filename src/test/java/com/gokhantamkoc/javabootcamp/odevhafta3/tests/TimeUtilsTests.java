package com.gokhantamkoc.javabootcamp.odevhafta3.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.gokhantamkoc.javabootcamp.odevhafta3.util.TimeUtils;

@RunWith(SpringRunner.class)
public class TimeUtilsTests {

	@Test
	public void TestConvertToDate() {
		Date date = TimeUtils.convertToDate(1_655_600_000_000L);
		assertThat(date).isNotEqualTo(null);
		assertThat(date.getHours()).isGreaterThanOrEqualTo(0);
		assertThat(date.getMinutes()).isEqualTo(53);
	}
}
