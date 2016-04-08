package com.goeuro.codingtest;

import java.util.List;

import com.goeuro.codingtest.city.vo.CityInfoVO;

import static com.goeuro.codingtest.city.CityDataRetriever.getCityInformation;
import static com.goeuro.codingtest.city.CityDataWriter.writeCityInfoToCSVFile;

public class Main {
	
	public static void main(String[] args) {
		
		if(args.length>0)
		{
			String cityName = getCompleteCityName(args);
			try {
				
				System.out.println("Program started for city: "+cityName );
				
				List<CityInfoVO> cityInfoList = getCityInformation(cityName );
				
				writeCityInfoToCSVFile(cityInfoList, cityName);
				
				System.out.println("Program successfully completed");
				
			} catch (Exception e) {
			System.err.println("Error while executing the program, caused by: "+e.getMessage());
			}
		}
		else{
			System.err.println("Please provide a city name as command line argument and run the program again");
		}
	}

	private static String getCompleteCityName(String[] args) {
		StringBuilder cityNameBig = new StringBuilder();
		for(String namePart:args)
		{
			cityNameBig.append(namePart).append(" ");
		}
		return cityNameBig.toString().trim();
	}
}
