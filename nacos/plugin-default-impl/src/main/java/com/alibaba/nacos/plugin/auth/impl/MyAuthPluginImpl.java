package com.alibaba.nacos.plugin.auth.impl;

import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.plugin.auth.api.IdentityContext;
import com.alibaba.nacos.plugin.auth.api.Permission;
import com.alibaba.nacos.plugin.auth.api.Resource;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.exception.AccessException;
import com.alibaba.nacos.plugin.auth.impl.constant.AuthConstants;
import com.alibaba.nacos.plugin.auth.impl.users.NacosUser;
import com.alibaba.nacos.plugin.auth.impl.users.User;
import com.alibaba.nacos.plugin.auth.spi.server.AuthPluginService;
import com.alibaba.nacos.sys.utils.ApplicationUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Steafan(zyf)
 * @create 2022/12/10 16:13
 * @desc
 */
public class MyAuthPluginImpl implements AuthPluginService {
    private static final String USER_IDENTITY_PARAM_KEY = "imooc_user";

    private static final List<String> IDENTITY_NAMES = new LinkedList<String>() {
        {
            add(AuthConstants.AUTHORIZATION_HEADER);
            add(Constants.ACCESS_TOKEN);
            add(AuthConstants.PARAM_USERNAME);
            add(AuthConstants.PARAM_PASSWORD);
        }
    };

    private NacosAuthManager nacosAuthManager;

    @Override
    public Collection<String> identityNames() {
        return IDENTITY_NAMES;
    }

    @Override
    public boolean enableAuth(ActionTypes action, String type) {
        // enable all of action and type
        return true;
    }

    @Override
    public boolean validateIdentity(IdentityContext identityContext, Resource resource) throws AccessException {
        checkNacosAuthManager();
        User user = nacosAuthManager.login(identityContext);
        identityContext.setParameter(USER_IDENTITY_PARAM_KEY, user);
        identityContext.setParameter(com.alibaba.nacos.plugin.auth.constant.Constants.Identity.IDENTITY_ID,
                user.getUserName());
        return true;
    }

    @Override
    public Boolean validateAuthority(IdentityContext identityContext, Permission permission) throws AccessException {
        NacosUser user = (NacosUser) identityContext.getParameter(USER_IDENTITY_PARAM_KEY);
        System.out.println(identityContext.getParams());
        nacosAuthManager.auth(permission, user);
        return true;
    }

    @Override
    public String getAuthServiceName() {
        return "IMOOC_AUTH_PLUGIN";
    }

    private void checkNacosAuthManager() {
        if (null == nacosAuthManager) {
            nacosAuthManager = ApplicationUtils.getBean(NacosAuthManager.class);
        }
    }
}
