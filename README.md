# SSO Demo 单点登录

#### 介绍
一个简单的单点登录(SSO)的demo.简单来说就是在一个具有多个子系统的系统中，只用登录一个子系统，然后访问其他子系统时不需要再次登录，即“一次登录，多处访问”，能够有效的提升用户体验。




#### 使用说明

1.  更换自己的Redis

#### 流程

1.用户首次访问A系统，A系统发现用户未登录，则重定向到SSO认证中心并携带请求url，进行登录验证;

2用户在SSO认证中心进行用户名和密码睑证登录，登录成功后，服务器生成一个ticket，然后重定向到系统A的源url并将该ticket追加到url参数。

3.系统A获取到url参数中的ticket，向SSO发起ticket较验，较验成功，则系统A放行，并将ticket存入到cookie.

4用户访问8系统，此时B系统domain下已经携带ticket，直接向SSO发起ticket较验，较验成功，则放行，并将ticket存入cookie(更新ticket过期时间)

5.用户登出时，移除domain下的cookie。



#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
