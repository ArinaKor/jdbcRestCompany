package com.company.controllers;

import com.company.daoImpl.ProjectsDaoImpl;
import com.company.models.Department;
import com.company.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectsDaoImpl projectDao;
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Project> findById(@PathVariable("id") int id)
    {
        Project project = this.projectDao.findById(id);
        if(project==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // System.out.println(project.toString());
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Project>> findAll(){
        List<Project> projects = this.projectDao.findAll();
        if(projects.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }

    }
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Project> delete(@PathVariable("id") int id){
        Project project = this.projectDao.findById(id);
        this.projectDao.delete(id);
        if(project == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(project, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Project> save(@RequestBody Project project){
        this.projectDao.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Project> update(@PathVariable("id") int id, @RequestBody Project project){
        this.projectDao.update(id,project);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
