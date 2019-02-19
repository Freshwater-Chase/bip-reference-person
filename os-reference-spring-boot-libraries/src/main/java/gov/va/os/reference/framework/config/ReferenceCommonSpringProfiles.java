package gov.va.os.reference.framework.config;

/**
 * Constants to store the profiles commonly used in ASCENT applications.
 *
 */
public final class ReferenceCommonSpringProfiles {

	/**
	 * Spring default profile
	 */
	public static final String PROFILE_DEFAULT = "default";

	/**
	 * Spring profile for local dev environment
	 */
	public static final String PROFILE_ENV_LOCAL_INT = "local-int";

	/**
	 * Spring profile for local dev environment
	 */
	public static final String PROFILE_ENV_DOCKER_DEMO = "docker-demo";

	/**
	 * Spring profile for AWS CI environment
	 */
	public static final String PROFILE_ENV_AWS_CI = "aws-ci";

	/**
	 * Spring profile for AWS DEV environment
	 */
	public static final String PROFILE_ENV_AWS_DEV = "aws-dev";

	/**
	 * Spring profile for AWS STAGE environment
	 */
	public static final String PROFILE_ENV_AWS_STAGE = "aws-stage";

	/**
	 * Spring profile for AWS PROD environment
	 */
	public static final String PROFILE_ENV_AWS_PROD = "aws-prod";

	/**
	 * Spring profile for remote client real implementations
	 */
	public static final String PROFILE_REMOTE_CLIENT_IMPLS = "remote_client_impls";

	/**
	 * Spring profile for remote client simulator implementations
	 */
	public static final String PROFILE_REMOTE_CLIENT_SIMULATORS = "remote_client_sims";

	/**
	 * Spring profile for unit test specific impls
	 */
	public static final String PROFILE_UNIT_TEST = "unit_test_sims";

	/**
	 * Spring profile for remote audit simulator implementations
	 */
	public static final String PROFILE_REMOTE_AUDIT_SIMULATORS = "remote_audit_client_sims";

	/**
	 * Spring profile for remote audit impl implementations
	 */
	public static final String PROFILE_REMOTE_AUDIT_IMPLS = "remote_audit_client_impl";

	/**
	 * Spring Profile to signify that the application will run embedded redis
	 */
	public static final String PROFILE_EMBEDDED_REDIS = "embedded-redis";

	/**
	 * Spring Profile to signify that the application will run embedded aws
	 */
	public static final String PROFILE_EMBEDDED_AWS = "embedded-aws";

	/**
	 * Spring Profile to signify that the configuration will not be loaded in embedded aws
	 */
	public static final String NOT_PROFILE_EMBEDDED_AWS = "!embedded-aws";

	ReferenceCommonSpringProfiles() {
		throw new IllegalStateException("Utility class");
	}
}
