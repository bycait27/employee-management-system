# Environment Variables Setup

## For Local Development

### Option 1: Use application-local.properties (Recommended)
1. Copy `application-local.properties.example` to `application-local.properties`
2. Update the JWT secret in that file
3. Run with: `mvn spring-boot:run -Dspring.profiles.active=local`

### Option 2: Environment Variables
Set these environment variables before starting the application:

```bash
export JWT_SECRET="your-super-secret-jwt-key-here"
export JWT_EXPIRATION="86400000"
```

Then run: `mvn spring-boot:run`

## For Production

### Set Environment Variables:
```bash
export JWT_SECRET="your-production-jwt-secret-minimum-256-bits"
export JWT_EXPIRATION="86400000"
export SPRING_PROFILES_ACTIVE="prod"
```

### Or use Docker secrets, Kubernetes secrets, etc.

## Generate New JWT Secret

Use this command to generate a secure random secret:
```bash
openssl rand -base64 64
```

## Important Security Notes

1. **Never commit JWT secrets to Git**
2. **Use different secrets for different environments**
3. **JWT secrets should be at least 256 bits (32 bytes)**
4. **Rotate secrets regularly in production**
5. **Use proper secret management in production (AWS Secrets Manager, Azure Key Vault, etc.)**
