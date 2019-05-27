# Spring Boot Simple JWT

### Generate RSA private key in PKCS#8 format
```bash
openssl genpkey -algorithm RSA -pkeyopt rsa_keygen_bits:2048 -out private.pem
```

### Generate public key
```bash
openssl rsa -pubout -in private.pem -out public.pem
```
