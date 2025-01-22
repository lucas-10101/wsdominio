# Web Service: Dominio

### Descrição do projeto

Voltado para o controle de usuários, empresas e objetos do locatário de nível global referenciados por outros serviços que compõem a aplicação
A aplicação conta com os seguintes objetos de domínio:

|Entidade|Função|
|-|-|
|Dominio|Contém informações gerais sobre os locatários e suas capacidades dentro do sistema, limites, cotas e configurações utilizadas pela aplicação
|Usuario|O usuário da aplicação em si|
|Empresa|Informações e configurações sobre a empresa matriz e suas filiais do locatário
|FuncaoAcesso|Representa as funções das quais um usuário pode ser atribuido para acesso ao sistema
|GrupoAcesso|Grupos que permitem re-utilizar funções de acesso em grupos e atribuir a usuários por empresa

As permissões de grupos ou usuários são atribuidas ao funcionário e empresa, portanto existe o isolamento de acesso entre filiais para um controle mais granular dos acessos

Esta aplicação é um modelo inicial e simplificado dos controles distribuidos entre outros serviços (aplicação baseada em arquitetura de serviços "SOA")

### Recursos de OIDC e geração de tokens de acesso

Esta aplicão possui as informações principais relacionadas ao usuário e suas dependências, porém a geração de tokens e implementação do padrão OIDC é delegado a ferramentas mais robustas e especificas para esta funcionalidade, dentre as implementações possíveis estão:

+ Keycloak
+ AWS Cognito

### Recursos Spring Framework

+ Spring Data JDBC: Uso das classes JDBCTemplate para acesso a dados
+ Spring Security: Configurações de acesso, segurança e identidade na aplicação
+ Spring Web: Para controladores e métodos de ponto para comunicação com outros serviços em XML/JSON

Outros recursos também podem ter sido utilizados durante o desenvolvimento como suporte a aplicação em outros aspectos e podem ser adicionados a depender de sua relevancia.

### UML

Uma versão do diagrama de UML já esta preparada e estar disponível em versões futuras


# Autor do projeto: [lucas-10101](https://github.com/lucas-10101)
### Licenciamento

Este projeto não inclui licença, pois por definição, não é feito para licenciamento e é de propriedade exclusiva.

# Como executar

Junto a esta aplicação é adicionado um arquivo docker para execução de um container ou também poderá ser executado a partir dos arquivos locais em formato JAR