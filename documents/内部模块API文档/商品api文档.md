# 商品后台API文档

## 返回错误信息格式说明
|字段|含义|
|-|-|
|status|状态|
|info|错误的信息|
```
    //样例 id不存在
    {
        "status"：false,
        "info":"can not found id"
    }
```
## 功能API说明
### 1.显示商品列表
 >//内部访问地址如下
 "http://myhost:port/goods/disaplay"

 ### 字段说明
 |字段|格式|含义|必填|
 |-|-|-|-|
|goodsBelong|int|商品所属商家|是|
|index|int|当前商品位置|是|
|limit|int|返回的条数|是|
 ### 返回值说明
 |字段|含义|
 |-|-|
 |goods|商品信息数组|
 |数组中存放信息json对象||
 ## 商品信息json对象字段说明
 |字段|含义|
 |-|-|
 |goodsId|商品Id|
 |goodsName|商品名称|
 |goodsStock|商品数量|
 |goodsPrice|商品价格|
 |goodsPicture|商品图片地址|
 |goodsBelong|商品所属商家|
 

```
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
### 2.显示某个商品全部信息
 >//内部访问地址如下
 "http://myhost:port/goods/allDisaplay"

|字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商品id|是|

 ### 返回值说明
  |字段|含义|
 |-|-|
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
### 3.修改商品信息
 >//内部访问地址如下
 "http://myhost:port/goods/modify"

 字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商品ID|是|
|name|varchar|商品名|是|
|stock|int|商品数量|是|
|price|int|商品价格|是|
|picture|varch|商品图片|否|
|depict|varch|描述商品|否|
|goodsBelong|int|商品所属商家|是|
### 返回值说明
 |字段|含义|
 |-|-|
 |goodsId|商品ID|
|goodsName|商品名|
|goodsStock|商品数量|
|goodsPrice|商品价格|
|goodsPicture|商品图片|
|goodsDepict|描述商品|
|goodsBelong|商品所属商家|

```
    //样例说明
    {
       "goodsId":"1",
        "goodsName":"SA",
        "goodsStock":"100",
        "goodsPrice":"2",
        "goodsPicture":"picture.jpg",
        "goodsDepict":"辣",
        "goodsBelong":"1"
    }      
       //用户id无法修改
    {
        "status":flase,
        "info":"the shopId can not modify information"
    }
```
### 4.添加商品
>//内部访问地址如下
 "http://myhost:port/goods/add"

字段|格式|含义|必填|
 |-|-|-|-|
|name|varchar|商品名|是|
|stock|int|商品数量|是|
|price|int|商品价格|是|
|picture|varch|商品图片|否|
|depict|varch|描述商品|否|
|goodsBelong|int|商店Id|是|
### 返回值说明
 |字段|含义|
 |-|-|
 |goodsId|商品ID|
|goodsName|商品名|
|goodsStock|商品数量|
|goodsPrice|商品价格|
|goodsPicture|商品图片|
|goodsDepict|描述商品|
|goodsBelong|商品所属商家|

```
    //样例说明
    {
         "goodsId":"1",
        "goodsName":"SA",
        "goodsStock":"100",
        "goodsPrice":"2",
        "goodsPicture":"picture.jpg",
        "goodsDepict":"辣"
        "goodsBelong":"1"
        
    }  
       //未提供足够参数
    {
        "status":flase,
        "info":can not add shop
    }    
```

### 5.删除商品
>//内部访问地址如下
 "http://myhost:port/goods/delete"

|字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商品id|是|
 
 ### 返回值说明
 |字段|含义|
 |-|-|
|isDelete|删除状态，0代表未删除，1代表删除|

```
    //样例说明
    {

        "isDelete":1
    }      
       //id不存在
    {
        "status":flase,
        "info":shopId can not found and can not delete
    }
```
