package com.company.controllers;

import com.company.daoImpl.DepartmentDaoImpl;
import com.company.models.Department;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentDaoImpl departmentDao;

   // @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> findById(@PathVariable("id") int id)
    {
        Department department = this.departmentDao.findById(id);
        if(department==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       // System.out.println(department.toString());
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Department>> findAll(){
       List<Department> departments = this.departmentDao.findAll();
       if(departments.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       else{
           return new ResponseEntity<>(departments, HttpStatus.OK);
       }

    }
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> delete(@PathVariable("id") int id){
       Department department = this.departmentDao.findById(id);
       this.departmentDao.delete(id);
       if(department == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       else{
           return new ResponseEntity<>(department, HttpStatus.OK);
       }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> save(@RequestBody Department department){
       this.departmentDao.save(department);
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> update(@PathVariable("id") int id,@RequestParam("name") String name/*, @RequestBody Department department*/){
        this.departmentDao.update(id,name);

        //this.departmentDao.update(id,department);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
