package com.healthfullu.data.external.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(FatSecretServiceImpl.class);
	private static final String CONSUMER_KEY = "e3e6c77a91444eacbd978c9801808c9a";
	private static final String SECRET_KEY = "884e354877484b649c2ad210b4d150f9";
	
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
		
		if(LOGGER.isDebugEnabled()) {
			for (FoodEntry foodEntry: foodEntries.getFood_entry()) {
				LOGGER.debug(foodEntry.getFood_id() + "   " + foodEntry.getFood_entry_description());
			}
		}
		return foodEntries.getFood_entry();
	}
	
	
	private String genSigBaseString(DataSource dataSource, Integer dateInt) throws UnsupportedEncodingException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
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
		params.append("oauth_consumer_key=" + CONSUMER_KEY + "&");
		params.append("oauth_nonce=" + 123 + new Date().getTime() + "&");
		params.append("oauth_signature_method=HMAC-SHA1&");
		params.append("oauth_timestamp=" + new Date().getTime() + "&");
		params.append("oauth_token=" + URLEncoder.encode(oauthToken, "UTF-8") + "&");
		params.append("oauth_version=1.0");
		
		String params2 = URLEncoder.encode(params.toString(), "UTF-8");
		sigBaseString.append(params2);
		LOGGER.debug("base: |" + sigBaseString.toString() + "|");

		String mySecretKey = URLEncoder.encode(SECRET_KEY, "UTF-8") + "&" + URLEncoder.encode(oauthTokenSecret, "UTF-8");
		LOGGER.debug("secret key: " + mySecretKey);		
		
		String sig = HmacSha1Signature.calculateRFC2104HMAC(sigBaseString.toString(), mySecretKey);
		LOGGER.debug("sig: " + sig);		
		
		String url = "http://platform.fatsecret.com/rest/server.api?" + params + "&oauth_signature=" + URLEncoder.encode(sig, "UTF-8");
		return url;
	}
	
	
	
	
	public void getUserProfileAuthInfo() {
		
		
		
		
		
		
		
		
		
		
		String url;
		
		try {
			StringBuilder sigBaseString = new StringBuilder("");
			String httpMethod = URLEncoder.encode("GET", "UTF-8");
			sigBaseString.append(httpMethod);
			sigBaseString.append("&");
			
			String requestURL = URLEncoder.encode("http://platform.fatsecret.com/rest/server.api", "UTF-8");
			sigBaseString.append(requestURL);
			sigBaseString.append("&");
			
			StringBuilder params = new StringBuilder("");
//		params.append("date=" + dateInt.toString() + "&");
			
			params.append("oauth_consumer_key=" + CONSUMER_KEY + "&");
			params.append("oauth_signature_method=HMAC-SHA1&");
			params.append("oauth_timestamp=" + new Date().getTime() + "&");
			params.append("oauth_nonce=" + 123 + new Date().getTime() + "&");
			params.append("oauth_version=1.0" + "&");
			params.append("method=profile.get_auth&");
			params.append("user_id=srizvi1@gmail.com&");
			
			
			params.append("format=json&");
			
			
			
			
			
			
			
			String params2 = URLEncoder.encode(params.toString(), "UTF-8");
			sigBaseString.append(params2);
			
			String mySecretKey = URLEncoder.encode(SECRET_KEY, "UTF-8") + "&" + URLEncoder.encode("", "UTF-8");
			
			String sig = HmacSha1Signature.calculateRFC2104HMAC(sigBaseString.toString(), mySecretKey);
			
			
		    url = "http://platform.fatsecret.com/rest/server.api?" + params + "oauth_signature=" + URLEncoder.encode(sig, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        Client c = Client.create(cc);
        WebResource resource = c.resource(url);
		String response = resource.get(String.class);
		System.out.println(response);
		
	}
	
	public static void main(String[] args) {
		FatSecretServiceImpl service = new FatSecretServiceImpl();
		service.getUserProfileAuthInfo();
		
	}
	
	
	
}
