# 接口文档说明:

> 设计者：吴亮and叶文成 时间：2018.4.16 版本：1.0.0   
服务基于JavaWeb完成  
1. 所有http请求是基于post
2. 为实现服务的统一，接口返回的数据默认为json格式
 
-----
## 1. 功能说明
 
1.1 获取购物车信息
1.2 购物车的添加（信息更新）
1.3 购物车的删除（信息更新）
 

## 2. API及详细描述

### 注：返回的错误信息
#### 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| status  | 处理状态 |
| info | 错误的信息 |
``` java 
//样例 服务发生错误
{
    "status": false,
    "info": "wrong account or password."
}
```
  下文将不再对 status 和 info 字段做详细说明 

### 2.1 购物车的添加
``` java
"http://myhost:8080/cart/add"
//api第一版，Auth服务集合中基于post的登录服务
```
#### 2.1.1 调用API需要向接口发送一个JSON（POST）来访问服务。

###字段如下格式
| 字段名     | 格式     | 含义 | 必填 |
| :------| :------ | :------ | :------ |
| UserID | text，且必须由数字组成 | 用户ID | 是 |
| Goods  | Json | 购物车信息 | 是 |

#### 2.1.2 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| status | 上述不再说明 |
| info | 上述不再说明 |
``` javascript
//样例 添加成功
{
    "status": true,
    "info": "success add."
}
//样例 有错误产生
{
    "status": false,
    "info": "Database Error."
}
```



### 2.2 购物车的查询 ：
``` java
"http://myhost:8080/cart/get"
//api第一版，Auth服务集合中基于post的登录服务
```
#### 2.2.1 调用API需要向接口发送一个JSON（POST）来访问服务。

###字段如下格式
| 字段名     | 格式     | 含义 | 必填 |
| :------| :------ | :------ | :------ |
| UserID | text，且必须由数字组成 | 用户ID | 是 |

#### 2.2.2 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| Goods | json格式的购物车信息 |
 

``` java
//样例 查询成功

{
     
    "Goods": "..."
}
```

### 2.3 购物车的删除 ：
``` java
"http://myhost:80/cart/delete"
//api第一版，Auth服务集合中基于post的登录服务
```
#### 2.3.1 调用API需要向接口发送一个JSON（POST）来访问服务。

###字段如下格式
| 字段名     | 格式     | 含义 | 必填 |
| :------| :------ | :------ | :------ |
| UserID | text，且必须由数字组成 | 用户ID | 是 |
| Goods | JSON        | 购物车信息 | 是 |

#### 2.3.2 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| status | 上述不再说明 |
| info | 上述不再说明 |
``` java 
//样例 删除成功
{
    "status": true,
    "info": "success delete."
}
//样例 有错误产生
{
    "status": false,
    "info": "Database Error."
}
```