# 商家后台API文档

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


### 1.显示商家列表
 >//内部访问地址如下
 "http://myhost:port/shop/shopDisaplay"

 ### 字段说明
 |字段|格式|含义|必填|
 |-|-|-|-|
|number|int|返回商家数目|是|
|page|int|显示商家页数|是|
 ### 返回值说明
 |字段|含义|
 |-|-|
 |shopId|商店Id|
 |shopName|商店名称|
 |shopRank|商店评级|
 |shopPicture|商店图片地址|
 |isopen|是否营业|

```
    //样例说明正常显示
    {
        "shopId":1,
        "shopName":"xxx",
        "shopRank":5,
        "shopPicture":"picture.jpg"
        "isOpen":0;
        
    }      
    //未提供足够参数
    {
        "status":flase,
        "info":wrong disaplay
    }
```
 ### 2.显示某个商家全部信息
 >//内部访问地址如下
 "http://myhost:port/shop/shopAllDisaplay"

|字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商家id|是|

 ### 返回值说明
  |字段|含义|
 |-|-|
|shopName|商店名称|
|shopType|商店类型|
|shopKeeper|商店店主|
|shopAddress|商店地址|
|shopPhoneNumber|商店号码|
 |shopRank|商店评级|
 |shopPicture|商店图片|
 |isOpen|是否营业|
 

 ```
    //样例说明
    {
        "shopName":"xxx",
        "shopType":"午餐",
        "shopkeeper":"商家名",
        "shopAddress":"512",
        "shopPhoneNumber":"13955556666",
        "shopRank":5,
        "shopPicture":"picture.jpg"
        "isOpen":1
    }     
       //用户id不存在 
    {
        "status":flase,
        "info":wrong disaplay
    } 
```
 ### 3.修改商家信息
 >//内部访问地址如下
 "http://myhost:port/shop/shopModify"

 字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商店的ID|是|
|name|varchar|商店名|是|
|type|varchar|商店类型|是|
|shopkeeper|varchar|商家名|是|
|address|varchar|商店地址|是|
|phoneNumber|varchar|商家号码|是|
|rank|int|商店等级|是|
|picture|varch|商品图片|否|
|isOpen|int|营业状态|是|
### 返回值说明
 |字段|含义|
 |-|-|
 |shopId|商店的ID|
|shopName|商店名|
|shopType|商店类型|
|shopkeeper|商家名|
|shopAddress|商店地址|
|shopPhoneNumber|商家号码|
|shopRank|商店等级|
|shopPicture|商品图片|
|isOpen|营业状态|
```
    //样例说明
    {
        "shopId":1,
        "shopName":"xxx",
        "shopType":"午餐",
        "shopkeeper":"商家名",
        "shopAddress":"512",
        "shopPhoneNumber":"13955556666",
        "shopPicture":"picture.jpg",
        "shopRank":5,
        "isOpen":0;
    }      
       //用户id无法修改
    {
        "status":flase,
        "info":"the shopId can not modify information"
    }
```

### 4.添加商家
>//内部访问地址如下
 "http://myhost:port/shop/shopAdd"

字段|格式|含义|必填|
 |-|-|-|-|
|name|varchar|商店名|是|
|type|varchar|商店类型|是|
|shopkeeper|varchar|商家名|是|
|address|varchar|商店地址|是|
|phoneNumber|varchar|商家号码|是|
|rank|int|商店等级|是|
|picture|varch|商品图片|否|
|isOpen|int|营业状态|是|
### 返回值说明
 |字段|含义|
 |-|-|
 |shopId|商店的ID|
|shopName|商店名|
|shopType|商店类型|
|shopkeeper|商家名|
|shopAddress|商店地址|
|shopPhoneNumber|商家号码|
|shopRank|商店等级|
|shopPicture|商品图片地址|
|isOpen|营业状态|

```
    //样例说明
    {
        "shopId":2,
        "shopName":"xxx",
        "shopType":"午餐",
        "shopkeeper":"商家名",
        "shopAddress":"513",
        "shopPhoneNumber":"13955556666",
        "shopRank":5,
        "shopPicture":"picture.jpg"
        "isOpen":0;
        
    }  
       //未提供足够参数
    {
        "status":flase,
        "info":can not add shop
    }    
```

### 5.删除商家
>//内部访问地址如下
 "http://myhost:port/shop/shopDelete"

|字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商家id|是|
 
 ### 返回值说明
 |字段|含义|
 |-|-|
 |shopId|商店的ID|
|shopName|商店名|
|shopType|商店类型|
|shopkeeper|商家名|
|shopAddress|商店地址|
|shopPhoneNumber|商家号码|
|shopRank|商店等级|
|shopPicture|商品图片|
|isOpen|营业状态|
|isDelete|删除状态，0代表未删除，1代表删除|

```
    //样例说明
    {
        "shopId":1,
        "shopName":"xxx",
        "shopType":"午餐",
        "shopkeeper":"商家名",
        "shopAddress":"512",
        "shopPhoneNumber":"13955556666",
        "shopRank":5,
        "shopPicture":"Picture.jpg"
        "isOpen":0;
        "isDelete":1,
    }      
       //id不存在
    {
        "status":flase,
        "info":shopId can not found and can not delete
    }
```
### 5.修改是否营业
>//内部访问地址如下
 "http://myhost:port/shop/shopOpen"

|字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商家id|是
 ### 返回值说明
 |字段|含义|
 isOpen|0代表未营业，1代表营业|
```
    //样例说明
    {
        "isOpen":0
    }
        //id不存在
    {
        "status":flase,
        "info":shopId can not found and can not open
    }
```
 ### 6.审核商家
 >//内部访问地址如下
 "http://myhost:port/shop/shopVerify"

 |字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商家id|是|
  ### 返回值说明
 |字段|含义|
 |-|-|
 |isVerify|0代表未通过审核，1代表通过审核|
```
    //样例说明
    {
        "isVerify":0
    }
        //id不存在
    {
        "status":flase,
        "info":"shopId can not found and can not verify"
    }
```

 ### 7.修改评级
 >//内部访问地址如下
 "http://myhost:port/shop/shopRank"

 |字段|格式|含义|必填|
 |-|-|-|-|
 |id|int|商家id|是
 ### 返回值说明
 |字段|含义|
 |-|-|
 |shopRank|商店等级|
 ```
    //样例说明
    {
        "shopRank":5
    }
           //id不存在
    {
        "status":flase,
        "info":"shopId can not found and can not comment"
    }
 ```
 ### 8.通过商家名获取商家id
 >//内部访问地址如下
 "http://myhost:port/shop/queryShopidByName"

 |字段|格式|含义|必填|
 |-|-|-|-|
 |name|varchar|商家名|是|
 ### 返回值说明
 |字段|含义|
 |-|-|
 |shopId|商家Id|
 ```
    //样例说明
    {
        "shopId":1
    }
    //商家名不存在 无法查看id
   {
        "status":flase,
        "info":"name can not found"
    }
 ```
 