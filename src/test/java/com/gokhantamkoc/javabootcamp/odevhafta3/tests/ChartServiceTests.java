package com.gokhantamkoc.javabootcamp.odevhafta3.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;

import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CryptoDataCSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.service.ChartService;
import com.gokhantamkoc.javabootcamp.odevhafta3.util.chart.CandleStickChart;

@RunWith(SpringRunner.class)
public class ChartServiceTests {

	@Test
	public void Test() {
		CSVRepository csvRepository = new CryptoDataCSVRepository();
		ChartService chartService = new ChartService(
			csvRepository
		);
		
		CandleStickChart candleStickChart = chartService.createChartFromCryptoData();
		assertThat(candleStickChart).isNotEqualTo(null);
	}
}
