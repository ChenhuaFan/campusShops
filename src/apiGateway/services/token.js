const request = require('../common/request');
const token = require('../common/readServices').services.token;
const conf = require('../config/default');

module.exports = {
    'get': async req => {
        // use myReq send a request to /token/get to get result
        const header = req.header == undefined ? conf.requestHeader : req.header;
        const body = req.body;
        // here is request fun;
        console.log(`${token.url}:${token.port}`);
        let result = await request.post(`http://${token.url}:${token.port}/token/get`, header, body);
        return result.body;
    },
    'update': async req => {
        
    },
    'getSecret': async req => {

    }
}