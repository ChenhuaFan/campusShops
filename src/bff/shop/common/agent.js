const agent = require('superagent');

let temp = (url, body) => {
    return agent
        .post(url)
        .send(body)
        .set('Accept', 'application/json')
        .then(function(res) {
            return res.body;
        });
};

module.exports = temp;