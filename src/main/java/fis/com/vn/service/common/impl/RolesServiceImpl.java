package fis.com.vn.service.common.impl;

import fis.com.vn.model.entity.Roles;
import fis.com.vn.repository.RolesRepository;
import fis.com.vn.rest.response.RoleResponse;
import fis.com.vn.service.common.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public RoleResponse getRolesByRoleType(String roleType) {
        List<Roles> rolesList = rolesRepository.getRolesByRoleType(roleType);

        HashMap<Long, RoleResponse> map = new HashMap<Long, RoleResponse>();
        map.put(0L, RoleResponse.builder().key("Root").title("Root").children(new ArrayList<RoleResponse>()).build());
        for( Roles roles: rolesList )
        {
            map.put(roles.getRoleId(),RoleResponse.builder().key(roles.getRoleCode())
                            .title(roles.getRoleName())
                    .children(new ArrayList<RoleResponse>()).build());
        }
        for( Roles roles : rolesList )
        {
            if(roles.getRoleParentId().equals(roles.getRoleId()))
            {
                map.get(0L).addRoleResponse(map.get(roles.getRoleId())); // add to the root
            }
            else
            {
                RoleResponse node = map.get(roles.getRoleParentId());
                RoleResponse childNode = map.get(roles.getRoleId());
                node.addRoleResponse(childNode); // add to the relevant parent
            }
        }

        return map.get(0L);
    }


}
