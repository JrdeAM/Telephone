# Implementação do padrão Observer - Telephone

## Objetivo do Projeto

O projeto consiste na implementação de um telefone utilizando o padrão de projeto **Observer**.

O objetivo inicial era permitir que o teclado (`KeyPad`) inserisse novos dígitos no modelo do telefone (`PhoneModel`), porém o sistema não possuía nenhuma forma de avisar outros componentes quando uma alteração acontecia.

Durante a implementação, o projeto foi modificado para que objetos interessados nas mudanças do telefone pudessem ser notificados automaticamente.

A implementação adicionou:

- uma interface para definir observadores;
- registro de observadores no modelo do telefone;
- notificação automática após inserção de novos dígitos;
- múltiplos observadores na interface gráfica;
- exibição do último dígito inserido;
- exibição do número completo para discagem.

---

# Adicionar interface para observadores do telefone

Nesta primeira etapa foi criada a estrutura inicial para utilização do padrão **Observer**.

O problema inicial do projeto era que a classe `PhoneModel` armazenava os números digitados, mas não possuía nenhuma forma de avisar outros objetos quando seu estado era alterado.

Para resolver esse problema foi criada a interface:

- `PhoneObserver.java`

Essa interface define o comportamento que todos os observadores do telefone devem possuir.

Foi criado o método:

```java
void update();
```

---

## Funcionamento

O método `update()` representa uma ação que será executada sempre que o modelo do telefone sofrer alguma alteração.

A estrutura inicial passou a funcionar da seguinte forma:

```
PhoneObserver

      |

      |

Classes interessadas nas alterações do telefone
```

---

A criação dessa interface permitiu que diferentes componentes pudessem observar o modelo sem criar uma dependência direta entre as classes.

---

# Implementar Screen como observador do modelo do telefone

Nesta etapa a classe `Screen` foi modificada para implementar a interface criada anteriormente.

Antes da alteração, a tela apenas possuía uma referência para o modelo do telefone, mas não recebia nenhuma atualização automática.

Após a alteração, a classe passou a implementar:

```java
implements PhoneObserver
```

---

## Alteração realizada

Foi implementado o método:

```java
update()
```

Esse método é executado sempre que o `PhoneModel` informa que houve uma mudança.

---

## Novo funcionamento

O fluxo passou a ser:

```
PhoneModel

      |

      ↓

Notifica PhoneObserver

      |

      ↓

Screen recebe atualização
```

---

Com essa alteração, a tela passou a possuir o comportamento necessário para acompanhar as alterações realizadas no número de telefone.

---

# Adicionar registro e notificação de observadores no PhoneModel

Nesta etapa o modelo do telefone foi alterado para gerenciar seus observadores.

Antes da alteração, o `PhoneModel` apenas armazenava os dígitos inseridos.

O funcionamento era:

```
KeyPad

   ↓

PhoneModel

   ↓

Armazena dígito
```

Nenhum componente era informado sobre a mudança.

---

## Alterações realizadas

Foi adicionada uma lista de observadores:

```java
List<PhoneObserver>
```

Essa lista é responsável por armazenar todos os objetos interessados nas alterações do modelo.

---

## Método addObserver()

Foi criado o método:

```java
addObserver(observer)
```

Esse método permite registrar novos observadores no modelo.

Exemplo:

```java
model.addObserver(screen);
```

---

## Método notifyObservers()

Foi criado o método:

```java
notifyObservers()
```

Sua responsabilidade é percorrer todos os observadores registrados e executar:

```java
observer.update();
```

---

## Novo fluxo do sistema

```
PhoneModel

      |

      ↓

Lista de observadores

      |

      ↓

Executa update()
```

---

# Notificar observadores após inserção de novos dígitos

Nesta etapa foi realizada a integração entre a inserção dos números e a atualização dos observadores.

O método responsável por adicionar novos dígitos:

```java
addDigit()
```

foi alterado.

---

## Antes da alteração

O funcionamento era:

```
Novo dígito

      ↓

Adicionar na lista
```

---

## Depois da alteração

O fluxo passou a ser:

```
Novo dígito

      ↓

Adicionar na lista

      ↓

Notificar observadores

      ↓

Atualizar componentes interessados
```

---

## Resultado

Sempre que o teclado insere um novo número, a tela recebe uma atualização automaticamente.

Exemplo:

```
Pressing: 5

Número atual: [5]
```

Depois:

```
Pressing: 8

Número atual: [5, 8]
```

---

Essa alteração implementou o comportamento principal do padrão Observer:

O objeto observado (`PhoneModel`) informa automaticamente todos os objetos interessados quando seu estado muda.

---

# Implementar múltiplos observadores na Screen para exibir último dígito e número completo

Nesta etapa a responsabilidade da classe `Screen` foi ampliada.

Em vez de ser apenas um observador, a interface passou a criar dois observadores independentes.

A própria `Screen` passou a registrar esses observadores no modelo.

---

# Primeiro observador

## Responsabilidade

Exibir somente o último dígito inserido pelo usuário.

Quando um novo número é pressionado, esse observador consulta o modelo e recupera apenas o valor mais recente.

Exemplo:

```
Pressing: 8

8
```

---

# Segundo observador

## Responsabilidade

Montar o número completo armazenado no modelo e exibir a mensagem de discagem.

Exemplo:

```
Agora discando 081999887766...
```

---

## Novo funcionamento

O fluxo passou a ser:

```
                PhoneModel

                     |

          ------------------------

          |                      |

  Observador 1              Observador 2

 Último dígito             Número completo

```

---

Dessa forma, uma mesma alteração no modelo consegue atualizar diferentes comportamentos da interface.

---

# Ajustar geração de dígitos para valores válidos de telefone

Nesta etapa foi realizada uma correção na geração dos números pelo teclado.

Inicialmente o `KeyPad` permitia valores fora do intervalo de um telefone.

O sistema poderia gerar:

```
10
11
```

Porém um telefone utiliza apenas:

```
0 até 9
```

---

## Alteração realizada

A geração dos números foi ajustada para utilizar somente valores válidos:

```java
Random.nextInt(10);
```

---

## Resultado

Agora os números gerados representam corretamente um telefone.

Exemplo:

```
Pressing: 0

0

Pressing: 8

8

Pressing: 1

1

...

Agora discando 081999887766...
```

---

# Resultado Final

Após todas as etapas, o projeto Telephone passou a utilizar corretamente o padrão **Observer**.

A implementação final permite:

- criar diferentes observadores para o modelo do telefone;
- registrar componentes interessados nas alterações;
- atualizar automaticamente a interface após novas entradas;
- criar múltiplos comportamentos de visualização;
- separar o modelo da lógica de apresentação.

O fluxo final do sistema ficou:

```
KeyPad

    ↓

PhoneModel

    ↓

Notifica observadores

    ↓

---------------------

|                   |

Screen Observador 1  Screen Observador 2

Último dígito        Número completo

```

A utilização do padrão Observer tornou o projeto mais organizado, flexível e preparado para adicionar novos componentes que dependam das alterações do telefone.