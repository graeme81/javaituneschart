import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

public class Chart {
	
	private int num;
	private List<String> song = new ArrayList<String>();
	private List<String> artist = new ArrayList<String>();
	private String date;
	
	public Chart(int number) throws Exception{
		num = number;
		
		String url = "https://itunes.apple.com/gb/rss/topsongs/limit="+num+"/json";
		URL obj = new URL(url);
		HttpURLConnection connect = (HttpURLConnection)obj.openConnection();
		// default is GET
		connect.setRequestMethod("GET");
		connect.setRequestProperty("User-Agent","Mozilla/5.0");
		
		int responseCode = connect.getResponseCode();
		System.out.println("\nSending 'GET' request to URL: " + url);
		System.out.println("Response Code: " + responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) !=null){
			response.append(inputLine);
		}
		in.close();
		
		String out = response.toString();
		
		JSONObject myResponse = new JSONObject(out);
		JSONObject feed = myResponse.getJSONObject("feed");
		JSONObject updated = feed.getJSONObject("updated");
		date = updated.getString("label");
		
		JSONArray entry = feed.getJSONArray("entry"); 
		
		for (int i = 0; i < num; i++){
			JSONObject track = entry.getJSONObject(i);
			JSONObject title = track.getJSONObject("im:name");
			JSONObject singer = track.getJSONObject("im:artist");
			
			song.add(title.getString("label"));
			artist.add(singer.getString("label"));
				
		}
		
	}

	public List<String> getArtist() {
		return artist;
	}

	public List<String> getSong() {
		return song;
	}

	public String getDate() {
		String sub = date.substring(0, 10);
		String[] bits = sub.split("-");
		String newDate = bits[2]+"/"+bits[1]+"/"+bits[0];
		return newDate;
	}

	

}
