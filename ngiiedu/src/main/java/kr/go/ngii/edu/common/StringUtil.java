package kr.go.ngii.edu.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {

	public static Map<String, ?> stringToMap(String jsonString) {
		try {
			if (jsonString == null) {
				return null;
			}
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonString, new TypeReference<HashMap<String, ?>>() {
			});
		} catch (IOException e) {
			return null;
		}
	}

	public static String mapToString(Map<String, ?> map) {
		try {
			if (map == null) {
				return null;
			}
			return new ObjectMapper().writeValueAsString(map);
		} catch (Exception e) {
			return null;
		}
	}

	public static Map convertObjectToMap(Object obj) {
		Map map = new HashMap();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				map.put(fields[i].getName(), fields[i].get(obj));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static Object convertMapToObject(Map<String, Object> map, Object obj) {
		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator itr = map.keySet().iterator();

		while (itr.hasNext()) {
			keyAttribute = (String) itr.next();
			methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
			Method[] methods = obj.getClass().getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methodString.equals(methods[i].getName())) {
					try {
						methods[i].invoke(obj, map.get(keyAttribute));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return obj;
	}

	public static boolean isNullOrEmpty(Object value) {
		try {
			if (value == null) {
				return true;
			}

			String str = value.toString();
			return str == null || "".equals(str);
		} catch (Exception e) {
			return true;
		}
	}

	public static String encodeURIComponent(String s) {
		String result = null;

		try {
			result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		}

		// This exception should never occur.
		catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}
}
