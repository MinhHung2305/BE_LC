package fis.com.vn.service.common.impl;

import fis.com.vn.model.entity.Channel;
import fis.com.vn.repository.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChannelServiceImplTest {

    @InjectMocks
    ChannelServiceImpl channelService;

    @Mock
    ChannelRepository channelRepository;

    @Test
    void deleteChannel() {
        Long channelId = 1L;
        channelService.deleteChannel(channelId);
        verify(channelRepository, times(1)).deleteById(channelId);

    }

    @Test
    void createChannel() {
        Channel channel = Channel.builder()
                .channelCode("DIN")
                .channelClass("Director")
                .channelName("Directory Module")
                .channelType("Internet")
                .description("Description")
                .build();
        channelService.createChannel(channel);
        verify(channelRepository, times(1)).save(channel);
    }
}