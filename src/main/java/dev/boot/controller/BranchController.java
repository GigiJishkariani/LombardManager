package dev.boot.controller;

import dev.boot.dto.BranchDTO;
import dev.boot.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService service;

    @PostMapping
    @Operation(summary = "Saves new branch")
    public ResponseEntity<BranchDTO> save(@RequestBody BranchDTO branchDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(branchDTO));
    }

    @GetMapping
    @Operation(summary = "Finds all branches")
    public ResponseEntity<List<BranchDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds branch by id")
    public ResponseEntity<BranchDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(service.findById(id));
    }


    @PutMapping("/{branch_id}")
    @Operation(summary = "Updates the branch")
    public ResponseEntity<BranchDTO> update(@RequestBody BranchDTO branchDTO, @PathVariable long branch_id){
        return ResponseEntity.ok(service.update(branchDTO, branch_id));
    }

}
