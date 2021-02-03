import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NaverMain {
public static void main(String[] args) {
	//HTTP 프로토콜로 데이터를 요청하는 부분
	//1. 데이터를 요청할 API 주소를 문자열로 선언
	//2. 전달할 파라메터를 인코딩 작업을 해줘야한다
	try {
		//3. Url 완성해준다. 주소랑 파라메터(쿼리 스트링)를 조합을 한다.
		String apiUrl = "https://localhost:9999";
		URL url = new URL(apiUrl);
		//4. open connection 해서 요청을 하는 부분 (실제로 데이터를 보내주는 부분이다.)
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//5. 데이터를 보냈으니 문자열을 받아야한다. 문자열을 읽어줘야하기 때문에 inputstream을 초기화 해서 읽는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		//6. 데이가 만약 json이면 파싱 해서 원하는 데이터만 추출 후 출력한다!
		String result = "";
		//7. 여기서 데이터를 하나씩 뽑아오는 부분을 만든다.
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			result += str;
		}
		System.out.println(result);
		//데이터가 json이기 때문에 파싱을 해준다.
		br.close();
		
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}



