package hello;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
  
  public Account findByUsername(String username);
  long deleteByUsername(String username);
}