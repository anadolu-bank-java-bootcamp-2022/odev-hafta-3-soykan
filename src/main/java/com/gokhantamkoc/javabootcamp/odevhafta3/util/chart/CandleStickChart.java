package com.gokhantamkoc.javabootcamp.odevhafta3.util.chart;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.FixedMillisecond;

import com.gokhantamkoc.javabootcamp.odevhafta3.util.TimeUtils;

public class CandleStickChart extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private String chartTitle;
	private JFreeChart candlestickChart;
	private CandlestickChartFactory candleStickChartFactory;
	
	public CandleStickChart(String chartTitle) {
		this.chartTitle = chartTitle;
		this.candleStickChartFactory = new CandlestickChartFactory();
		this.candlestickChart = this.candleStickChartFactory.createChart(this.chartTitle);
	}
	
	public void addCandle(long time, double open, double high, double low, double close, double volume) {
		try {
			FixedMillisecond timeAsFixedMillisecond = new FixedMillisecond(
				TimeUtils.convertToDate(time)
			);
			this.candleStickChartFactory.addOhlc(
				timeAsFixedMillisecond, 
				open, 
				high, 
				low, 
				close
			);
			this.candleStickChartFactory.addVolume(timeAsFixedMillisecond, volume);
		} catch (Exception ex) {
			System.out.println("Error ocurred while adding candle to the chart: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public JFreeChart getCandleStickChart() {
		return candlestickChart;
	}
}
