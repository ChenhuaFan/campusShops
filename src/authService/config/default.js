module.exports = {
    jwt: {
        head: {
            "typ": "JWT",
            "alg": "HS256"
        },
        payload : {
            "iat" : "",
            "exp" : 1200000,
            "nbf" : ""
        },
        secret: "lONZ03Soa&aJb$KuWE$njAZ%PWK#Z6k9CYLnusw^"
    }
}