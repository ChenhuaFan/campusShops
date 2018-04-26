# 商店类型和商店多对多关联API
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
### 1.通过商店类型id获取商店id
>//内部访问地址如下
 "http://myhost:port/rshoptype/typeFindshopId"
  ### 字段说明
 |字段|格式|含义|必填|
 |-|-|-|-|
 |shoptype|int|商店类型|
 |index|int|当前商店位置|是|
 |limit|int|返回的条数|是|
 ##### 返回值说明
 |字段|含义|
 |-|-|
 |shopid|商店信息数组|
 |数组中存放信息json对象| 返回值说明|
 
 ## 商家信息json对象字段说明
  |字段|含义|
 |-|-|
 |rShopId|商店Id|


```
    //样例说明正常显示
    {
        "shopid":[
       { 
           "rShopId":"1"
       },
        { 
        "rShopId":"2"
       }
      ]
    }      
    //未提供足够参数
    {
        "status":flase,
        "info":wrong disaplay
    }
```
### 2.通过商店id获取商店类型id
>//内部访问地址如下
 "http://myhost:port/rshoptype/shopIdFindshopTypeid"
 ### 字段说明
 |字段|格式|含义|必填|
 |-|-|-|-|
 |shopId|int|商店id|
 |index|int|当前商品位置|是|
 |limit|int|返回的条数|是|
 
 |字段|含义|
 |-|-|
 |shopType|商店类型信息数组|
|数组中存放信息json对象|

## 商店类型信息json对象字段说明
  |字段|含义|
 |-|-|
|rshopTypeId|商品类型id|
```
    //样例说明正常显示
    {
        "shopType":[
       { 
           "rshopTypeId":"1"
       },
        { 
        "rshopTypeId":"2"
       }
      ]
    }      
    //未提供足够参数
    {
        "status":flase,
        "info":wrong disaplay
    }
```
### 3.添加关联
>//内部访问地址如下
 "http://myhost:port/rshoptype/add"
 ### 字段说明
 |字段|格式|含义|必填|
 |-|-|-|-|
 |rShopId|int|商店表外键|是|
|rshopTypeId|int|商品类型表外键|是|
### 返回值说明
 |字段|含义|
 |-|-|
  |rShopShopTypeId|关联Id|
  |rShopId|商店表外键|
  |rshopTypeId|商品类型表外键|

```
    //样例说明正常显示
    {
       "rShopShopTypeId":"1",
       "rShopId":"1";
       "rshopTypeId":"1"
    }      
    //未提供足够参数
    {
        "status":flase,
        "info":wrong 
    }
```

### 4.删除关联
>//内部访问地址如下
 "http://myhost:port/rshoptype/delete"
 ### 字段说明
 |字段|格式|含义|必填|
 |-|-|-|-|
 |rShopShopTypeId|int|关联Id号|是|
### 返回值说明
 |字段|含义|
 |-|-|
 |isDelete|删除状态，0代表未删除，1代表删除|

```
    //样例说明
    {

        "isDelete":1,
    }      
       //id不存在
    {
        "status":flase,
        "info"：id can not found and can not delete
    }
```