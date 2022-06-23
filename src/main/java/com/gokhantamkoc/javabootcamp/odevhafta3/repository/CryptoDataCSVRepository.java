package com.gokhantamkoc.javabootcamp.odevhafta3.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;

public class CryptoDataCSVRepository implements CSVRepository {
	
	private final String COMMA_DELIMITER = ",";

	@Override
	public List<Candle> readCSV(String filename) throws FileNotFoundException, IOException {
		List<Candle> candles = new ArrayList<Candle>();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		// Bu alandan itibaren kodunuzu yazabilirsiniz

		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line;
		final int INDEX_OF_UNIX_TIMESTAMP_VALUE = 0;
		final int INDEX_OF_OPEN_VALUE = 3;
		final int INDEX_OF_HIGH_VALUE = 4;
		final int INDEX_OF_LOW_VALUE = 5;
		final int INDEX_OF_CLOSE_VALUE = 6;
		final int INDEX_OF_VOLUME_VALUE = 7;
		bufferedReader.readLine(); // Skip first (header) line
		while ((line = bufferedReader.readLine()) != null) {
			String[] splittedLineData = line.split(COMMA_DELIMITER);

			String timestampValue = splittedLineData[INDEX_OF_UNIX_TIMESTAMP_VALUE];
			long timestamp = Long.parseLong(timestampValue);

			String openValue = splittedLineData[INDEX_OF_OPEN_VALUE];
			double open = Double.parseDouble(openValue);

			String highValue = splittedLineData[INDEX_OF_HIGH_VALUE];
			double high = Double.parseDouble(highValue);

			String lowValue = splittedLineData[INDEX_OF_LOW_VALUE];
			double low = Double.parseDouble(lowValue);

			String closeValue = splittedLineData[INDEX_OF_CLOSE_VALUE];
			double close = Double.parseDouble(closeValue);

			String volumeValue = splittedLineData[INDEX_OF_VOLUME_VALUE];
			double volume = Double.parseDouble(volumeValue);

			Candle candle = new Candle(timestamp, open, high, low, close, volume);
			candles.add(candle);
		}
		// Bu alandan sonra kalan kod'a dokunmayiniz.
		return candles;
	}

}
