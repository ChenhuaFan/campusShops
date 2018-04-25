# 用户操作API文档
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

## API功能说明
### 1. 用户注册
```javascript
// 请求地址
"http://host/api/v1/auth/register"
```
### 请求字段说明
|字段名|格式|含义|必填|备注|
|:--|:--|:--|:-:|:--|
|userName|text|用户名|是||
|pw|text|密码|是||
|email|text|用户邮箱|是||
|phone|text|用户手机号|是||
|gender|text|用户性别|是||

### 返回值说明
|字段名|含义|
|:--|:--|
|token|登录成功返回的token令牌|

```javascript
//样例 注册成功
{
  "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
```
### 2. 用户登录
```javascript
// 请求地址
"http://host/api/v1/auth/login"
```
### 请求字段说明
|字段|格式|含义|是否必填|
|:----|:----|:----|:----:|
|userName|text|用户名|是|
|pw|sha256加密字符串|用户密码的加密值|是|
### 返回值说明
|字段名|含义|
|:--|:--|
|token|登录成功返回的token令牌|

```javascript
//样例 登录成功
{
  "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
```

### 3. 令牌更新
```javascript
// 请求地址
"http://host/api/v1/auth/update"
```
### 字段说明

|字段名|格式|含义|必填|
|:--|:--|:--|:-:|
|oldToken|text|需要被更新的token|是|

### 返回值说明

|字段名|含义|
|:--|:--|
|token|登录成功返回的token令牌|

~~~javascript
//样例 更新成功
{
  "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
```