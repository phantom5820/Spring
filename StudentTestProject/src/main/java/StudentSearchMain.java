

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentSearchMain {

	public static void main(String[] args) {
		//문자열 하나 콘솔에서 입력받음 --> 검색할 이름 일부분 입력
				String search = null;
				Scanner sc = new Scanner(System.in);
				System.out.println("검색할 회원 이름 일부분을 입력하세요 ");
				search = sc.nextLine();
				
				//HTTP 프로토콜로 데이터를 요청하는 부분
				//1. 데이터를 요청할 API 주소를 문자열로 선언
				//2. 전달할 파라메터를 인코딩 작업을 해줘야한다
				try {
					search = URLEncoder.encode(search, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					//3. Url 완성해준다. 주소랑 파라메터(쿼리 스트링)를 조합을 한다.
					String apiUrl = "http://localhost:9999/studentSerach.do?kind=name&search="+search;
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
					JSONObject json = new JSONObject(result);
					if(json.getInt("Code") == 500) {
						throw new Exception(json.getString("result") + "\t" + json.getInt("Code") + "\t" + json.getString("Message")+"\n");
					}
					List<Object> list = json.getJSONArray("result").toList();
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i));
					}
					JSONArray arr = json.getJSONArray("result");
					for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						System.out.println(obj.getString("sno"));
						System.out.println(obj.getString("name"));
						System.out.println(obj.getString("major"));
						System.out.println(obj.getInt("score"));
					}
					br.close();
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception e) {
					try {
						//true 가 들어가야 추가모드, false 새파일로 생성
						FileOutputStream fos = new FileOutputStream("error.txt",true);
						PrintWriter pw = new PrintWriter(fos);
						//에러가 발생한 시점의 날짜를 세팅
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
						Calendar today = Calendar.getInstance();
						//로그 내용 앞에 발생한 날짜
						String str = sdf.format(today.getTime()) + "\t" + e.getMessage();
						System.out.println(str);
						pw.write(str);
						pw.flush();
						pw.close();
						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		}
