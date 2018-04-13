const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const conf = require('./config/default');
const base64url = require('base64url')
const crypto = require('crypto');

app.use(koaBody());

const getCrypto = (type, key) => {
    return crypto.createHmac(type, key);
}

const authGet = async ctx => {
    const tokenExp = conf.token;
    const post = ctx.request.body;
    let token = "";
    let header = conf.token.header;
    let payload = conf.token.payload;
    let secret = ""; 
    //built payload
    for(let key in payload) {
        payload[key] = post[key];
    }
    if(payload["role"]=="")
    //prepare for builting secret
    payload["iat"] = new Date().getTime();
    payload["exp"] = parseInt(payload["iat"]) + tokenExp.limit.exp;
    payload["nbf"] = parseInt(payload["exp"]);
    //built secret
    header = base64url(JSON.stringify(header));
    payload = base64url(JSON.stringify(payload));
    let hamc = getCrypto(tokenExp.header.alg, tokenExp.secret);
    hamc.update(`${header}.${payload}`);
    secret = hamc.digest('hex');
    token = `${header}.${payload}.${secret}`;
    //set response
    ctx.response.status = 200;
    ctx.response.type = 'json';
    ctx.response.body = {
        "token": token
    };
};

const authUpdate = ctx => {
    //do update a old token and return a new token if former token is legal
    ctx.response.status = 200;
    ctx.response.type = 'json';
    ctx.response.body = {
        "status": true,
        "info": "ab"
    }
}

app.use(route.post('/token/get', authGet));
app.use(route.post('/token/update', authUpdate));

app.listen(3030);