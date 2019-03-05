package com.kft.oms.controller.api;

import com.kft.oms.model.AssociationModel;
import com.kft.oms.service.AssociationService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("apiAssociationController")
@RequestMapping("/api/associations")
public class AssociationController {

    private final AssociationService associationService;

    public AssociationController(AssociationService associationService) {
        this.associationService = associationService;
    }

    @GetMapping
    public List<String> findByName(@RequestParam String name){
        return associationService.findByNameStartingWith(name, Pageable.unpaged())
                .stream().map(AssociationModel::getName).collect(Collectors.toList());
    }

    @GetMapping(value = "/name/{name}")
    public Optional<AssociationModel> getByName(@PathVariable String name){
        return associationService.findByNameAsAssociationModel(name);
    }

}
