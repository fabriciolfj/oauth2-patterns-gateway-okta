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

Para gerar o token, apenas acesse: http://localhost:8080/greeting.

###### Detalhes da implementação
- O ribbon foi desabilitado para que ReactorLoadBalancer funcione.
- Usamos o scope customizado "pricing".
- Spring boot auto configurou a aplicação como client oauth2, devido ao client.registration presente no arquivo application.yaml.

###### Exemplos de requisições:

export ACCESS_TOKEN={accessToken}
``` 
#Adicionando um cart:
curl   -d '{"customerId": "fabricio@live.com", "lineItems": [{ "productName": "camisa", "quantity": 3}]}' -H "Authorization: Bearer ${ACCESS_TOKEN}" -H 'Content-Type: application/json' -H 'Accept: application/json' http://localhost:8080/cart

#Consultando um cart:
curl -H 'Accept: application/json' -H "Authorization: Bearer ${ACCESS_TOKEN}" http://localhost:8080/cart/1

#Adicionando preço aos itens
curl  -d '{"customerId": "fabricio@live.com", "lineItems": [{ "productName": "jeans", "quantity": 1}]}' -H 'Content-Type: application/json' -H 'Accept: application/json' http://localhost:8082/pricing/price
``` 
