package com.gokhantamkoc.javabootcamp.odevhafta3;

import java.io.File;

import org.jfree.chart.ChartUtilities;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CryptoDataCSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.service.ChartService;

@SpringBootApplication
public class DrawGraphicApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(DrawGraphicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ChartService chartService = new ChartService(
			new CryptoDataCSVRepository()
		);
		ChartUtilities.saveChartAsPNG(
				new File("result.png"), 
				chartService.createChartFromCryptoData().getCandleStickChart(), 
				1000, 1000);
	}
}
