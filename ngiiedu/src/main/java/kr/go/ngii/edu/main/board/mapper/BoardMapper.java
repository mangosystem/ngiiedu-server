package kr.go.ngii.edu.main.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.board.model.BbsFAQuestion;
import kr.go.ngii.edu.main.board.model.BbsNotice;
import kr.go.ngii.edu.main.board.model.BbsQuestion;
import kr.go.ngii.edu.main.board.model.BbsReply;

@Mapper
public interface BoardMapper {

	//public Notice get(Notice notice);
	//public List<HashMap<String, String>> getNoticeList();
	public List<BbsNotice> getNoticeList();
	
	public List<BbsNotice> getNoticeList(@Param("offset") int offset, @Param("limit") int limit);

	public BbsNotice getNoticeListbyId(BbsNotice notice);

	//public Boolean insertNotice(String title, String content);

	public void insertNotice(BbsNotice notice);
	
	public void modifyNotice(BbsNotice notice);

	public void deleteNotice(@Param("noticeId") int noticeId);
	
	public List<BbsQuestion> getQnaList();
	
	public List<BbsQuestion> getQnaList(@Param("offset") int offset, @Param("limit") int limit);

	public BbsQuestion getQnaListbyId(BbsQuestion question);

	public void insertQna(BbsQuestion question);
	
	public void modifyQna(BbsQuestion question);

	public void deleteQna(@Param("qnaId") int qnaId);

	public int getQnaCnt();
	
	public List<BbsFAQuestion> getFaqList();
	
	public List<BbsFAQuestion> getFaqList(@Param("offset") int offset, @Param("limit") int limit);

	public BbsFAQuestion getFaqListbyId(BbsFAQuestion question);

	public void insertFaq(BbsFAQuestion question);
	
	public void modifyFaq(BbsFAQuestion question);

	public void deleteFaq(@Param("faqId") int faqId);

	public int getFaqCnt();

	public List<BbsReply> getReListbyIdx(BbsReply reply);

	public BbsReply getRebyIdx(BbsReply reply);

	public void insertRe(BbsReply reply);

	public void modifyRe(BbsReply reply);

	public void deleteRe(@Param("idx") int idx);

}
