 package in.main.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.main.entity.Student;
import in.main.exception.StudentNotFoundException;
import in.main.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repository;
	
	public StudentService(StudentRepository repository) {
		
		this.repository = repository;
		
	}
	
	 public Student createStudent(Student student) {
	        return repository.save(student);
	    }

	public List<Student> getAllStudents(Pageable pageable,String search) {
		if(search ==null) {
			return repository.findAll(pageable).getContent();
		}
		else {
			return repository.findByNameContainingIgnoreCase(search, pageable).getContent();
		}

}

	    public Student getStudentById(Long id) {
	        return repository.findById(id)
	                .orElseThrow(() -> new StudentNotFoundException(
	                        "Student not found with id " + id));
	    }

	    public Student updateStudent(Long id, Student student) {
	        Student existing = getStudentById(id);
	        existing.setName(student.getName());
	        existing.setEmail(student.getEmail());
	        existing.setCourse(student.getCourse());
	        return repository.save(existing);
	    }

	    public void deleteStudent(Long id) {
	        repository.deleteById(id);
	    }
	    
	    
	
}
