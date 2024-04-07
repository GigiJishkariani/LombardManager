package dev.boot.service;


import dev.boot.domain.Account;
import dev.boot.domain.Tech;
import dev.omedia.boot.domain.*;
import dev.boot.dto.BranchDTO;
import dev.boot.dto.TechDTO;
import dev.boot.repository.BranchRepository;
import dev.boot.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
class ScheduledPaymentCheckServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    @Autowired
    private ItemRepository itemRepository;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    @Autowired
    private ScheduledPaymentCheckService scheduledPaymentCheckService;

    @Test
    public void testCheckAmountPaidByUser_2Months3000total100Paid_SolutionItemTakenTrue() {
        ItemService itemService = mock(ItemService.class);
        ItemRepository itemRepository = mock(ItemRepository.class);
        BranchService branchService = mock(BranchService.class);


        BranchDTO branchDTO = mock(BranchDTO.class);
        when(branchService.save(any(BranchDTO.class))).thenReturn(branchDTO);
        when(branchDTO.getId()).thenReturn(1L);

        long id = 1L;
        double amountRecieved = 3000.0;
        double monthlyPayment = 150.0;
        TechDTO techDTO = mock(TechDTO.class);
        techDTO.setAmountReceived(3000);
        techDTO.setMonthlyPayment(150);
        techDTO.setDateReceived(LocalDate.now().minusMonths(2));
        when(techDTO.getAmountReceived()).thenReturn(amountRecieved);
        when(techDTO.getMonthlyPayment()).thenReturn(monthlyPayment);
        when(techDTO.getDateReceived()).thenReturn(LocalDate.now().minusMonths(2));
        when(techDTO.getId()).thenReturn(id);
        techDTO.setBranch(branchDTO);

        Tech tech = mock(Tech.class);
        tech.setAmountReceived(3000);
        tech.setMonthlyPayment(150);
        tech.setDateReceived(LocalDate.now().minusMonths(2));
        when(tech.getId()).thenReturn(id);
        when(tech.getAmountReceived()).thenReturn(3000.0);
        when(tech.getMonthlyPayment()).thenReturn(150.0);
        when(tech.getDateReceived()).thenReturn(LocalDate.now().minusMonths(2));

        when(itemService.save(any(TechDTO.class))).thenReturn(techDTO);

        Account account = mock(Account.class);
        when(account.getTotalToBePaid()).thenReturn(3000.0);
        when(account.getAmountPaidByUser()).thenReturn(100.0);
        when(account.getItemId()).thenReturn(id);
        when(account.getId()).thenReturn(id);

        when(accountService.findAll()).thenReturn(List.of(account));
//        when(itemRepository.findById(account.getItemId())).thenReturn(Optional.of(tech));

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        when(itemRepository.findById(idCaptor.capture())).thenReturn(Optional.of(tech));


//        verify(itemRepository, times(1)).findById(idCaptor.getValue());
//        when(itemRepository.save(any(Item.class))).thenReturn(tech);
//        ArgumentCaptor<Item> techCaptor = ArgumentCaptor.forClass(Item.class);
//        when(itemRepository.save(techCaptor.capture())).thenReturn(tech);

        scheduledPaymentCheckService.checkAmountPaidByUser();

//        Assertions.assertTrue(techCaptor.getValue().isTakenFromUser());
        Assertions.assertTrue(tech.isTakenFromUser());
        Assertions.assertNotNull(tech.getItemTakingDate());
    }

}
