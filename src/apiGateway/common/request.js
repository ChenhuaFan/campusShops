const superagent = require('superagent');

module.exports = {
    'post': async (url, header, body) => {
        return await superagent
            .post('http://localhost:5000/token/get')
            .set(header)
            .send(body)
    },
    'get': async (url, header, query) => {
        return await superagent
            .get(url)
            .query(query)
            .set(header)
    }
}