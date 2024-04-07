package dev.boot.service;

import dev.boot.domain.*;
import dev.boot.dto.ItemDTO;
import dev.boot.dto.JewelryDTO;
import dev.boot.dto.TechDTO;
import dev.boot.dto.VehicleDTO;
import dev.boot.repository.*;
import dev.omedia.boot.domain.*;
import dev.omedia.boot.dto.*;
import dev.omedia.boot.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final VehicleRepository vehicleRepository;
    private final JewelryRepository jewelryRepository;
    private final TechRepository techRepository;
    private final BranchRepository branchRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public ItemDTO save(ItemDTO itemDTO) {
        Item item = itemRepository.save(modelMapper.map(itemDTO, Item.class));
        return modelMapper.map(item, ItemDTO.class);
    }



    //მანქანის დალომბარდება
    public VehicleDTO pawnVehicle(VehicleDTO vehicleDTO, long branchId){
        Vehicle vehicle = vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class));
        if (branchRepository.existsById(branchId)){
            Branch branch = branchRepository.findById(branchId).get();

            branch.getItems().add(vehicle);
            vehicle.setBranch(branch);
            branchRepository.save(branch);
            vehicleRepository.save(vehicle);
            storeAccount(vehicle);

            return modelMapper.map(vehicle, VehicleDTO.class);
        }
        throw new RuntimeException();
    }


    public JewelryDTO pawnJewelry(JewelryDTO jewelryDTO, long branchId){
        Jewelry jewelry = jewelryRepository.save(modelMapper.map(jewelryDTO, Jewelry.class));
        if (branchRepository.existsById(branchId)){
            Branch branch = branchRepository.findById(branchId).get();

            branch.getItems().add(jewelry);
            jewelry.setBranch(branch);
            branchRepository.save(branch);
            jewelryRepository.save(jewelry);
            storeAccount(jewelry);

            return modelMapper.map(jewelry, JewelryDTO.class);
        }
        throw new RuntimeException();
    }

    public TechDTO pawnTech(TechDTO techDTO, long branchId){
        Tech tech = techRepository.save(modelMapper.map(techDTO, Tech.class));
        if (branchRepository.existsById(branchId)){
            Branch branch = branchRepository.findById(branchId).get();

            branch.getItems().add(tech);
            tech.setBranch(branch);
            branchRepository.save(branch);
            techRepository.save(tech);
            storeAccount(tech);
            return modelMapper.map(tech, TechDTO.class);
        }
        throw new RuntimeException();
    }


    private void storeAccount(Item item){
        Account account = new Account();
        account.setItemId(item.getId());
        account.setAmountPaidByUser(0);
        account.setTotalToBePaid(item.getAmountReceived());
        accountRepository.save(account);
    }

}
