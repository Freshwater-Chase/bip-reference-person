package gov.va.ocp.reference.person.model.validators;

import java.util.List;

import org.springframework.http.HttpStatus;

import gov.va.ocp.framework.log.OcpLogger;
import gov.va.ocp.framework.log.OcpLoggerFactory;
import gov.va.ocp.framework.messages.MessageKeys;
import gov.va.ocp.framework.messages.MessageSeverity;
import gov.va.ocp.framework.messages.ServiceMessage;
import gov.va.ocp.framework.validation.AbstractStandardValidator;
import gov.va.ocp.reference.person.messages.PersonMessageKeys;
import gov.va.ocp.reference.person.model.PersonByPidDomainRequest;

/**
 * Validates the PID input on the {@link PersonByPidDomainRequest}.
 *
 * @see AbstractStandardValidator
 * @author aburkholder
 */
public class PersonByPidDomainRequestValidator extends AbstractStandardValidator<PersonByPidDomainRequest> {
	/** Class logger */
	private static final OcpLogger LOGGER = OcpLoggerFactory.getLogger(PersonByPidDomainRequestValidator.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see gov.va.ocp.framework.validation.AbstractStandardValidator#validate(java.lang.Object, java.util.List)
	 */
	@Override
	public void validate(PersonByPidDomainRequest toValidate, List<ServiceMessage> messages) {
		// validate the request content (PID)
		Long pid = toValidate.getParticipantID();
		if (pid == null) {
			LOGGER.debug("PID is null");
			messages.add(new ServiceMessage(MessageSeverity.ERROR, HttpStatus.BAD_REQUEST,
					MessageKeys.OCP_VALIDATOR_NOT_NULL, super.getCallingMethodName() + "Participant ID"));
		} else if (pid <= 0) {
			LOGGER.debug("PID is <= 0");
			messages.add(new ServiceMessage(MessageSeverity.ERROR, HttpStatus.BAD_REQUEST,
					PersonMessageKeys.OCP_PERSON_INFO_REQUEST_PID_MIN));
		}
	}
}
