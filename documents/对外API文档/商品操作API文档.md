# 商品操作API文档
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
### 1. 按商家Id查找所有商品
```javascript
// 请求地址
"http://host/api/v1/products/get"
```
### 字段说明
|字段|格式|含义|必填|
|:--|:--|:--|:-:|
|goodsBelong|int|商家Id|是|
|index|int|当前分页位置|是|
|limit|int|返回的条数|是|
### 返回值说明
|字段|含义|
|:--|:--|
|goods|商品信息数组|
### 商品信息对象字段说明
|字段|含义|
|:--|:--|
|goodsId|商品Id|
|goodsName|商品名称|
|goodsStock|商品数量|
|goodsPrice|商品价格|
|goodsPicture|商品图片地址|
|goodsBelong|商品所属商家|
 

```javascript
//样例说明正常显示
    
{
	"goods":[
		{
		    "goodsId":"1",
		    "goodsName":"xxx",
		    "goodsStock":"100",
		    "goodsPrice":"5",
		    "goodsPicture":"picture.jpg",
		    "goodsBelong":"1"
		},
		{
		    "goodsId":"2",
		    "goodsName":"xxx",
		    "goodsStock":"100",
		    "goodsPrice":"3",
		    "goodsPicture":"picture2.jpg",
		    "goodsBelong":"1"
		}
	]
}      
//未提供足够参数
{
    "status":flase,
    "info":wrong disaplay
}
```

### 2. 得到商品详情信息
```javascript
// 请求地址
"http://host/api/v1/products/getDetail"
```
### 字段说明
|字段|格式|含义|必填|
|:--|:--|:--|:-:|
|id|int|商品id|是|

### 返回值说明
|字段|含义|
|:--|:--|
|goodsId|商品Id|
|goodsName|商品名称|
|goodsStock|商品数量|
|goodsPrice|商品价格|
|goodsPicture|商品图片地址|
|goodsDepict|商品描述|
|goodsBelong|商品所属商家|

```
//样例说明
{
    "goodsId":"1",
    "goodsName":"SA",
    "goodsStock":"100",
    "goodsPrice":"2",
    "goodsPicture":"picture.jpg",
    "goodsDepict":"辣"，
    "goodsBelong":"1"
}     
//商品id不存在 
{
    "status":flase,
    "info":wrong disaplay
} 
```