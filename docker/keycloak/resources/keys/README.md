# Keys

## Token signing keys

Generated using:
```bash
openssl req -x509 -newkey rsa:1024 -nodes -keyout key.pem -out cert.pem -sha256 -days 36500
```

Make online:
```bash
head -n -1 key.pem | tail -n +2 -f | tr -d '\n'
```
