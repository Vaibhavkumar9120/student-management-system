package in.main.dto;

import in.main.entity.Student;

public class StudentMapper {

	public static Student toEntity(StudentDTO dto) {
		Student student = new Student();
		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setCourse(dto.getCourse());
		
		return student;
	}
	public static StudentDTO toDTO(Student student) {
		StudentDTO dto = new StudentDTO();
		dto.setName(student.getName());
		dto.setEmail(student.getEmail());
		dto.setCourse(student.getCourse());
		
		return dto;
}
}