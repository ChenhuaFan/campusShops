# 商店类型后台API文档

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
## 1.显示商家类型
>//内部访问地址如下
 "http://myhost:port/shopType/disaplay"
 ### 字段说明
 |字段|格式|含义|必填|
 |-|-|-|-|
|index|int|记录开始位置|是|
|lim|int|需要的类型数目|是|
### 返回值说明
 |字段|含义|
 |-|-|
 |shopType|商品信息数组|
 |数组中存放信息json对象||
## 商品信息json对象字段说明
 |字段|含义|
 |-|-|
 |shopTypeId|商品类型Id|
 |shopTypeName|商品类型名|
 |shopTypePicture|商品类型图片|
 ```
    //样例说明正常显示
    
    {
        "shopType":[
        {
        "shopTypeId":"1",
        "shopTypeName":"xxx",
        "shopTypePicture":"picture.jpg"
        },
        {
        "shopTypeId":"2",
        "shopTypeName":"xxx",
        "shopTypePicture":"picture2.jpg"
        }
      ]
        
    }      
    //未提供足够参数
    {
        "status":flase,
        "info":wrong disaplay
    }
```
### 2.添加商品类型
>//内部访问地址如下
 "http://myhost:port/shopType/add"

字段|格式|含义|必填|
 |-|-|-|-|
|name|varchar|商品类型名|是|
|picture|varch|商品类型图片|否|

### 返回值说明
 |字段|含义|
 |-|-|
 |shopTypeId|商品类型Id|
 |shopTypeName|商品类型名|
 |shopTypePicture|商品类型图片|

```
    //样例说明
    {
       "shopTypeId":"1"
        "shopTypeName":"午餐"
        "shopTypePicture":"picture2.jpg"
        
    }  
       //未提供足够参数
    {
        "status":flase,
        "info":can not add shopType
    }    
```

### 5.删除商品
>//内部访问地址如下
 "http://myhost:port/shopType/delete"

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
