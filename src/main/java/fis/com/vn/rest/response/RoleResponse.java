package fis.com.vn.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private String title;
    private String key;
    private List<RoleResponse> children = new ArrayList<>();

    public void addRoleResponse(RoleResponse roleResponse) {
        this.children.add(roleResponse);
    }
}
