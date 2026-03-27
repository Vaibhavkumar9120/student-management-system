package in.main.controller;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.main.entity.Student;
import in.main.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

	 private final StudentService service;

	    public StudentController(StudentService service) {
	        this.service = service;
	    }

	    @PostMapping
	    public Student create(@Valid @RequestBody Student student) {
	        return service.createStudent(student);
	    }

	    @GetMapping("/list")
public List<Student> getAll(@RequestParam( required =false, defaultValue= "1") int pageNo,
		@RequestParam(required = false, defaultValue= "3") int pageSize,//default value is for when user doesn't give any data
        @RequestParam(required =false, defaultValue= "Id") String sortBy,
        @RequestParam(required =false, defaultValue= "ASC") String sortDir,
        @RequestParam(required=false)String search )
{ 
	    Sort sort=null;
	    if(sortDir.equalsIgnoreCase("ASC")) {
	    	sort=Sort.by(sortBy).ascending();
	    }
	    	else {
	    		sort=Sort.by(sortBy).descending();
	    	}
	    
    return service.getAllStudents(PageRequest.of(pageNo-1, pageSize , sort ),search);
}

	    @GetMapping("/{id}")
	    public Student getById(@PathVariable Long id) {
	        return service.getStudentById(id);
	    }

	    @PutMapping("/{id}")
	    public Student update(@PathVariable Long id,
	                          @RequestBody Student student) {
	        return service.updateStudent(id, student);
	    }

	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        service.deleteStudent(id);
	        return "Student deleted successfully";
	    }
	   
}
