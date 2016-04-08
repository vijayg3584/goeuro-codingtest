package com.goeuro.codingtest.city;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.core.MediaType;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.goeuro.codingtest.city.vo.CityInfoVO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class CityDataRetriever {
	
	private static final String CITY_API_ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/";
	
	
	public static List<CityInfoVO> getCityInformation(String cityName) throws Exception {
		
		List<CityInfoVO> cityInfoList  = null;
		
		if(isNotBlank(cityName))
		{
			ClientResponse clientResponse = getResponse(cityName);
			
			if(200 == clientResponse.getStatus())
			{
				cityInfoList = clientResponse.getEntity(new GenericType<List<CityInfoVO>>(){});
				updateUserOnConsole(cityName, cityInfoList);
			}else{
				throw new RuntimeException("Error while getting data, error code: "+clientResponse.getStatus());
			}
			
		}else{
			throw new Exception("City name cannont be blank");
		}
		return cityInfoList;
	}


	private static void updateUserOnConsole(String cityName, List<CityInfoVO> cityInfoList) {
		if(cityInfoList.isEmpty())
		{
			System.out.println("No information for city: "+cityName);
		}
		else{
			System.out.println("Successfully fetched information for city: "+cityName);
		}
	}


	private static ClientResponse getResponse(String cityName) throws UnsupportedEncodingException {
		Client client = getInitializedClient();
		WebResource resource = client.resource(getEndPointWithCityName(cityName));
		ClientResponse clientResponse = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		return clientResponse;
	}


	private static String getEndPointWithCityName(String cityName) throws UnsupportedEncodingException {
		return CITY_API_ENDPOINT+URLEncoder.encode(cityName,"UTF-8");
	}


	private static Client getInitializedClient() {
		ClientConfig cfg = new DefaultClientConfig();
		cfg.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cfg);
		return client;
	}

}
