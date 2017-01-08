package core.services;

import core.entities.UserRole;
import core.entities.UserRolesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserRoleService {
    private final UserRolesDAO userRolesDAO;

    @Autowired
    public UserRoleService(UserRolesDAO userRolesDAO) {
        Assert.notNull(userRolesDAO, "UserRolesDAO must not be null!");
        this.userRolesDAO = userRolesDAO;
    }

    public void createRole(long userId, String role) {
        UserRole userRole = new UserRole();
        userRole.setUserid(userId);
        userRole.setRole(role);
        userRolesDAO.save(userRole);
    }
}
