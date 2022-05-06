package com.example.gestionempleados.controller;

import com.example.gestionempleados.exceptions.ResourceNotFoundException;
import com.example.gestionempleados.model.Employee;
import com.example.gestionempleados.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")

public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }


    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el id " + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee e = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el id " + id));
        e.setNombre(employee.getNombre());
        e.setApellido(employee.getApellido());
        e.setEmail(employee.getEmail());
        return ResponseEntity.ok(employeeRepository.save(e));
    }
}
