package org.hrd.spring.entities.responses;

/**
 * 
 * @author Tola
 *	Created Date: 2017/07/03
 *
 */
public class ResponseRecord<User> extends Response{
	/*
	 * Response record class using with single object
	 */
	private User user;

	
	
	public ResponseRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseRecord(String message, boolean status, User user) {
		super(message, status);
		this.user= user;
	}

	public User getUser() {
		return user;
	}

	public void setData(User user) {
		this.user= user;
	}

}
