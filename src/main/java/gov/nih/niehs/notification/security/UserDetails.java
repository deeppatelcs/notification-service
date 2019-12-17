package gov.nih.niehs.notification.security;

import org.springframework.stereotype.Service;

import io.swagger.annotations.ApiModelProperty;

@Service
public class UserDetails {
	
	private String sessionUserId = null;
	
	public UserDetails sessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
		return this;
	}

	/**
	 * Get senderID
	 * 
	 * @return senderID
	 **/
	@ApiModelProperty(value = "")

	public String getSessionUserId() {
		return sessionUserId;
	}

	public void setSessionUserId(String username) {
		this.sessionUserId = username;
	}
}
