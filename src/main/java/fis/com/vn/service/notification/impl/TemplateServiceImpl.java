package fis.com.vn.service.notification.impl;

import fis.com.vn.model.entity.Template;
import fis.com.vn.repository.TemplateRepository;
import fis.com.vn.service.notification.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    @Override
    public Template getTemplateByCode(String templateCode) {
        Template template =  templateRepository.findByTemplateCode(templateCode).get(0);
        return template;
    }
}
