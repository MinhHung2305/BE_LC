package fis.com.vn.service.impl;

import fis.com.vn.rest.response.UserGroupResponse;
import fis.com.vn.service.common.UserGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class UserGroupServiceImplTest {
    @Autowired
    UserGroupService userGroupService;

    @Test
    void getPermissionUserGroup() {
        String userGroupCode = "Group2619";
        UserGroupResponse userGroupResponse = userGroupService.getPermissionUserGroup(userGroupCode);
        System.out.println(userGroupResponse);
    }
}