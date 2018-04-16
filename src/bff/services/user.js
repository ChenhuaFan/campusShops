const request = require('superagent');



module.exports = {
    'get': async req => {
        // use myReq send a request to /token/get to get result
        const header = req.header;
        const body = req.body;
        // here is request fun;
        const request = request(url, header, body);
        let result = await request();
        return result;
    },
    'update': async req => {
        
    },
    'getSecret': async req => {

    }
}