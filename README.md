# Análise dos Princípios SOLID

## Princípio de Responsabilidade Única (SRP)

**ClienteFactory Interface**
- **Arquivo**: `tech.ada.banco.factory.ClienteFactory`
- **Linha**: 3
- **Descrição**: A interface `ClienteFactory` define um único método `criarCliente()`, separando a responsabilidade de criação de clientes em uma interface específica, seguindo o SRP ao evitar que a lógica de criação seja misturada com outras funcionalidades.

## Princípio Aberto/Fechado (OCP)

**Implementação de ClientePFFactory**
- **Arquivo**: `tech.ada.banco.factory.ClientePFFactory`
- **Linha**: 7
- **Descrição**: A classe `ClientePFFactory` implementa a interface `ClienteFactory` e fornece a implementação específica para criar instâncias de `ClientePF`. A classe está aberta para extensão (novas fábricas de clientes podem ser adicionadas), mas fechada para modificação, uma vez que a interface `ClienteFactory` permanece inalterada.

## Princípio de Substituição de Liskov (LSP)

**Método criarCliente em ClientePFFactory**
- **Arquivo**: `tech.ada.banco.factory.ClientePFFactory`
- **Linha**: 8
- **Descrição**: O método `criarCliente` em `ClientePFFactory` retorna um objeto `ClientePF`, que é uma subclasse de `Cliente`. Isso segue o LSP, permitindo que objetos `ClientePF` sejam usados em qualquer lugar que espera-se um objeto do tipo `Cliente`.

## Princípio da Segregação de Interface (ISP)

**Segregação de ClientePFDTO e ClientePJDTO**
- **Arquivo**: `tech.ada.banco.dto.ClientePFDTO` e `tech.ada.banco.dto.ClientePJDTO`
- **Linhas**: `tech.ada.banco.dto.ClientePFDTO` (15-28) e `tech.ada.banco.dto.ClientePJDTO` (11-20)
- **Descrição**: As classes `ClientePFDTO` e `ClientePJDTO` segregam as interfaces específicas para clientes físicos e jurídicos, evitando uma interface monolítica que não atende de forma eficiente às necessidades específicas de cada tipo de cliente.

## Princípio da Inversão de Dependência (DIP)

**Injeção de Dependências no ClienteController**
- **Arquivo**: `tech.ada.banco.controller.ClienteController`
- **Linha**: 20
- **Descrição**: A classe `ClienteController` usa injeção de dependências para o serviço `ClientePFService` e o mapeador `ModelMapper`, demonstrando o princípio DIP. Em vez de instanciar diretamente essas dependências, o controle das instâncias é invertido e gerenciado pelo framework de injeção de dependências, como Spring.

## Padrões de Design Utilizados

### 1. Factory Method
- **Caminho da Classe**: `tech.ada.banco.factory.ClientePFFactory`
- **Linha do Código**: 7
- **Descrição**: A classe `ClientePFFactory` implementa o método `criarCliente()` da interface `ClienteFactory`, fornecendo uma implementação específica para criar instâncias de `ClientePF`. Este padrão permite a criação de objetos sem especificar a classe exata dos objetos que serão criados.

### 2. Abstract Factory
- **Caminho da Classe**: `tech.ada.banco.factory.ClienteFactory`
- **Linha do Código**: 3
- **Descrição**: A interface `ClienteFactory` atua como uma Abstract Factory, definindo um conjunto de métodos para criar diferentes tipos de clientes (`ClientePF` e `ClientePJ`). Isso permite a criação de famílias de objetos relacionados sem depender de suas classes concretas.

### 3. Builder
- **Caminho da Classe**: `tech.ada.banco.usuario.UsuarioBuilder`
- **Linha do Código**: 3
- **Descrição**: A classe `UsuarioBuilder` segue o padrão Builder para facilitar a criação de instâncias de `Usuario`, proporcionando uma maneira clara e fluente de configurar e instanciar objetos complexos.