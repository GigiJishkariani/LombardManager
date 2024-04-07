package dev.boot.service;


import dev.boot.domain.Account;
import dev.boot.domain.Item;
import dev.boot.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduledPaymentCheckService {

    private final AccountService accountService;
    private final ItemRepository itemRepository;



    @Scheduled(cron = "0 1 0 1 * ?")
//    @Scheduled(cron = "*/10 * * * * ?")
    //nivtis chamortmeva
    public void checkAmountPaidByUser() {
        List<Account> accounts = (List<Account>) accountService.findAll();

        accounts.stream()
                .map(account -> {
                    Item item = itemRepository.findById(account.getItemId())
                            .orElseThrow(NoSuchElementException::new);

                    if (item.isTakenFromUser() || item.isUserTookOut()) {
                        return null;
                    }
                    double amountPaid = account.getAmountPaidByUser();
                    double amountToBePaid = (LocalDate.now().getMonthValue() - item.getDateReceived().getMonthValue()) * item.getMonthlyPayment();
                    if (amountPaid < amountToBePaid) {
                        item.setTakenFromUser(true);
                        item.setItemTakingDate(LocalDate.now());
                        System.err.println("shemovida if shi");
                        itemRepository.save(item);
                    }
                    return item;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


    }
}
