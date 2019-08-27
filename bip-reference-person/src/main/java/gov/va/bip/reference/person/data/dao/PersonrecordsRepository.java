package gov.va.bip.reference.person.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.bip.reference.person.data.orm.entity.Personrecord;

/**
 * Repository Class to handle database access operation to the PERSONRECORD table associated with the Personrecord POJO
 *
 */
@Repository
public interface PersonrecordsRepository extends JpaRepository<Personrecord, Long> {

	/**
	 * Retrieve a Personrecord based on the pid.
	 *
	 * @param pid the pid
	 * @return Personrecord
	 */
	Personrecord findByPid(Long pid);

}
