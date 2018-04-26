# 商家操作API文档
> 2018.4.26   
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

## API功能说明
```javascript
// 请求地址
"http://host/api/v1/shop/get"
```
### 字段说明
|字段|格式|含义|必填|
|:--|:--|:--|:-:|
|index|int|当前分页数|是|
|limit|int|返回的条数|是|
 
### 返回值说明
|字段|含义|
|:--|:--|
|shop|商店信息数组|
 
### 商家对象字段说明
|字段|含义|
|:--|:--|
|shopId|商店Id|
|shopName|商店名称|
|shopRank|商店评级|
|shopPicture|商店图片地址|
|isopen|是否营业|
|shopBelong|店主userId|

```javascript
//样例说明正常显示
{
    "shop":[
		{ 
		"shopId":"1",
		"shopName":"xxx",
		"shopRank":5,
		"shopPicture":"picture.jpg",
		"isOpen":"0"，
		"shopBelong":"1"
		},
		{ 
		"shopId":"2",
		"shopName":"xxx",
		"shopRank":"5",
		"shopPicture":"picture.jpg",
		"isOpen":"0",
		 "shopBelong":"2"
		}
	]
}      
//未提供足够参数
{
    "status":flase,
    "info":wrong disaplay
}
```