const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const superagent = require('superagent');
const config = require('./config/default');
const time = require('./modules/time')
const jwt = require('./modules/jwt');

app.use(koaBody());

const authGet = async ctx => {
    //do signin and return a token if user info is correct.
    const body = ctx.request.body;
    //request other apis and get return values
    let userReq = body => {
        return superagent
        .post('http://localhost:3000/user/auth')
        .type('application/json')
        .set('Accept', 'application/json')
        .send(body)
        .then((res, err) => {
            return JSON.parse(res.res.text);
        });
    }
    //async get return values and set response
    let userRes = await userReq(body);
    if(userRes.status != false) {
        //generate token
        let now = time.now();
        let payload = {
            "name": "samchevia",
            "uuid": 1,
            "role": "admin",
            "iat": now,
            "exp": config.jwt.payload.exp,
            "nbf": now + config.jwt.payload.exp
        };
        let token = jwt.create(config.jwt.head, payload, config.jwt.secret);
        //set response
        ctx.response.status = 200;
        ctx.response.type = 'json';
        ctx.response.body = {
            "status": true,
            "info": token
        }
    } else {
        ctx.response.status = 403;
        ctx.response.type = 'json';
        ctx.response.body = {
            "status": false,
            "info": userRes.info
        }
    }
};

const authPost = ctx => {
    //do signup and return a token if request params are correct.
}

const authUpdate = ctx => {
    //do update a old token and return a new token if former token is legal
}

const test = ctx => {
    //here is a test module for returning a json.
    const body = ctx.request.body;
    if(body.a == 'a') {
        ctx.response.type = 'json';
        ctx.response.body = {
            "status": true
        }
    }
    else {
        ctx.response.type = 'json';
        ctx.response.body = {
            "status": false,
            "info": "wrong pass or user"
        }
    }
}

app.use(route.post('/auth/get', authGet));
app.use(route.post('/auth/post', authPost));
app.use(route.post('/auth/update', authUpdate));
//test link
app.use(route.post('/user/auth', test));

app.listen(3000);