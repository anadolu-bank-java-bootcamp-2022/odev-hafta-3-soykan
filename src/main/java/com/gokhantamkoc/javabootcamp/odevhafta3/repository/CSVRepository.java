package com.gokhantamkoc.javabootcamp.odevhafta3.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;

public interface CSVRepository {
	
	List<Candle> readCSV(String filename) throws FileNotFoundException, IOException;
}
