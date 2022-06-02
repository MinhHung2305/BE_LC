package fis.com.vn.service.common.impl;

import fis.com.vn.model.entity.Channel;
import fis.com.vn.repository.ChannelRepository;
import fis.com.vn.service.common.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    @Override
    public List<Channel> getAll() {
        return channelRepository.findAll();
    }

    @Override
    public void deleteChannel(Long channelId) {
        channelRepository.deleteById(channelId);
    }

    @Override
    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }
}
