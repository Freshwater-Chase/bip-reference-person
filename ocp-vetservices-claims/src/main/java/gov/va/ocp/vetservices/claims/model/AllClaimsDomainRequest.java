package gov.va.ocp.vetservices.claims.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import gov.va.ocp.framework.transfer.ProviderTransferObjectMarker;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A class to represent a request for PersonInfoDomain from the Person Service.
 *
 */
@ApiModel(description = "Model for data to request PersonInfoDomain from the Person Service")
@NotNull(message = "{ocp.reference.person.info.request.NotNull}")
public class AllClaimsDomainRequest implements ProviderTransferObjectMarker {
	public static final String MODEL_NAME = AllClaimsDomainRequest.class.getSimpleName();

	/** A String representing a social security number. */
	@ApiModelProperty(value = "The Participant ID of the person for whom to retrieve data", required = true,
			example = "6666345")
	@NotNull(message = "{ocp.reference.person.info.request.pid.NotNull}")
	@Min(value = 1, message = "{ocp.reference.person.info.request.pid.Min}")
	private String pid;

	/**
	 * Gets the pid.
	 *
	 * @return the pid
	 */
	public String getId() {
		return pid;
	}

	/**
	 * Sets the pid.
	 *
	 * @param pid the pid
	 */
	public void setId(String pid) {
		this.pid = pid;
	}
}
