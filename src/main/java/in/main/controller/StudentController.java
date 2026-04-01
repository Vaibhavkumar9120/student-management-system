package in.main.controller;



import java.util.List;


import org.springframework.data.domain.PageRequest;

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

import in.main.dto.StudentDTO;
import in.main.dto.StudentMapper;
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
	    public StudentDTO create(@Valid @RequestBody StudentDTO dto) {
	        return StudentMapper.toDTO( 
	        		service.createStudent(
	        				StudentMapper.toEntity(dto)
	        				)
	        		);
	    }

	    @GetMapping("/list")
public List<StudentDTO> getAll(@RequestParam( required =false, defaultValue= "1") int pageNo,
		@RequestParam(required = false, defaultValue= "3") int pageSize,//default value is for when user doesn't give any data
        @RequestParam(required =false, defaultValue= "id") String sortBy,
        @RequestParam(required =false, defaultValue= "ASC") String sortDir,
        @RequestParam(required=false)String search )
{ 
	    Sort sort=sortDir.equalsIgnoreCase("ASC") 
	    ?Sort.by(sortBy).ascending()
	    :Sort.by(sortBy).descending();
	    	
	    
    return service.getAllStudents(PageRequest.of(pageNo-1, pageSize , sort ),search)
    		.stream()
    		.map(StudentMapper::toDTO)
    		.toList();
}

	    @GetMapping("/{id}")
	    public StudentDTO getById(@PathVariable Long id) {
	        return StudentMapper.toDTO(service.getStudentById(id));
	    }

	    @PutMapping("/{id}")
	    public StudentDTO update(@PathVariable Long id,@Valid
	                          @RequestBody StudentDTO dto) {
	    	Student updated = service.updateStudent(id, StudentMapper.toEntity(dto));
	        return StudentMapper.toDTO(updated);
	    }

	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        service.deleteStudent(id);
	        return "Student deleted successfully";
	    }
	   
}
