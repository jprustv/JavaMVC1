package model;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.*;

public class VagalumeConnection {
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
	public String getLyrics(String artist, String song) {
		String lyrics;
		@SuppressWarnings("deprecation")
		String a = URLEncoder.encode(artist);
		@SuppressWarnings("deprecation")
		String s = URLEncoder.encode(song);
		String url = "http://api.vagalume.com.br/search.php?art="+a+"&mus="+s;
		
		try{
		//	System.out.println(url);
			InputStream is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			JSONArray jsonMUS = json.getJSONArray("mus");
			JSONObject jsonMusText = jsonMUS.getJSONObject(0);
			lyrics = jsonMusText.getString("text");
			is.close();
		} catch(Exception e){
			System.out.println("Exceção encontrada: "+ e);
			lyrics = null;
		} finally{
		
		}
		
		return lyrics;
	}
}
