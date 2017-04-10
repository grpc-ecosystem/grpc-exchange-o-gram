# Everything default
```
$ openssl req -x509 -new -newkey rsa:1024 -keyout ca.key -nodes -out ca.pem -config ca-openssl.cnf -days 3650 -extensions v3_req
$ openssl genrsa -out server.key.rsa 1024
$ openssl pkcs8 -topk8 -in server.key.rsa -out server.key -nocrypt
$ rm server.key.rsa
```

# Everything default, except common name localhost
```
$ openssl req -new -key server.key -out server.csr -config server-openssl.cnf
$ openssl x509 -req -in server.csr -CA ca.pem -CAkey ca.key -CAcreateserial -out server.pem -days 500 -sha256
$ rm server.csr
$ rm ca.srl
```
