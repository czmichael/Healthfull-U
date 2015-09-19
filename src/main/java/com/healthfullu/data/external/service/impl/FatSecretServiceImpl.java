package com.healthfullu.data.external.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.healthfullu.data.external.service.FatSecretService;
import com.healthfullu.data.model.DataSource;
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

	private static final Logger LOGGER = Logger.getLogger(FatSecretServiceImpl.class.getName());
	
	@Autowired
	DataSourceService dataSourceService;
	
	@Override
	public List<FoodEntry> getUserFoodEntriesByDate(DataSource dataSource, Integer dateInt) {
		
		String url;
		try {
			url = genSigBaseString(dataSource, dateInt);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        Client c = Client.create(cc);
        WebResource resource = c.resource(url);
		String response = resource.get(String.class);
		
		
		FoodEntriesRoot foodEntriesRoot = new Gson().fromJson(response, FoodEntriesRoot.class);
		FoodEntries foodEntries = foodEntriesRoot.getFood_entries();
		
		

//for (FoodEntry foodEntry: foodEntries.getFood_entry()) {
//	System.out.println(foodEntry.getFood_id() + "   " + foodEntry.getFood_entry_description());
//	
//}

		return foodEntries.getFood_entry();
	}
	
	
	
	private String genSigBaseString(DataSource dataSource, Integer dateInt) throws UnsupportedEncodingException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		String consumerKey = "e3e6c77a91444eacbd978c9801808c9a";
		String secretKey = "884e354877484b649c2ad210b4d150f9";
		
		String oauthToken = dataSource.getOauthToken();
		String oauthTokenSecret = dataSource.getOauthTokenSecret();
		
		StringBuilder sigBaseString = new StringBuilder("");
		String httpMethod = URLEncoder.encode("GET", "UTF-8");
		sigBaseString.append(httpMethod);
		sigBaseString.append("&");
		
		String requestURL = URLEncoder.encode("http://platform.fatsecret.com/rest/server.api", "UTF-8");
		sigBaseString.append(requestURL);
		sigBaseString.append("&");

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
		LOGGER.fine("base: |" + sigBaseString.toString() + "|");

		String mySecretKey = URLEncoder.encode(secretKey, "UTF-8") + "&" + URLEncoder.encode(oauthTokenSecret, "UTF-8");
		LOGGER.fine("secret key: " + mySecretKey);		
		
		String sig = HmacSha1Signature.calculateRFC2104HMAC(sigBaseString.toString(), mySecretKey);
		LOGGER.fine("sig: " + sig);		
		
		String url = "http://platform.fatsecret.com/rest/server.api?" + params + "&oauth_signature=" + URLEncoder.encode(sig, "UTF-8");
		return url;
	}
}
