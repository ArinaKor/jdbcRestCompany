package com.company.controllers;

import com.company.daoImpl.WorkerProjectDaoImpl;
import com.company.daoImpl.WorkersDaoImpl;
import com.company.models.Project;
import com.company.models.Workers;
import com.company.models.WorkersProjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker-project")
public class WorkerProjectController {
    @Autowired
    private WorkerProjectDaoImpl workersPrDao;

    @RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<WorkersProjects>> findAll(){
        List<WorkersProjects> workerss = this.workersPrDao.findAll();
        if(workerss.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(workerss, HttpStatus.OK);
        }

    }
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Project>> findProjectByWorkers(@RequestBody Workers workers){
        List<Project> workerss = this.workersPrDao.findProjectByWorker(workers);
        if(workerss.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(workerss, HttpStatus.OK);
        }

    }
    @RequestMapping(value = "/workers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Workers>> findWorkersByProject(@RequestBody Project project){
        List<Workers> workerss = this.workersPrDao.findWorkersByProject(project);
        if(workerss.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(workerss, HttpStatus.OK);
        }

    }
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WorkersProjects> save(@RequestBody WorkersProjects workersProjects){
        this.workersPrDao.save(workersProjects);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id_worker}/{id_project}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WorkersProjects> delete(@PathVariable("id_worker") int id_worker, @PathVariable("id_project") int id_project){
       this.workersPrDao.delete(id_worker, id_project);

            return new ResponseEntity<>(HttpStatus.OK);

    }
    @RequestMapping(value = "{id_worker}/{id_project}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workers> update(@PathVariable("id_worker") int id_worker,@PathVariable("id_project") int id_project, @RequestBody WorkersProjects workersProjects){
        this.workersPrDao.update(id_worker, id_project, workersProjects);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
