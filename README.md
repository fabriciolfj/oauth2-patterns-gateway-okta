# Spring Gateway com OKTA

## Introdução

OpenID Connect define um mecanismo de autenticação do utilizador final com base no fluxo de código de autorização OAuth2. Neste padrão, o servidor de autorização devolve um código à aplicação, que pode então trocá-lo diretamente por um token de id e um token de acesso. O Servidor de autorização autentica a aplicação com um clientId e clientSecret antes de a troca acontecer.

Para configurar uma aplicação no okta, sugiro o artigo https://developer.okta.com/blog/2019/05/15/spring-boot-login-options.

Para subir o gateway da aplicação, faça uso das variáveis de ambiente:

```
OKTA_OAUTH2_ISSUER={yourOktaIssuer} \
OKTA_OAUTH2_CLIENT_ID={yourOktaClientId} \
OKTA_OAUTH2_CLIENT_SECRET={yourOktaClientSecret} \
```
