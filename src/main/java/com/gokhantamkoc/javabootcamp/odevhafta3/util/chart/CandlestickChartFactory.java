package com.gokhantamkoc.javabootcamp.odevhafta3.util.chart;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public class CandlestickChartFactory {
	// OHLC
	private OHLCSeriesCollection candlestickDataset;
	private OHLCSeries ohlcSeries;
	private NumberAxis priceAxis;
	private CandlestickRenderer candlestickRenderer;
	private XYPlot candlestickSubplot;
	
	// Volume
	private TimeSeriesCollection volumeDataset;
	private TimeSeries volumeSeries;
	private NumberAxis volumeAxis;
	private XYPlot volumeSubplot;
	
	// Common Date Axis
	private XYBarRenderer timeRenderer;
	private DateAxis dateAxis;
	
	// Main Plot
	private CombinedDomainXYPlot mainPlot;
	
	public CandlestickChartFactory() {
		
	}
	
	public JFreeChart createChart(String chartTitle) {
		this.createTimeRenderer();
		this.createDateAxis();
		this.createOHLCPlot();
		this.createVolumePlot();
		this.createMainPlot();
		
		// Create mainPlot
		this.createMainPlot();

		JFreeChart chart = new JFreeChart(
			chartTitle,
			JFreeChart.DEFAULT_TITLE_FONT, 
			this.mainPlot, 
			true
		);
		chart.removeLegend();
		return chart;
	}

	private void createTimeRenderer() {
		this.timeRenderer = new XYBarRenderer();
		this.timeRenderer.setShadowVisible(false);
		this.timeRenderer.setBaseToolTipGenerator(
			new StandardXYToolTipGenerator(
				"Volume--> Time={1} Size={2}",
				new SimpleDateFormat("kk:mm"), 
				new DecimalFormat("0")
			)
		);
	}
	
	private void createDateAxis() {
		// Creating charts common dateAxis
		this.dateAxis = new DateAxis("Time");
		this.dateAxis.setDateFormatOverride(new SimpleDateFormat("kk:mm"));
		// reduce the default left/right margin from 0.05 to 0.02
		this.dateAxis.setLowerMargin(0.02);
		this.dateAxis.setUpperMargin(0.02);
	}

	private void createOHLCPlot() {
		this.createCandleStickOHLCDataset();
		this.createCandleStickPriceAxis();
		this.createCandleStickChartRenderer();
		this.createCandleStickSubplot();
	}

	private void createCandleStickOHLCDataset() {
		this.candlestickDataset = new OHLCSeriesCollection();
		this.ohlcSeries = new OHLCSeries("close");
		candlestickDataset.addSeries(this.ohlcSeries);
	}

	private void createCandleStickPriceAxis() {
		this.priceAxis = new NumberAxis("close");
		priceAxis.setAutoRangeIncludesZero(false);
	}
	
	private void createCandleStickChartRenderer() {
		this.candlestickRenderer = new CandlestickRenderer(
			CandlestickRenderer.WIDTHMETHOD_AVERAGE,
			false, 
			new CustomHighLowItemLabelGenerator(new SimpleDateFormat("kk:mm"), new DecimalFormat("0.000"))
		);
	}
	
	private void createCandleStickSubplot() {
		this.candlestickSubplot = new XYPlot(this.candlestickDataset, null, this.priceAxis, this.candlestickRenderer);
		candlestickSubplot.setBackgroundPaint(Color.white);
	}
	
	private void createVolumePlot() {
		this.createVolumeDataSet();
		this.createVolumeAxis();
		this.createVolumeSubPlot();
	}

	private void createVolumeDataSet() {
		this.volumeDataset = new TimeSeriesCollection();
		this.volumeSeries = new TimeSeries("volume");
		this.volumeDataset.addSeries(this.volumeSeries);
	}
	
	private void createVolumeAxis() {
		this.volumeAxis = new NumberAxis("volume");
		this.volumeAxis.setAutoRangeIncludesZero(false);
		this.volumeAxis.setNumberFormatOverride(new DecimalFormat("0"));
	}
	
	private void createVolumeSubPlot() {
		this.volumeSubplot = new XYPlot(
			this.volumeDataset, 
			null,
			this.volumeAxis, 
			this.timeRenderer
		);
		volumeSubplot.setBackgroundPaint(Color.white);
	}

	private void createMainPlot() {
		this.mainPlot = new CombinedDomainXYPlot(this.dateAxis);
		this.mainPlot.setGap(10.0);
		this.mainPlot.add(this.candlestickSubplot, 3);
		this.mainPlot.add(this.volumeSubplot, 1);
		this.mainPlot.setOrientation(PlotOrientation.VERTICAL);
	}
	
	public void addOhlc(FixedMillisecond time, double open, double high, double low, double close) {
		this.ohlcSeries.add(
				time,
				open, 
				high, 
				low, 
				close);
	}
	
	public void addVolume(FixedMillisecond time, double volume) {
		this.volumeSeries.add(
				time,
				volume);
	}
}
