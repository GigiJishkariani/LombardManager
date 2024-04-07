package dev.boot.service;

import dev.boot.domain.*;
import dev.boot.dto.*;
import dev.omedia.boot.domain.*;
import dev.omedia.boot.dto.*;
import dev.boot.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    @CachePut(value = "branches", key = "#result.id")
    public BranchDTO save(BranchDTO branchDTO) {
        System.err.println("daiqesha");
        Branch branch = branchRepository.save(modelMapper.map(branchDTO, Branch.class));
        return modelMapper.map(branch, BranchDTO.class);
    }

    @Cacheable(value = "branches", key = "#root.methodName")
    public List<BranchDTO> findAll() {
        log.info("finding all branches");
        List<Branch> branches = (List<Branch>) branchRepository.findAll();
        return branches.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BranchDTO convertToDto(Branch branch) {
        BranchDTO branchDTO = modelMapper.map(branch, BranchDTO.class);
        branchDTO.setItems(convertItemsToDto(branch.getItems()));
        return branchDTO;
    }

    private List<ItemDTO> convertItemsToDto(List<Item> items) {
        return items.stream()
                .map(this::convertItemToDto)
                .collect(Collectors.toList());
    }

    private ItemDTO convertItemToDto(Item item) {
        if (item instanceof Vehicle) {
            return modelMapper.map((Vehicle) item, VehicleDTO.class);
        } else if (item instanceof Tech) {
            return modelMapper.map((Tech) item, TechDTO.class);
        } else if (item instanceof Jewelry) {
            return modelMapper.map((Jewelry) item, JewelryDTO.class);
        }
        return null;
    }




    @Cacheable(value = "branches", key = "#id")
    public BranchDTO findById(long id) {
        return branchRepository.findById(id)
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .orElseThrow(RuntimeException::new);
    }


    public BranchDTO update(BranchDTO branchDTO, long id){
        if (branchRepository.existsById(id)){
            branchDTO.setId(id);
            Branch updated = branchRepository.save(modelMapper.map(branchDTO, Branch.class));
            return modelMapper.map(updated, BranchDTO.class);
        }
        else {
            throw new RuntimeException();
        }
    }

    public void deleteById(long id) {
        branchRepository.deleteById(id);
    }

}
