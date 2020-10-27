# DataAnalyzer

O sistema analisa dados em um formato preestabelecido nos arquivos de texto simples presentes em um determinado diretório de entrada e gera um relatório em um diretório de saída.

### Pré-requisitos

1. Deve existir o diretório de entrada `HOME_DIRECTORY\data\in\` onde **HOME_DIRECTORY** é o diretório padrão do usuário no sistema operacional.

| Sistema Operacional | Caminho                     | Variável de Ambiente                   | Exemplo          |
| ------------------- | --------------------------- | -------------------------------------- | ---------------- |
| Windows 7, 8 e 10   | \<root\>\Users\\<username\> | %USERPROFILE% ou %HOMEDRIVE%%HOMEPATH% | C:\Users\chesley |
| macOS               | /Users/\<username\>         | $HOME                                  | /Users/chesley   |
| Linux (Maioria)     | /home/\<username\>          | $HOME                                  | /home/chesley    |

2. Os arquivos devem ser documentos de texto simples, respeitarem as definições (descritas abaixo), terem a extensão `.bat` e serem incluídos no diretório de entrada (descrito acima).

### Definições

1. Existem 3 tipos de dados: `Vendedor, Cliente e Venda`
   
   Eles são definidos por um código no início de cada linha:
   - 001 para Vendedor
   - 002 para Ciente
   - 003 para Venda
   
   As demais linhas que não comecem com os códigos acima serão desconsideradas na análise.

2. Será usado o caractere `ç` para identificar e separar os campos de cada tipo de dado.

3. Campos:

   3.1. Vendedor
   | Ordem | Campo         | Descrição                           | 
   | ----- | ------------- | ----------------------------------- |
   | 01    | Code          | Código que define o tipo do dado    |
   | 02    | CPF           | Código CPF do vendedor              |
   | 03    | Name          | Nome do vendedor                    |
   | 04    | Salary        | Salario do vendedor                 |


   3.2. Cliente
   | Ordem | Campo         | Descrição                           |
   | ----- | ------------- | ----------------------------------- |
   | 01    | Code          | Código que define o tipo do dado    |
   | 02    | CNPJ          | Código CNPJ do cliente              |
   | 03    | Name          | Nome do cliente                     |
   | 04    | Business Area | Nome da área de negócios do cliente |
   
   
   3.3. Venda
   | Ordem | Campo         | Descrição                           |
   | ----- | ------------- | ----------------------------------- |
   | 01    | Code          | Código que define o tipo do dado    |
   | 02    | ID            | Código da compra                    |
   | 03    | Items **`*`** | Itens comprados                     |
   | 04    | Salesman Name | Nome do vendedor                    |
   
   **`*`**: O texto recuperado para esse campo deve estar envolto por colchetes e dar origem a uma lista de itens, será usado o caractere `,` para separar os itens e o caractere `-` para separar os campos desse item.
   | Ordem | Campo         | Descrição                           |
   | ----- | ------------- | ----------------------------------- |
   | 01    | ID            | Código do item                      |
   | 02    | Quantity      | Quantidade do item                  |
   | 03    | Price         | Preço do item                       |

4. O relatório será criado em um arquivo no diretório de saída em `HOME_DIRECTORY\data\out\` com o mesmo nome do arquio de entrada mas sendo incluído o prefixo `.done` imediatamente antes a extensão.

5. O sistema ao ser executado irá analisar todos os arquivos existentes no diretório de entrada e também ficará monitorando o diretório de entrada para analisar os novos arquivos incluídos.

### Exemplos:

   Arquivo de entrada: `dados.bat`
   ```
   001ç1234567891234çPedroç50000
   001ç3245678865434çPauloç40000.99
   002ç2345675434544345çJose da SilvaçRural
   002ç2345675433444345çEduardo PereiraçRural
   003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
   003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
   ```
   
   Arquivo de saída: `dados.done.bat`
   ```
   Quantidade de Clientes: 2
   Quantidade de Vendedores: 2
   ID da Venda Mais Cara: 10
   Pior Vendedor: Paulo
   ```

### Build e Execução (Usando Eclipse)

1. Criar o diretório de entrada

2. Baixe o projeto

3. Importe o projeto no Eclipse

4. Localize e abra a classe `com.zallpy.challenge.Initiator.java`

5. Execute o Launcher(Run) ou tecle o atalho `CRTL+F11`

6. Inclua o arquivo no diretório de entrada
