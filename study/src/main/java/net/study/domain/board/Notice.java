package net.study.domain.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Notice {

	private int bno;
	private String boardType;
	private String title;
	private Date regDate;
	private Date modDate;
	private String writer;
	private String modifier;
	private String content;
	private int atchflId;
	private String atchflNm;
	private MultipartFile atchFile;
	
	public Notice() {
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAtchflId() {
		return atchflId;
	}
	public void setAtchflId(int atchflId) {
		this.atchflId = atchflId;
	}
	public String getAtchflNm() {
		return atchflNm;
	}
	public void setAtchflNm(String atchflNm) {
		this.atchflNm = atchflNm;
	}
	
	@Override
	public String toString() {
		return "Notice [bno=" + bno + ", boardType=" + boardType + ", title=" + title + ", regDate=" + regDate + ", modDate=" + modDate + ", writer="
				+ writer + ", modifier=" + modifier + ", content=" + content + ", atchflId=" + atchflId + ", atchflNm=" + atchflNm + "]";
	}

	public MultipartFile getFile() {
		return atchFile;
	}

	public void setFile(MultipartFile file) {
		this.atchFile = file;
	}
	
	
	
}
