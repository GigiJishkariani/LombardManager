package dev.boot.controller;


import dev.boot.dto.JewelryDTO;
import dev.boot.dto.TechDTO;
import dev.boot.dto.VehicleDTO;
import dev.boot.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

//    @PostMapping
//    public ResponseEntity<ItemDTO> save(@RequestBody ItemDTO itemDTO){
//        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemDTO));
//    }

    @PostMapping("/vehicle/{branchId}")
    @Operation(summary = "Pawns a vehicle to the given branch")
    public ResponseEntity<VehicleDTO> pawnVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable long branchId){
        return ResponseEntity.ok(itemService.pawnVehicle(vehicleDTO,branchId));
    }

    @PostMapping("/jewelry/{branchId}")
    @Operation(summary = "Pawns jewelry to the given branch")
    public ResponseEntity<JewelryDTO> pawnJewelry(@RequestBody JewelryDTO jewelryDTO, @PathVariable long branchId){
        return ResponseEntity.ok(itemService.pawnJewelry(jewelryDTO,branchId));
    }

    @PostMapping("/tech/{branchId}")
    @Operation(summary = "Pawns tech to the given branch")
    public ResponseEntity<TechDTO> pawnTech(@RequestBody TechDTO techDTO, @PathVariable long branchId){
        return ResponseEntity.ok(itemService.pawnTech(techDTO,branchId));
    }
}
