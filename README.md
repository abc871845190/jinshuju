# 简介
金数据后端项目

# 使用框架
1、swagger2 查看接口和测试接口  
2、lombok 框架插件  
3、Spring Security 权限与密码加密工具  
4、Spring Mail 邮箱相关工具  
5、redis 缓存数据库工具  
6、Java Web Token 令牌工具  
7、阿里的excel解析框架 easyExcel  
8、aop 切面拦截  
9、ZXing二维码生成框架

# 项目操作
0、浏览所有的模版列表  
1、注册一个用户  
2、登陆  
3、登陆以后一系列操作：创建表单等系列操作

# 已完成功能汇总
用户模块：注册，登陆，通过用户名检查用户，修改密码，修改邮箱，修改电话，获取用户信息，邮箱验证，注销  
表单模块：创建表单，获取用户表单，获取表单信息（获取表单是否开启填写，获取表单浏览量），复制表单，重命名表单，修改表单，删除表单，设置表单是否开启填写，
    获取用户列表（按表单创建时间(默认)，按数据提交时间排序），查看我为别人填写过的表单
    设置表单tag（String），获取所有表单tag，分页获取表单列表，模糊查询已公开模版名字，查找特定类别的已公开模版  
数据模块：添加表单数据，查看表单数据，查看填写所有人数，查看当天填写人数，清空数据，删除单个数据项，修改单个数据项，
    导入excel解析excel并插入到数据库（easyExcel框架）  
    
# 尚未优化/未完成功能
一、数据库：表单添加截止日期  
二、表单：公开模版界面url和记录表单浏览量处理（redis+spring aop+spring schedule），公开模版页面填写url，添加表单自动生成公开填写连接  
三、数据：添加表单数据之前判断是否过了截止日期和是否能够公开填写，添加用户自定义添加表单数据功能，封装是否公开填写，设定公开填写截止日期，单表单数据分析提取  
四、后期：灵活运用redis缓存存储数据，处理405，500，400等报错返回信息处理，
给表单添加是否只能填写一次功能  
五、base64编码返回图片以及存图片


# 参数汇总
sql-url:jdbc:mysql://localhost:3306/jinshuju?autoReconnect=true&characterEncoding=utf-8&testOnBorrow=true&serverTimezone=Asia/Shanghai&useAffectedRows=true  
redis:host: 127.0.0.1 password: 123456 port: 6379

# 更新日志
2020-11-09  : 创建表单整合生成二维码功能  
2020-11-10  : 表单模块添加收藏表单功能，获取表单公开url功能  
2020-11-13  : 表单模块优化获取用户所有表单的字段信息获取，添加获取单个表单所有组件字段信息功能  
2020-11-16  : 修复refreshToken无法获取问题，token可持久使用，跟进前端字段问题,，整个更新用put接口，局部更新用patch接口  
2020-11-23  : 添加收藏code，添加表单发布功能，批量删除数据项功能  
2020-11-25  : 修复一堆有的没的bug，字符规范问题  
2020-11-26  : 更新表单sql优化，组件字段添加icon字段存储标签图片，添加获取发布表单功能，修改表单获取固定形式  
2020-11-27  : 修复修改表单原有数据没有清空bug  
2020-11-30  : 增加删除组件content内容以及对应数据content内容功能，上传图片功能  
