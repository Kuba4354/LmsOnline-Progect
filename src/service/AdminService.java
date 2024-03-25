package service;

import role.Admin;

public interface AdminService {
    Admin login(Admin admin);

    Admin changePassword(Admin admin);

}
