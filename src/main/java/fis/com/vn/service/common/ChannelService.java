package fis.com.vn.service.common;

import fis.com.vn.model.entity.Channel;

import java.util.List;

public interface ChannelService {
    List<Channel> getAll();
    void deleteChannel(Long channelId);
    Channel createChannel(Channel channel);
}
