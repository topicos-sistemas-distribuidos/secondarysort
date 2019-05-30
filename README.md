# secondarysort
A secondary sorting algorithm comparo between mapreduce and spark 

Features
---
1. Ordena palavras de arquivos texto de uma pasta de entrada.

Roteiro de execução
---
Faça a configuração do Cluster do Hadoop com 3 nós usando containers Docker disponível em https://github.com/topicos-sistemas-distribuidos/hadoop-cluster-docker

1. Vá até o nó master do hadoop. 

Obs: Inicie o hdfs e o yarn. É preciso garantir que o nó master tenha instalado o git, o maven e um editor de texto.  

2. Faça o clone desse repositório no nó master.
```
$ git clone https://github.com/topicos-sistemas-distribuidos/secondarysort.git
```

3. Execute o script de acordo com a feature compilada
```
$./my-sort.sh -sort
```

4. Output
```
sort output:
richard	Kingman
richard	Kingore
richard	Kingrey
richard	Kingry
richard	Kings
richard	Mathwich
richard	Mathys
richard	Matias
richard	Matice
richard	Matier
richard	Matin
richard	Matinez
richard	Matis
richard	Matise
richard	Matison
richard	Schultes
richard	Schultheis
richard	Schultheiss
richard	Schulthess
richard	Schultz
richard	Schultze
richard	Schulweis
richard	Schulz
richard	Schulze
```

Obs: Os scripts precisam de permissão para executar. Por exemplo: chmod +x my-sort.sh
