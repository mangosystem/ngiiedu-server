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
	
	public static final String PINOGIO_API_SERVER = resource.getString("PINOGIO_API_SERVER");
	public static final String PINOGIO_API_PROJECT_ID = resource.getString("PINOGIO_API_PROJECT_ID");
	
	public static final int BBS_NOTICE_POSTS_SIZE = Integer.parseInt(resource.getString("BBS_NOTICE_POSTS_SIZE"));
	public static final int BBS_FAQ_POSTS_SIZE = Integer.parseInt(resource.getString("BBS_FAQ_POSTS_SIZE"));
	public static final int BBS_QNA_POSTS_SIZE = Integer.parseInt(resource.getString("BBS_QNA_POSTS_SIZE"));
	public static final int BBS_PDS_POSTS_SIZE = Integer.parseInt(resource.getString("BBS_PDS_POSTS_SIZE"));

	public static final int BBS_NOTICE_LIST_SIZE = Integer.parseInt(resource.getString("BBS_NOTICE_LIST_SIZE"));
	public static final int BBS_FAQ_LIST_SIZE = Integer.parseInt(resource.getString("BBS_FAQ_LIST_SIZE"));
	public static final int BBS_QNA_LIST_SIZE = Integer.parseInt(resource.getString("BBS_QNA_LIST_SIZE"));
	public static final int BBS_PDS_LIST_SIZE = Integer.parseInt(resource.getString("BBS_PDS_LIST_SIZE"));
	
	public static final String FILE_SAVE_REPOSITORY = resource.getString("FILE_SAVE_REPOSITORY");
}
