package org.zerock.scheduler.common;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CommonService {
    public String requestAPI(StringBuffer url, String property) {
        String result = "";
        try {
            // URL url = new URL(apiURL);

            // 연결할 객체가 HTTP통신을 할 예정이므로 HTTP간의 연결 개체 1개를 만듬.
            HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", property);
            int responseCode = con.getResponseCode();
            // 여러 가지 정보를 읽어 들이는데 버퍼를 통해 읽어 들이기 위해 BufferReader 를 사용
            BufferedReader br;
            System.out.print("responseCode=" + responseCode);
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();			// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            con.disconnect();
            result = res.toString();
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public String requestAPI(StringBuffer url) {
        String result = "";
        try {
            // URL url = new URL(apiURL);

            // 연결할 객체가 HTTP통신을 할 예정이므로 HTTP간의 연결 개체 1개를 만듬.
            HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            // 여러 가지 정보를 읽어 들이는데 버퍼를 통해 읽어 들이기 위해 BufferReader 를 사용
            BufferedReader br;
            System.out.print("responseCode=" + responseCode);
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();			// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            con.disconnect();
            result = res.toString();
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }
}
