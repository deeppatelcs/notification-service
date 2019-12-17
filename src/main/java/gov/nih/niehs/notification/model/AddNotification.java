package gov.nih.niehs.notification.model;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * AddNotification
 */
@Validated
public class AddNotification {
	@JsonProperty("recipient_id")
	private String recipientId = null;

	@JsonProperty("subject")
	private String subject = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("severity_level")
	private Integer severityLevel = null;

	@JsonProperty("notification_type")
	private String notificationType = null;

	@JsonProperty("data_location_url")
	private String dataLocationUrl = null;

	public AddNotification recipientId(String recipientId) {
		this.recipientId = recipientId;
		return this;
	}

	/**
	 * notification recipient's userID
	 * 
	 * @return recipientId
	 **/
	@ApiModelProperty(required = true, value = "notification recipient's userId")
	@NotNull

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public AddNotification subject(String subject) {
		this.subject = subject;
		return this;
	}

	/**
	 * notification subject
	 * 
	 * @return subject
	 **/
	@ApiModelProperty(required = true, value = "notification subject")
	@NotNull

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public AddNotification message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * notification message content
	 * 
	 * @return message
	 **/
	@ApiModelProperty(required = true, value = "notification message content")
	@NotNull

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AddNotification severityLevel(Integer severityLevel) {
		this.severityLevel = severityLevel;
		return this;
	}

	/**
	 * severity level of notification an integer value between 1 and 5 minimum: 1
	 * maximum: 5
	 * 
	 * @return severityLevel
	 **/
	@ApiModelProperty(required = true, value = "severity level of notification an integer value between 1 and 5")
	@NotNull

	@Min(1)
	@Max(5)
	public Integer getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(Integer severityLevel) {
		this.severityLevel = severityLevel;
	}

	public AddNotification notificationType(String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * type of notification options are workflow, permission, system
	 * 
	 * @return notificationType
	 **/
	@ApiModelProperty(required = true, value = "type of notification options are workflow, permission, system")
	@NotNull

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public AddNotification dataLocationUrl(String dataLocationUrl) {
		this.dataLocationUrl = dataLocationUrl;
		return this;
	}

	/**
	 * notification associated logical location of data
	 * 
	 * @return dataLocationUrl
	 **/
	@ApiModelProperty(value = "notification associated logical location of data")

	public String getDataLocationUrl() {
		return dataLocationUrl;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AddNotification addNotification = (AddNotification) o;
		return Objects.equals(this.recipientId, addNotification.recipientId)
				&& Objects.equals(this.subject, addNotification.subject)
				&& Objects.equals(this.message, addNotification.message)
				&& Objects.equals(this.severityLevel, addNotification.severityLevel)
				&& Objects.equals(this.notificationType, addNotification.notificationType)
				&& Objects.equals(this.dataLocationUrl, addNotification.dataLocationUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(recipientId, subject, message, severityLevel, notificationType, dataLocationUrl);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AddNotification {\n");

		sb.append("    recipientId: ").append(toIndentedString(recipientId)).append("\n");
		sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    severityLevel: ").append(toIndentedString(severityLevel)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    dataLocationUrl: ").append(toIndentedString(dataLocationUrl)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
