package core.services;

import core.entities.GhostPassword;
import core.entities.GhostPasswordDAO;
import core.entities.User;
import core.entities.UserDAO;
import core.security.GenerateAPIKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.sql.Timestamp;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final GhostPasswordDAO ghostPasswordDAO;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder, GhostPasswordDAO ghostPasswordDAO) {
        Assert.notNull(userDAO, "UserDAO must not be null!");
        Assert.notNull(passwordEncoder, "PasswordEncoder must not be null!");
        Assert.notNull(ghostPasswordDAO, "GhostPasswordDAO must not be null!");
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.ghostPasswordDAO = ghostPasswordDAO;
    }

    public long createUser(long accoutId, String email, String password, String firstName, String lastName) {
        User user = new User();
        user.setAccountId(accoutId);
        user.setUserName(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setEnabled(1);
        user.setEmailVerified(false);
        user.setFirst(firstName);
        user.setLast(lastName);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userDAO.save(user);
        return user.getUserId();
    }

    public long createGhost(long accountId) {
        String ghostName = "ghost@"+accountId;
        String ghostPwd = GenerateAPIKey.generateGenericKey();

        User user = new User();
        user.setAccountId(accountId);
        user.setUserName(ghostName);
        user.setPassword(passwordEncoder.encode(ghostPwd));
        user.setEmail(ghostName);
        user.setEnabled(1);
        user.setEmailVerified(true);
        user.setFirst("Ghost");
        user.setLast("Spookerson");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userDAO.save(user);
        GhostPassword ghostPassword = new GhostPassword();
        ghostPassword.setAccountId(accountId);
        ghostPassword.setPassword(ghostPwd);
        ghostPassword.setUpdated(new Timestamp(System.currentTimeMillis()));
        ghostPasswordDAO.save(ghostPassword);
        return user.getUserId();
    }

    public boolean emailAddressExists(String email) {
        User user = userDAO.findByEmail(email);
        if(user == null || user.getUserId() < 1) {
            return false;
        }
        return true;
    }

    @CacheEvict(value = { "isDeveloper","getAccountIdForUserName","getFirstLastForUserName" }, allEntries = true)
    public void evictUserCaches() {
    }

    @Scheduled(cron = "0 1 1 * * ?")
    public void runEvict() {
        evictUserCaches();
    }
}
