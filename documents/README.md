# Documents

Here are the documents of several micro-services in this project. Them mainly describe how does module response request and what arguments they accept.

## examples
All of documents were written in Chinese. So here is two English version examples of them. One is module document, other is architecture document.

## Token Module API Documentation

> 2018.4.11  
> by: sam  
> Message delivery method based on Json   
> Workflow: create payload and header part based on template and then encrypted splice, return complete token. If the parameters are missing then return an error.
> ! When user role, admin level, user name is changed. The original token becomes invalid and requires a new login, thus increasing security.

The format of the error message returned is described below.

> The error info will not be explained in detail in subsequent documents

|field name|meaning|
|:--|:--|
|status|status
|status|status|
|info|the information about the error|

```javascript
//sample Service error: illegal access
{
  "status": false,
  "info": "insufficient params"
}
```

## Function API description
#### 1. Create a Token
```javascript
// The internal API is accessed at the following address
"http://myhost:port/token/get"
```

###### Field Description

|field name|format|meaning|required|comments|
|:--|:--|:--|:-:|:--|
|user|text|username of the user|is||
|userId|int|the user's Id|is||
|role|text|the user's role|yes||
|userImage|text|the user's avatar address|yes||
|shopId|int|The store the owner runs|No|Required if the user role is "store owner"|
|adminLevel|int|admin level|No|Required if user role is "admin"|

###### Return Value Description

|field name|meaning|
|:--|:--|
|token|token token returned by successful login|

```javascript
//sample login success
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
//Sample There is an error generated: wrong account or password
{
  "status": false,
  "info": "insufficient params"
}
```

#### 2. Update a Token
```javascript
// The internal API access address is as follows
"http://myhost:port/token/update"
```
###### Field Description

|field name|format|meaning|required|
|:--|:--|:--|:-:|
|oldToken|text|token that needs to be updated|yes|

###### Return Value Description

|field name|meaning|
|:--|:--|
|token|token token returned by successful login|

~~~javascript
//sample update success
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
//sample There is an error generated: illegal token
{
  "status": false,
  "info": "Illegal token"
}
~~~

## User API documentation
> 2018.4.25  
> by sam  
> based on json

## Description of the returned error message format
> All error messages after that are similar, so they are not given again and are only described here once.

| field name| meaning|
|:--|:--|
|status|status
|status|status|
|info|error message|

```javascript
{
	"status": false,
	"info": "wrong pw or username"
}
```

## API function description
### 1. user registration
```javascript
// Request address
"http://host/api/v1/auth/register"
```
### Request field description
|Field Name|Format|Meaning|Required|Remarks|
|:--|:--|:--|:--:-:|:--|
|userName|text|username|is||
|pw|text|Password|Yes||
|email|text|user email|yes||
|phone|text|user's cell phone number|yes||
|gender|text|user gender|yes||

### Return Value Description
|field name|meaning|
|:--|:--|
|token|token token returned by successful login|

```javascript
//sample Registration success
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
```
### 2. user login
```javascript
// Request address
"http://host/api/v1/auth/login"
```
### Request field description
|Field|Format|Meaning|Required|
|:--|:--|:--|:--|
|userName|text|username|yes|
|pw|sha256 encrypted string|encrypted value of user password|yes|
### Return value description
|fieldName|meaning|
|:--|:--|
|token|The token token returned from a successful login|

```javascript
//sample login success
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
```

### 3. token update
```javascript
// Request address
"http://host/api/v1/auth/update"
```
### Field Description

|field name|format|meaning|required|
|:--|:--|:--|:--|
|oldToken|text|token|that needs to be updated|is|

### Return Value Description

|field name|meaning|
|:--|:--|
|token|token token returned by successful login|

~~~javascript
//sample update success
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDgyNDQ1MTAsImlhdCI6MTUwODI0MzMxMCwid"
}
```

Translated with www.DeepL.com/Translator (free version)

