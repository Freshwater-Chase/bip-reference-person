package gov.va.ocp.reference.person.api.model.v1;

import gov.va.ocp.framework.transfer.ProviderTransferObjectMarker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A class to represent the data contained in the response
 * from the Person Service.
 *
 */
@ApiModel(description = "Model for the response from the Person Service")
public class PersonInfoResponse extends BaseProviderResponse implements ProviderTransferObjectMarker {

	/** A PersonInfoDomain instance. */
	@ApiModelProperty(value = "The object representing the person information")
	private PersonInfo personInfo;

	/**
	 * Gets the person info.
	 *
	 * @return A PersonInfoDomain instance
	 */
	public final PersonInfo getPersonInfo() {
		return personInfo;
	}

	/**
	 * Sets the person info.
	 *
	 * @param personInfo A PersonInfoDomain instance
	 */
	public final void setPersonInfo(final PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
}
