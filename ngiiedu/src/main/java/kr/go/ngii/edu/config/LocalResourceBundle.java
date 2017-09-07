package kr.go.ngii.edu.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalResourceBundle {

	private static ResourceBundle resource = ResourceBundle.getBundle("config/system", Locale.getDefault());

	public static final String JDBC_DBTYPE 	= resource.getString("JDBC_DBTYPE");
	public static final String JDBC_HOST 	= resource.getString("JDBC_HOST");
	public static final String JDBC_PORT 	= resource.getString("JDBC_PORT");
	public static final String JDBC_DATABASE = resource.getString("JDBC_DATABASE");
	public static final String JDBC_USERNAME = resource.getString("JDBC_USERNAME");
	public static final String JDBC_PASSWORD = resource.getString("JDBC_PASSWORD");

}
