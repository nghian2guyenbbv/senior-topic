Keycloak Microservices Configuration Guide

--- Step-by-Step Keycloak Configuration ---

1. Set up Keycloak
   - Port: 9090
   - Action: Ensure the instance is running and you have created a dedicated Realm (e.g., springboot-microservice-realm).
   - http://localhost:9090/admin
2. Create realm
   realm name: springboot-microservice-realm
3. Create Client
   - Client ID: microservice-auth
   - Name: microservice-auth
   - Capability Config:
   ![client-setting.png](images/client-setting.png)
     * Client Authentication: On (Enables Confidential access type).
     * Authorization: On (For fine-grained policies).
     * Authentication Flow: Standard Flow and Service Accounts Roles should be checked.
   - Service Account Roles: Allows the microservice to authenticate independently of a user.

4. Login Settings
   - Root URL: http://localhost:8080
   - Home URL: http://localhost:8080
   - Valid Redirect URIs: http://localhost:8080/*

5. Start the Gateway Application
   - Ensure your Spring Cloud Gateway application points toward the Keycloak discovery URL.

6. Generate Client Secret
   - Credentials tab -> Regenerate Code (Client Secret). Save this for application.yml.

7. Update swiggy-gateway
   Clean up Dependencies
   - Remove manual JWT signing/parsing dependencies (like jjwt or auth0-jwt).

8. Remove Manual Filters
   - Remove custom AuthenticationFilter or JwtTokenProvider classes.

9. Application Configuration (application.yml)
   spring:
     security:
       oauth2:
         resourceserver:
           jwt:
             issuer-uri: http://localhost:9090/realms/springboot-microservice-realm
             jwk-set-uri: http://localhost:9090/realms/springboot-microservice-realm/protocol/openid-connect/certs

10. Verify Discovery/Gateway
    - Check Service Discovery (e.g., Eureka at localhost:8761).

11. Create Security Configuration
In a Spring Cloud Gateway (WebFlux) environment, you need to define a SecurityWebFilterChain to handle the incoming JWTs from Keycloak.

Refined Step 11: SecurityConfig
Annotation: Use @EnableWebFluxSecurity (not WebSecurity, as Gateway is reactive).

CSRF: Usually disabled for stateless microservices.

Permit All: Ensure the Eureka dashboard or /eureka/** paths are accessible without a token so nodes can register.

Resource Server: Configure the gateway to act as an OAuth2 Resource Server.

--- Q&A ---

1. Explain JWT authentication with the Gateway:
   The API Gateway acts as the OAuth2 Resource Server or a Token Relay. The client sends a Bearer Token; the Gateway validates it against Keycloak's Public Key (JWKS). If valid, the Gateway forwards the request downstream, centralizing security.

2. What component was replaced by Keycloak?
   Keycloak replaces the Identity Provider (IdP) and the Authorization Server. You no longer need a local users table or a custom auth microservice for /login and JWT generation.

3. Explain OpenID Connect (OIDC):
   OIDC is an identity layer on top of OAuth 2.0. While OAuth 2.0 handles Authorization (access), OIDC handles Authentication (identity) by introducing the id_token (a JWT with user profile info).


docker run -p 9090:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin
quay.io/keycloak/keycloak:22.0.1 start-dev

realm name : springboot-microservice-realm

clinet name : microservice-auth

client secret : T20cuuUqBpdyqJukc8C5njs0gVEifk0q

issuer : http://localhost:9090/realms/springboot-microservice-realm

endpoint : http://localhost:9090/realms/springboot-microservice-realm/protocol/
openid-connect/token

-- TESTING --
request token 
![request-token.png](images%2Frequest-token.png)
test token 
![test-token.png](images%2Ftest-token.png)

test:
![img.png](img.png)


curl --location 'http://localhost:8080/swiggy/37jbd832' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJEUW0zM2dONjBvLUYxZm0xel9LcHdLTFRGUjZQQXIya05weGdrWV82OGVJIn0.eyJleHAiOjE3NzUxNDk5NzAsImlhdCI6MTc3NTE0OTY3MCwianRpIjoiNWE1MmI5NmUtZjQ1ZS00YjNhLTkyOWEtMGZjMTk5NjE1YTU1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDkwL3JlYWxtcy9zcHJpbmdib290LW1pY3Jvc2VydmljZS1yZWFsbSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJjNGIyYTFkZS1mNGZkLTQ5ODktYWM3Ny01NzdiYzE2ZGEyNGUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJtaWNyb3NlcnZpY2UtYXV0aCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgwIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXNwcmluZ2Jvb3QtbWljcm9zZXJ2aWNlLXJlYWxtIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUgb2ZmbGluZV9hY2Nlc3MiLCJjbGllbnRIb3N0IjoiMTcyLjI3LjAuMSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LW1pY3Jvc2VydmljZS1hdXRoIiwiY2xpZW50QWRkcmVzcyI6IjE3Mi4yNy4wLjEiLCJjbGllbnRfaWQiOiJtaWNyb3NlcnZpY2UtYXV0aCJ9.fM78V6nXo6U9P-bJaERAnR4CaF7Jaw2jHQ9QFDwfdRK3ZzZb1qn5ki6kPWh2Z3sbOMF89ow9em_Lzv4EMAF_Kb1SHLTqN8OSEh2Ada11T56Ad7yANAjj15cdl7m4i4gsVkD54C26pM_ZSi_8z__gGvMc61NE4MpYlgkWYmmYB21fg0RIudTs02m6HIrz7hqcAY97MVjNF4FXj853x8aMlpGypNWxPN6TRdFedkFhNQQ7xev6YOWdxhuF92OSqwuPRS3fRdR2GvHkKx3TkTG0-LL6QCpyXFLQv_DSaFnf7g2qieg8_PkTV16jwjYYgjamQl-AOYZJYwBfFBbgkkxlIA' \
--header 'Cookie: JSESSIONID=7CE91EE75A65277C0DCB6C5736C5DF5D'