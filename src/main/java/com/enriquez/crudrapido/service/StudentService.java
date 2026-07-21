package com.enriquez.crudrapido.service;

import com.enriquez.crudrapido.dto.StudentDTO;
import com.enriquez.crudrapido.entity.Student;
import com.enriquez.crudrapido.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService{
    
    @Autowired
    StudentRepository studentRepository;
    //lista todos
    public Page<StudentDTO> getStudents(Pageable pageable){
        return studentRepository.findAll(pageable).map(this::convertToDTO);
    }
    //ista por id
    public Optional<StudentDTO> getStudent(Long id){
        return studentRepository.findById(id).map(this::convertToDTO);
    }
    //Guardar o actualizar
    public StudentDTO saveOrUpdate(StudentDTO dto){
        Student student = Student.builder()
        .studentId(dto.getStudentId())
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .email(dto.getEmail())
        .build();

        Student saved = studentRepository.save(student);
        return convertToDTO(saved);
    }
    //Eliminar
    public boolean delete(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Para mapear de Entity a DTO
    private StudentDTO convertToDTO(Student student) {
        return StudentDTO.builder()
                .studentId(student.getStudentId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .build();
    }
}