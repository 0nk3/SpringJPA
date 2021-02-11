package com.springintro.SpringIntro.student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll(); // returns a list
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email already assigned to a student");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void delete(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(id + "does not exists");
        }
        studentRepository.deleteById(id);
    }
    // updating student information : name and email will validation
    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("" +
                "Student with " + id + " does not exist . . ."));
        if(!name.isEmpty() && !name.isBlank() && Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(!email.isEmpty() && !email.isBlank() && Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email already taken");
            }
            student.setEmail(email);
        }
    }
}
