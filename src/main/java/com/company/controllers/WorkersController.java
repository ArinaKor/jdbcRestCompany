package com.company.controllers;

import com.company.daoImpl.WorkersDaoImpl;
import com.company.models.Workers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkersController {
    @Autowired
    private WorkersDaoImpl workersDao;
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
        List<Workers> workerss = this.workersDao.findAll();
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
}
