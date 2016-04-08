package com.goeuro.codingtest.city;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.goeuro.codingtest.city.vo.CityInfoVO;

public class CityDataWriter {

	private static final String COMMA_SEP = ", ";
	private static final String DIR = System.getProperty("user.home");
	private static final String EOL = System.getProperty("line.separator");
	private static final String CSV_FILE_EXTN = ".csv";

	public static void writeCityInfoToCSVFile(List<CityInfoVO> cityInfoList, String cityName) throws IOException {
		FileWriter fileWriter = null;
		try {

			if (!cityInfoList.isEmpty()) {
				
				StringBuilder output = new StringBuilder("_id, name, type, latitude, longitude");
				
				final String fileName = DIR + File.separator + cityName + CSV_FILE_EXTN;
				
				fileWriter = new FileWriter(fileName);
				
				for (CityInfoVO cityInfo : cityInfoList) {
					output.append(EOL);
					output.append(cityInfo.get_id()).append(COMMA_SEP);
					output.append(cityInfo.getName()).append(COMMA_SEP);
					output.append(cityInfo.getType()).append(COMMA_SEP);
					output.append(cityInfo.getGeo_position().getLatitude()).append(COMMA_SEP);
					output.append(cityInfo.getGeo_position().getLongitude());

					fileWriter.write(output.toString());
					output.setLength(0); //clearing the builder
				}
				System.out.println("CSV file successfully generated at location: "+fileName);
			}
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (Exception e) {
				System.err.println("Error while closing filewriter caused because: "+e.getMessage());
			}
		}
	}
}
