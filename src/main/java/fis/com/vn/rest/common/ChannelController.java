package fis.com.vn.rest.common;

import fis.com.vn.model.entity.Channel;
import fis.com.vn.rest.response.BaseResponse;
import fis.com.vn.service.common.ChannelService;
import fis.com.vn.util.ResponseFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
@Api(tags = "Data Config controller")
public class ChannelController {
    @Autowired
    ChannelService channelService;

    /**
     * get all Channel
     * @return list channel
     */
    @GetMapping("/channel/getAll")
    public ResponseEntity<BaseResponse<List<Channel>>> getAllChannel() {
        List<Channel> channelList = channelService.getAll();
        return ResponseFactory.success(HttpStatus.OK, channelList);
    }

}
