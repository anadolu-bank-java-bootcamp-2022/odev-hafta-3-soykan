package com.gokhantamkoc.javabootcamp.odevhafta3.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CryptoDataCSVRepository;

@RunWith(SpringRunner.class)
public class CryptoDataCSVRepositoryTests {

	@Test
	public void TestReadCSV() {
		CSVRepository csvRepository = new CryptoDataCSVRepository();
		try {
			List<Candle> candles = csvRepository.readCSV("Binance_BTCUSDT_d.csv");
			assertThat(candles.size()).isGreaterThan(0);
			assertThat(candles.get(0).getTime()).isEqualTo(1_655_600_000_000L);
			assertThat(candles.get(18).getTime()).isEqualTo(1_654_040_000_000L);
		} catch(Exception ex) {
        	System.out.println(ex.getMessage());
        	ex.printStackTrace();
			fail();
        }
	}
}
