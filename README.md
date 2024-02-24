# Stock App

O Stock App é um aplicativo Android que permite aos usuários acompanhar informações sobre empresas e ações. Ele utiliza várias bibliotecas e tecnologias para fornecer uma experiência completa. Aqui estão os principais componentes do aplicativo:

## Funcionalidades

1. **Listagem de Empresas e Ações**: O aplicativo permite que os usuários visualizem uma lista de empresas e suas ações. Os dados são obtidos por meio de uma API usando o Retrofit e o Moshi.

2. **Pesquisa de Empresas**: Os usuários podem pesquisar empresas específicas pelo nome ou símbolo.

3. **Detalhes da Empresa e Gráficos**: Ao selecionar uma empresa, os usuários podem ver detalhes específicos, como preço das ações, volume de negociação e gráficos de desempenho. Os gráficos são renderizados usando o Canvas do Jetpack Compose.

4. **Conversor de Arquivos CSV**: O aplicativo também inclui um conversor de arquivos CSV. Os dados retornados pela API podem ser exportados para um arquivo CSV para análise posterior.

5. **Armazenamento em Cache com Room**: Para evitar chamadas excessivas à API, o aplicativo armazena os dados em cache usando a biblioteca Room. Isso permite que os usuários acessem informações offline.

## Tecnologias Utilizadas

- **Retrofit**: Biblioteca para fazer chamadas à API.
- **Moshi**: Biblioteca para converter JSON em objetos Kotlin.
- **OkHttp**: Cliente HTTP para Retrofit.
- **Jetpack Compose**: Biblioteca para criar interfaces de usuário declarativas.
- **Canvas do Jetpack Compose**: Para renderizar gráficos.
- **Room**: Biblioteca para armazenamento em cache local.
- **Junit e MockWebServer**: Para testes unitários e simulação de chamadas à API.
