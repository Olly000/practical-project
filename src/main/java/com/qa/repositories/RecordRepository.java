package com.qa.repositories;

import org.springframework.stereotype.Repository;
import com.qa.persistence.Record;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

	public Record findByTitle(String title);
	
	public List<Record> findAllByLabel(String label);


}
