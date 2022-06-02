package fis.com.vn.service.notification;

import fis.com.vn.model.entity.Template;

public interface TemplateService {
    Template getTemplateByCode(String templateCode);
}
