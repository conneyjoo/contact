package com.jinshun.contact.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class XmlWebRquestUtil {
	
	/**
	 * @return 返加通过URL请求的XML数据�?
	 * @param strXml
	 * @param siteUrl
	 */
	public static String webRequest(String strXml, String siteUrl) {
		String strRet = "";
		URL url;
		try {
			url = new URL(siteUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(3000);
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			osw.write(strXml);
			osw.flush();
			osw.close();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while (true) {
					String str = br.readLine();
					if (str == null)
						break;
					strRet += str;
				}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strRet;
	}

}
