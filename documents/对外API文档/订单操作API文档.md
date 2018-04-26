# 订单操作API文档
> 2018.4.25  
> by samchevia  
> 基于json

## 返回的错误信息格式说明
> 之后所有的错误信息都类似，故不再给出，仅在此做一次说明。

|字段名|含义|
|:--|:--|
|status|状态|
|info|错误的信息|

```javascript
{
	"status": false,
	"info": "wrong pw or username"
}
```
## 订单的状态(orderStatus)说明
|状态值|格式|含义|
|:-:|:--|:--|
|1|int|创立完成|
|2|int|支付完成|
|3|int|等待配送|
|4|int|配送完成|
|5|int|退款中|
|6|int|退款完成|

## API功能说明
### 1. 创建一个订单
```javascript
// 请求地址
"http://host/api/v1/order/post"
```
### 字段说明

|字段|格式|含义|是否必填|
|:----|:----|:----|:---:|
|orderContent|json|订单内容|是|
|orderAmount|text|订单金额|是|
|userId|int|订单发起的用户ID|是|
|shopId|int|订单所属的商家ID|是|
|remark|text|备注|否|
## 返回值说明

|字段|格式|含义|是否必填|
|:----|:----|:----|:---:|
|orderId|int|订单ID|是|
|orderNumber|text|订单编号|是|
|generatedTime|text|创建时间|是|
|orderContent|json|订单内容|是|
|orderAmount|text|订单金额|是|
|orderStatus|int|订单的状态|是|
|remark|text|备注|是|
|userId|int|订单发起的用户|是|
|shopId|int|订单所属的商家|是|
```
//样例:创建成功
{
	"generatedTime": "Wed Apr 25 21:19:01 CST 2018",
	"orderNumber": "152466234159911",
	"orderAmount": "25",
	"orderId": 8,
	"orderStatus": "1",
	"remark": "dada",
	"shopId": 1,
	"userId": 1,
	"orderContent": {
	    ...
	}
}
```

### 2. 获取某用户的订单
```javascript
// 请求地址
"http://host/api/v1/order/get"
```
### 字段说明
|字段|格式|含义|是否必填|
|:----|:----|:----|:---:|
|userId|int|用户ID|是|
|index|int|当前订单的位置|是|
|limit|int|返回的条数|是|
### 返回值说明
|字段|格式|含义|是否必填|
|:----|:----|:----|:---:|
|orderId|int|订单ID|是|
|orderNumber|text|订单编号|是|
|generatedTime|text|创建时间|是|
|orderContent|json|订单内容|是|
|orderAmount|text|订单金额|是|
|orderStatus|int|订单的状态|是|
|remark|text|备注|是|
|userId|int|订单发起的用户|是|
|shopId|int|订单所属的商家|是|
```
//样例:根据用户ID查询订单
//传入参数
{
	"userId":1,
	"index":2,
	"limit":5
}

//返回字段
{
    "order": [
        {
            "generatedTime": "Sat Apr 21 21:36:56 CST 2018",
            "orderNumber": "152431781660311",
            "orderAmount": "25",
            "orderId": 3,
            "orderStatus": "1",
            "remark": "dada",
            "shopId": 1,
            "userId": 1,
            "orderContent": {
                ...
            }
        },
        {
            "generatedTime": "Sat Apr 21 21:36:59 CST 2018",
            "orderNumber": "152431781912711",
            "orderAmount": "25",
            "orderId": 4,
            "orderStatus": "1",
            "remark": "dada",
            "shopId": 1,
            "userId": 1,
            "orderContent": {
                ...
            }
        },
        ...
    ]
}
```