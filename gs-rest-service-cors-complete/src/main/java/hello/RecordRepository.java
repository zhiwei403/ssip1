package hello;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

public interface RecordRepository extends MongoRepository<Record, String> {
	
	
	@Query("{ 'filename' : ?0 }")
    Record findByfilename(String filename);
	
	
	// @Query("delete from Record u where u.filename = ?0")
long deleteByFilename(String filename);
	
	
}
	
