package com.gokhantamkoc.javabootcamp.odevhafta3.service;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CryptoDataCSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.util.chart.CandleStickChart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChartService {
	
	CSVRepository cryptoDataCSVRepository;
	
	public ChartService(CSVRepository cryptoDataCSVRepository) {
		this.cryptoDataCSVRepository = cryptoDataCSVRepository;
	}
	
	public CandleStickChart createChartFromCryptoData() {
		// Bu metodu doldurmanizi bekliyoruz.
		CandleStickChart candleStickChart = new CandleStickChart("BTC/USDT Chart");
		String csvFileName = "Binance_BTCUSDT_d.csv";
		try {
			List<Candle> candles = cryptoDataCSVRepository.readCSV(csvFileName);
			for (Candle candle: candles) {
				candleStickChart.addCandle(candle.getTime(), candle.getOpen(),
						candle.getHigh(), candle.getLow(), candle.getClose(), candle.getVolume());
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(fileNotFoundException);
			System.out.println("File not found exception in CharService.java module!");
		} catch (IOException ioException) {
			System.out.println(ioException);
			System.out.println("IO exception in CharService.java module!");
		}
		return candleStickChart;
	}
}
