# Systems Integration Project
 Systems Integration Project
 
## Objetivo geral
O primeiro projeto, da Unidade Curricular de Integração de Sistemas, consiste na implementação de uma aplicação que manipule um conjunto de dados, presentes num ficheiro no formato Extensible Markup Language (XML), e os apresente numa página no formato Hypertext Markup Language (HTML). Com isto, é espectável que os alunos explorem um conjunto de ferramentas e recursos – XML, XML Schema (XSD) e XSLT – adquirindo assim novas competências relativas às diversas tecnologias XML.


## Enquadramento
Foi desenvolvida uma aplicação, em linguagem Java, que manipula um
ficheiro XML que contem a informação de um conjunto de livros. O utilizador
pode efetuar um conjunto de pesquisas de modo a filtrar um conjunto de livros
através de uma interface texto na consola. Após a pesquisa, o conteúdo
resultante da mesma é processado e reorganizado. Por fim, é gerado um ficheiro
HTML que apresenta os livros resultantes da pesquisa e do processamento.

O projeto pode ser divido em três blocos fundamentais. 

Numa primeira
parte, a classe Selector que começa por transformar a informação presente no
ficheiro XML em objetos Java (Unmarshal) seguindo restrições, como o tipo de
dados, que são asseguradas por um ficheiro XSD. Neste bloco é onde o
utilizador interage com o sistema, efetuando uma pesquisa, de modo a
selecionar um conjunto de livros. O Selector trata a pesquisa e por fim, escreve
o resultado da pesquisa num novo ficheiro XML (Marshal). Na eventualidade de
não ser devolvido nenhum livro após a pesquisa, o utilizador recebe uma
mensagem de aviso na consola.

Consequentemente, numa segunda parte, a classe Processor começa por
ler a informação escrita no novo ficheiro XML gerado e reorganiza-a num novo
formato, gerando outro ficheiro XML4. Este documento estrutura-se seguindo
uma lista de autores, onde cada um destes possuí uma lista de livros ordenados
pelo ranking de bestseller. Para além disso, são adicionadas algumas
informações estatísticas como o número de autores processados pela classe
Processor e o nome de um número N de autores. Este, número N, pode ser
manipulado pelo utilizador através da passagem por argumento para o método
main ou no caso de isto não acontecer, o número é explicitamente solicitado ao
utilizador no início da execução da classe Processor.


O último e terceiro bloco do projeto, a classe HTMLViewer, transforma, o
ficheiro XML devolvido pelo Processor, no formato HTML recorrendo a um
ficheiro XSL. Para que esta transformação ocorra, foi criado um XSL no qual é
aplicado um template que permite ver todos os autores e as suas respetivas
obras resultantes da pesquisa inicial. Cada livro contém informação, como por
exemplo, a data de publicação, o ranking atribuído pelos leitores, a categoria
literária, entre outros atributos. Para obter esta informação utilizou-se
unicamente XPath, que permite navegar nos elementos e atributos de um ficheiro
XML.
