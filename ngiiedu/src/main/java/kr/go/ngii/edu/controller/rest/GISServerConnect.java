package kr.go.ngii.edu.controller.rest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.ngii.edu.common.message.ErrorMessage;


public class GISServerConnect {

	public static void requestGET(URL url, HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			if(responseCode==200) {
				// 정상 호출
				HttpURLConnection http = (HttpURLConnection) url.openConnection();

				Enumeration<String> headerNames = request.getHeaderNames();
				while (headerNames.hasMoreElements()) {
					String key = (String) headerNames.nextElement();
					if (!key.equalsIgnoreCase("Host")) {
						http.setRequestProperty(key, request.getHeader(key));
					}
				}

				http.setDoInput(true);
				http.setDoOutput(false);
				byte[] buffer = new byte[8192];
				int read = -1;

				InputStream is = http.getInputStream();

				response.setStatus(http.getResponseCode());
				Map<String, List<String>> headerKeys = http.getHeaderFields();
				Set<String>	keySet = headerKeys.keySet();
				Iterator<String> iter = keySet.iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					String value = http.getHeaderField(key);
					if (key != null && value != null) {
						response.setHeader(key, value);
					}
				}
				ServletOutputStream sos = response.getOutputStream();
				response.resetBuffer();
				while ((read = is.read(buffer)) != -1) {
					sos.write(buffer, 0, read);
				}
				response.flushBuffer();
				sos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}