package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import entity.Students;

public interface StudentRepo extends MongoRepository<Students, String> {

	public Optional<Students> findByName(String name);
    public List<Students> findByAge(int age);  
    
}
