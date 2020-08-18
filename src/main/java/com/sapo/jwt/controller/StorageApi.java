package com.sapo.jwt.controller;

import com.sapo.jwt.model.entity.Storage;
import com.sapo.jwt.model.request.StorageRequest;
import com.sapo.jwt.sevice.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/storage")
public class StorageApi {

    private StorageService storageService;

    @Autowired
    public StorageApi(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping()
    public List<Storage> findAll(@RequestParam String name, @RequestParam int page){
        return storageService.findAll(name);
    }

    @GetMapping("/{id}")
    public Storage findOne(@PathVariable long id){
        return storageService.findOne(id);
    }

    @PostMapping()
    public Storage create(@Valid @RequestBody StorageRequest storageRequest){
        return storageService.create(storageRequest);
    }

    @PutMapping("/{id}")
    public Storage update(@RequestBody StorageRequest storageRequest,@PathVariable long id){
        return storageService.update(storageRequest,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        storageService.delete(id);
    }
}
