package core.services;

import core.entities.Account;
import core.entities.AccountDAO;
import core.util.PrettyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Service
public class AccountService {
    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        Assert.notNull(accountDAO, "AccountDAO must not be null!");
        this.accountDAO = accountDAO;
    }

    public long createAccount(String accountName, Account.Type accountType) {
        Account account = new Account();
        account.setName(accountName);
        account.setType(accountType);
        account.setActive(true);
        account.setCreated(new Timestamp(System.currentTimeMillis()));
        accountDAO.save(account);
        return account.getId();
    }

    public boolean companyNameExists(String name) {
        Account account = accountDAO.findByName(name);
        if(account == null || account.getId() < 1) {
            return false;
        }
        return true;
    }
}
