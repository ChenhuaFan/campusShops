const superagent = require('superagent');

module.exports = {
    'post': (url, header, body) => {
        superagent
            .post(url)
            .send(body)
            .set(header)
            .end(res => {
                return res;
            });
    },
    'get': (url, header, query) => {
        superagent
            .get(url)
            .query(query)
            .set(header)
            .end(res => {
                return res;
            });
    }
}