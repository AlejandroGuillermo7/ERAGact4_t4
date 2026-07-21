package com.enriquez.crudrapido.controller;

import com.enriquez.crudrapido.dto.StudentDTO;
import com.enriquez.crudrapido.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<Page<StudentDTO>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue ="10")int size){
        return ResponseEntity.ok(studentService.getStudents(PageRequest.of(page,size)));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getBId(@PathVariable("studentId")Long studentId){
        return studentService.getStudent(studentId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create (@Valid @RequestBody StudentDTO studentDTO) {
        studentDTO.setStudentId(null);
        StudentDTO created = studentService.saveOrUpdate(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long studentId, @Valid @RequestBody StudentDTO studentDTO) {
        if (studentService.getStudent(studentId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        studentDTO.setStudentId(studentId);
        StudentDTO updated = studentService.saveOrUpdate(studentDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {
        if (studentService.delete(studentId)) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.notFound().build(); 
    }

}

