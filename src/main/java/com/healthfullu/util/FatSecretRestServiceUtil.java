package com.healthfullu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.commons.codec.binary.Base64;


public class FatSecretRestServiceUtil {

	
	
	
	private static String genSigBaseString() throws UnsupportedEncodingException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
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
		params.append("date=16289&");
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
	
	/*
	 * Test main
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		String url = FatSecretRestServiceUtil.genSigBaseString();
		ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        Client c = Client.create(cc);
        WebResource resource = c.resource(url);
		String response = resource.get(String.class);
		System.out.println(response);
	}
	
	
}
