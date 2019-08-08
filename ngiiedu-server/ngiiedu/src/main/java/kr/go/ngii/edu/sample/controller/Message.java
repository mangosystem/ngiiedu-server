package kr.go.ngii.edu.sample.controller;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userinfo")
public class Message {

	String name;
	String text;

	public Message(){
		
	}
	
	public Message(String name, String text) {
		this.name = name;
		this.text = text;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
