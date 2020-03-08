package com.hww.houserent.userrealm;


import com.hww.houserent.entity.UserEntity;
import com.hww.houserent.service.impl.UserServiceImpl;
import com.hww.houserent.util.MD5Utils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;

    //授权中心
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登陆的用户信息
        String username = (String) principals.getPrimaryPrincipal();
        //创建授权验证对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //授予权限
        simpleAuthorizationInfo.setRoles(userService.getRoles(username));
        //授予角色
        simpleAuthorizationInfo.setStringPermissions(userService.getPermissions(username));

        //返回对象
        return simpleAuthorizationInfo;
    }

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String username = (String) token.getPrincipal();
        //调用根据用户名查询用户的方法
        UserEntity user = userService.getUserByName(username);
        System.out.println("调用根据用户名查询用户的方法" + user.getPassword());
        //对象不为空则存在
        if (user != null) {
            //进行身份验证
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "xxx");

            //设置盐，用来核对密码
            ((SimpleAuthenticationInfo) authenticationInfo).setCredentialsSalt(ByteSource.Util.bytes(MD5Utils.SALT));

            return authenticationInfo;
        }//返回空表示验证失败
        return null;
    }
}