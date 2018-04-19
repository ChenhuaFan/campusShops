# 接口文档说明:

> 设计者：吴亮and叶文成 时间：2018.4.16 版本：1.0.0   
服务基于JavaWeb完成  
1. 所有http请求是基于post
2. 为实现服务的统一，接口返回的数据默认为json格式
 
-----
## 1. 功能说明
 
1.1 工单的添加
1.2 工单的删除
1.3 获取工单的状态
1.4 获取工单的处理记录
 

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

## 2.1 工单的添加 ：
``` java
"http://localhost:8080/serviceWorkOrder/add"
//api第一版，Auth服务集合中基于post的登录服务
```
#### 2.1.1 调用API需要向接口发送一个JSON（POST）来访问服务。

###字段如下格式
| 字段名     | 格式     | 含义 | 必填 |
| :------| :------ | :------ | :------ |
| UserID | text，且必须由数字组成 | 用户ID | 是 |
| Title  | text | 标题 | 是 |
| Description  | text | 内容 | 是 |
| StartDate  | time | 开始时间 | 是 |

#### 2.1.2 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| status | 上述不再说明 |
| info | 上述不再说明 |
``` java
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

## 2.2 工单的删除 ：
``` java
"http://localhost:8080/serviceWorkOrder/delete"
//api第一版，Auth服务集合中基于post的登录服务
```
#### 2.2.1 调用API需要向接口发送一个JSON（POST）来访问服务。

###字段如下格式
| 字段名     | 格式     | 含义 | 必填 |
| :------| :------ | :------ | :------ |
| UserID | text，且必须由数字组成 | 用户ID | 是 |
| ServiceID | text，且必须由数字组成 | 工单ID | 是 |

#### 2.2.2 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| status | 上述不再说明 |
| info | 上述不再说明 |
``` java
//样例 删除成功
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


## 2.3 获取工单的状态 ：
``` java
"http://localhost:8080/serviceWorkOrder/status"
//api第一版，Auth服务集合中基于post的登录服务
```
#### 2.3.1 调用API需要向接口发送一个JSON（POST）来访问服务。

###字段如下格式
| 字段名     | 格式     | 含义 | 必填 |
| :------| :------ | :------ | :------ |
| UserID | text，且必须由数字组成 | 用户ID | 是 |
| ServiceID | text，且必须由数字组成 | 工单ID | 是 |

#### 2.3.2 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| status | 工单状态值 |
 0表示继续，1表示终止
``` java
//样例 工单终止
{
    "status": 1,
    
}

//样例 有错误产生
{
    "state": false,
    "info": "Database Error."
}
```

## 2.4 获取工单的处理记录 ：
``` java
"http://localhost:8080/serviceWorkOrder/records"
//api第一版，Auth服务集合中基于post的登录服务
```
#### 2.4.1 调用API需要向接口发送一个JSON（POST）来访问服务。

###字段如下格式
| 字段名     | 格式     | 含义 | 必填 |
| :------| :------ | :------ | :------ |
| UserID | text，且必须由数字组成 | 用户ID | 是 |
| ServiceID | text，且必须由数字组成 | 工单ID | 是 |
#### 2.4.2 返回值说明:
| 字段名 | 含义 |
| :---| :--- |
| records | json格式的处理记录集 |
 
``` java
//样例 查询结果

{
    "2": {
        "ServiceID": 2,
        "UserID": 2,
        "Description": "v自行车v",
        "Time": "2018-04-19 22:50:52.0"
    },
    "4": {
        "ServiceID": 2,
        "UserID": 2,
        "Description": "v自行车v",
        "Time": "2018-04-19 22:50:52.0"
    },
    "6": {
        "ServiceID": 2,
        "UserID": 2,
        "Description": "v自行车v",
        "Time": "2018-04-19 22:50:52.0"
    },
    "8": {
        "ServiceID": 2,
        "UserID": 2,
        "Description": "v自行车v",
        "Time": "2018-04-19 22:50:52.0"
    }
}

//样例 有错误产生
{
    "status": false,
    "info": "Database Error."
}
```
