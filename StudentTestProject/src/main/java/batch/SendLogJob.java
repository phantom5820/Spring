package batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendLogJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			File file = new File("error.txt");
			FileReader fr = new FileReader("error.txt");
			BufferedReader br = new BufferedReader(fr);
			
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				sendLog(str.split("\t"));
			}
			System.out.println("파일삭제 : " + file.delete());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}

	private void sendLog(String[] split) {
		String queryString = "";
		String[] paramArr = {"log_date","error_code","content"};
		//쿼리스트링 완성
		try {
		for (int i = 0; i < paramArr.length; i++) 
				queryString += paramArr[i] + "=" + URLEncoder.encode(split[i], "utf-8");
			System.out.println(queryString);
			//웹 서버로 전송
			String apiUrl = "http:localhost:9999/sendLog.do?"+queryString;
			URL url = new URL(apiUrl);
			//API 요청부분 ---> 데이터 전송
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//요청결과 받음
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			System.out.println(br.readLine());
			br.close();
			conn.disconnect();
			
			
			
		} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}

	
	
}
