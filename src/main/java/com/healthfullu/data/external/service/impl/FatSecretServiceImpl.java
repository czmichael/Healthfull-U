package com.healthfullu.data.external.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.healthfullu.data.external.service.FatSecretService;
import com.healthfullu.data.model.FoodEntries;
import com.healthfullu.data.model.FoodEntriesRoot;
import com.healthfullu.data.model.FoodEntry;
import com.healthfullu.data.model.User;
import com.healthfullu.services.DataSourceService;
import com.healthfullu.util.HmacSha1Signature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@Service("fatSecretService") 
public class FatSecretServiceImpl implements FatSecretService {

	@Autowired
	DataSourceService dataSourceService;
	
	@Override
	public List<FoodEntry> getUserFoodEntriesByDate(User user, Integer dateInt) {
		
//		dateInt = 16288;
		
		String url;
		try {
			url = genSigBaseString(dateInt);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        Client c = Client.create(cc);
        WebResource resource = c.resource(url);
		String response = resource.get(String.class);
//System.out.println(response);
		
		
		
		FoodEntriesRoot foodEntriesRoot = new Gson().fromJson(response, FoodEntriesRoot.class);
		FoodEntries foodEntries = foodEntriesRoot.getFood_entries();
		
		
//System.out.println("food entry id: " + foodEntries.getFood_entry().size());	

for (FoodEntry foodEntry: foodEntries.getFood_entry()) {
	System.out.println(foodEntry.getFood_id() + "   " + foodEntry.getFood_entry_description());
	
}

		return foodEntries.getFood_entry();
	}
	
	
	
	private String genSigBaseString(Integer dateInt) throws UnsupportedEncodingException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		
		String consumerKey = "e3e6c77a91444eacbd978c9801808c9a";
		String secretKey = "884e354877484b649c2ad210b4d150f9";
		String oauthToken = "1ad4531fd75e426188ae2c679d680bd7";  //George
		String oauthTokenSecret = "1a9bfded5cad4bc68f6e019d79bd646d";
		
		StringBuilder sigBaseString = new StringBuilder("");
		String httpMethod = URLEncoder.encode("GET", "UTF-8");
		sigBaseString.append(httpMethod);
		sigBaseString.append("&");
		
		String requestURL = URLEncoder.encode("http://platform.fatsecret.com/rest/server.api", "UTF-8");
		sigBaseString.append(requestURL);
		sigBaseString.append("&");
		
		
		
//System.out.println(URLEncoder.encode("884e354877484b649c2ad210b4d150f9", "UTF-8"));
		

		StringBuilder params = new StringBuilder("");
		params.append("date=" + dateInt.toString() + "&");
		params.append("format=json&");
		params.append("method=food_entries.get&");
		params.append("oauth_consumer_key=" + consumerKey + "&");
		params.append("oauth_nonce=" + 123 + new Date().getTime() + "&");

		
		params.append("oauth_signature_method=HMAC-SHA1&");
		params.append("oauth_timestamp=" + new Date().getTime() + "&");

		
		params.append("oauth_token=" + URLEncoder.encode(oauthToken, "UTF-8") + "&");
		params.append("oauth_version=1.0");
		
		String params2 = URLEncoder.encode(params.toString(), "UTF-8");
		sigBaseString.append(params2);
		
System.out.println("base: |" + sigBaseString.toString() + "|");		
		


		String mySecretKey = URLEncoder.encode(secretKey, "UTF-8") + "&" + URLEncoder.encode(oauthTokenSecret, "UTF-8");
System.out.println("secret key: " + mySecretKey);		
		
//		String sig = HmacSha1Signature.calculateRFC2104HMAC(sigBaseString.toString(), mySecretKey);


String sig = HmacSha1Signature.calculateRFC2104HMAC(sigBaseString.toString(), mySecretKey);
		
		
	
		
		
System.out.println("sig: " + sig);		
		
		String url = "http://platform.fatsecret.com/rest/server.api?" + params + "&oauth_signature=" + URLEncoder.encode(sig, "UTF-8");
		return url;
	}
	
	
	
	
	private String response2 =
	"\"{" +  
   "\"food_entries\":{" +  
      "\"food_entry\":[" +  
         "{  \"food_id\":\"3436\"}, " +
         "{  \"food_id\":\"3423\"}  " +
         "]}}";
	
	
	private String response3 = "{'food_entries':{'food_entry':[{'food_id': '3436'}, {'food_id': '3437'}]}}";
	
	
}
