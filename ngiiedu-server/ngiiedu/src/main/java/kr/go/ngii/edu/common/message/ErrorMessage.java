package kr.go.ngii.edu.common.message;

public class ErrorMessage {

	public static String SERVER_ERROR = "오류가 발생하였습니다.";
	
	public static String FOBRIDDEN = "접근 권한이 없습니다.";
	
	public static String BAD_REUQEST = "입력값이 잘못되었습니다.";

	public static String COURSE_AUTHKEY_FAILED 	= "수업코드를 찾을 수가 없습니다. 다시 확인해 주세요.";

	public static String COURSE_JOIN_WAITING 	= "수업참여 대기중입니다.";

	public static String COURSE_JOIN_ACTIVE 		= "이미 수업에 참여하고 있습니다.";

	public static String COURSE_JOIN_BLOCK 		= "참여할 수 없는 수업입니다.";

	public static String COURSE_CREATE_FAILED	= "새로운 수업을 만들 수 없습니다.";

	public static String DUPICATE_ID 		= "이미 존재하는 아이디입니다.";
	
	public static String PASSWORD_AUTHENTICATION_FAILED = "비밀번호 가 다릅니다.";

	public static String COURSE_MEMBER_UPDATE_FAIL	= "사용자의 상태 변경이 서버에서 실패 하였습니다.";
}
