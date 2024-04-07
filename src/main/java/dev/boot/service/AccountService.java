package dev.boot.service;

import dev.boot.domain.Account;
import dev.boot.domain.Item;
import dev.boot.repository.AccountRepository;
import dev.boot.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final ItemRepository itemRepository;

    public Account save(Account entity) {
        return repository.save(entity);
    }

    public Optional<Account> findById(Long aLong) {
        return repository.findById(aLong);
    }



    //nivtis gatana
    public void update(long accountId,double money){
        Optional<Account> account= findById(accountId);
        account.orElseThrow(NoSuchElementException::new);
        Account account1 =account.get();
        account1.setAmountPaidByUser(account1.getAmountPaidByUser()+money);
        if (account1.getAmountPaidByUser() >= account1.getTotalToBePaid()){
            Optional<Item> item = itemRepository.findById(account1.getItemId());
            item.orElseThrow(NoSuchElementException::new);
            Item item1 = item.get();
            item1.setUserTookOut(true);
            item1.setItemTakingDate(LocalDate.now());
        }
        repository.save(account1);
    }
    public Iterable<Account> findAll() {
        return repository.findAll();
    }
}