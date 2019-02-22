1.得到token
http://localhost:8043/oauth/token?username=admin&password=1234567&grant_type=password&scope=all&client_id=admin&client_secret=123456

2.访问资源

http://localhost:8043/user?access_token=f9de7f26-766e-4686-9f88-1b1b8f1b473b

3.返回字段说明

>access_token：表示访问令牌，必选项。

>token_type：表示令牌类型，该值大小写不敏感，必选项，可以是bearer类型或mac类型。

>expires_in：表示过期时间，单位为秒。如果省略该参数，必须其他方式设置过期时间。

>refresh_token：表示更新令牌，用来获取下一次的访问令牌，可选项。

>scope：表示权限范围，如果与客户端申请的范围一致，此项可省略。
