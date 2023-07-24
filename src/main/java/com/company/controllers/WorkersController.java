package com.company.controllers;

import com.company.daoImpl.DepartmentDaoImpl;
import com.company.daoImpl.WorkersDaoImpl;
import com.company.models.Department;
import com.company.models.Project;
import com.company.models.Workers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkersController {
    @Autowired
    private WorkersDaoImpl workersDao;
    @Autowired
    private DepartmentDaoImpl departmentDao;
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workers> findById(@PathVariable("id") int id)
    {
        Workers workers = this.workersDao.findById(id);
        if(workers==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // System.out.println(workers.toString());
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }
    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Workers>> findAll(){
        List<Workers> workerss = this.workersDao.findAllWithJoins();
        if(workerss.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(workerss, HttpStatus.OK);
        }

    }
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workers> delete(@PathVariable("id") int id){
        Workers workers = this.workersDao.findById(id);
        this.workersDao.delete(id);
        if(workers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(workers, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workers> save(@RequestBody Workers workers){
        this.workersDao.save(workers);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workers> update(@PathVariable("id") int id, @RequestBody Workers workers){
        this.workersDao.update(id,workers);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
