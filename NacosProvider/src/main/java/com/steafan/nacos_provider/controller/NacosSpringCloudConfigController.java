package com.steafan.nacos_provider.controller;

//import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steafan(zyf)
 * @create 2022/10/19 9:28
 * @desc
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class NacosSpringCloudConfigController {

    @NacosInjected
    private ConfigService configService;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }

    @RequestMapping(value = "/configPublishSpringCloud", method = RequestMethod.POST)
    public ServerResponse<Boolean> configPublishSpringCloud(String dataId, String group, String content) throws NacosException {
        boolean configPublishSpringCloudStatus = configService.publishConfig(dataId, group, content);
        if (configPublishSpringCloudStatus) {
            return ServerResponse.createBySuccess(configPublishSpringCloudStatus);
        }
        return ServerResponse.createByError(configPublishSpringCloudStatus + "");
    }

}
