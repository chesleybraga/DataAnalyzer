package com.zallpy.challenge.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zallpy.challenge.builder.BuilderInterface;
import com.zallpy.challenge.comparator.SaleComparator;
import com.zallpy.challenge.factory.BuilderFactory;
import com.zallpy.challenge.util.Constants;
import com.zallpy.challenge.util.Util;
import com.zallpy.challenge.vo.Client;
import com.zallpy.challenge.vo.Sale;
import com.zallpy.challenge.vo.Salesman;

/**
 * @author Chesley Braga
 */
public class FileController {

    /**
     * Identifica todos os arquivos que estão na pasta e subpastas e os processa.
     *
     * @param directory pasta raiz que contem os arquivos
     */
    public void processDirectory(Path directory) {
	System.out.println("Processando pasta: " + directory);
	List<File> files = getFiles(directory);

	for (File eachFile : files) {
	    try {
		System.out.println("Processando arquivo: " + eachFile.getName());
		processFile(eachFile.toPath());
	    } catch (Exception e) {
		// Uma Exception ao processar um arquivo não deve interromper o processamento
		// dos demais
		System.err.println("ERRO - Não foi possivel processar o arquivo '" + eachFile + "'. Mensagem de Erro: "
			+ e.getMessage());
	    }
	}

	System.out.println();
    }

    /**
     * Identifica todos os arquivos que estão na pasta de forma recursiva
     *
     * @param directory pasta raiz que contem os arquivos
     * @return todos od arquivos presente na pasta raiz e em suas subpastas
     */
    protected List<File> getFiles(Path directory) {
	List<File> allFiles = new ArrayList<>();
	File[] files = directory.toFile().listFiles();

	for (File eachFile : files) {
	    try {
		if (eachFile.isDirectory()) {
		    allFiles.addAll(getFiles(eachFile.toPath()));
		} else if (validateExtension(eachFile.toPath())) {
		    allFiles.add(eachFile);
		}
	    } catch (Exception e) {
		// Uma Exception ao obter um arquivo não deve interromper a recuperacao dos
		// demais
		System.err.println("ERRO - Não foi possivel obter o arquivo '" + eachFile + "'. Mensagem de Erro: "
			+ e.getMessage());
	    }
	}

	return allFiles;
    }

    /**
     * Valida se a extensão do arquivo é suportada
     *
     * @param fullFileName nome do arquivo incluindo o caminho
     * @return true se a extensão do arquivo é suportada, caso contrario retorna
     *         false
     */
    private boolean validateExtension(Path fullFileName) {
	boolean isValid = false;

	for (String eachExtension : Constants.ACCEPTED_EXTENSIONS) {
	    if (fullFileName.toString().endsWith(eachExtension)) {
		isValid = true;
		break;
	    }
	}

	return isValid;
    }

    /**
     * procesa os arquivos
     *
     * @param fullFileName nome do arquivo incluindo o caminho
     * @throws IOException Caso ocorra algum erro ao ler as linhas do arquivo de
     *                     entrada ou criar ou escrever o arquivo de saida
     */
    private void processFile(Path fullFileName) throws IOException {
	Collection<?> vos = processInputFile(fullFileName);

	String fileNameOutput = buildOutputFileName(fullFileName.getFileName());
	processOutputFile(vos, fileNameOutput);
    }

    /**
     * Processa as linhas do arquivo para ojetos, facilitando o manuseio dos dados
     *
     * @param fullFileName nome do arquivo incluindo o caminho
     * @return as linhas do arquivo processadas como um objecto
     * @throws IOException Caso ocorra algum erro ao ler as linhas do arquivo de
     *                     entrada
     */
    private Collection<?> processInputFile(Path fullFileName) throws IOException {
	Collection<Object> vos = new ArrayList<>();
	List<String> lines = Files.readAllLines(fullFileName);

	for (int i = 0; i < lines.size(); i++) {
	    String eachLine = lines.get(i);

	    if (!Util.isEmpty(eachLine)) {
		try {
		    BuilderInterface<?> builder = BuilderFactory.getInstance().getBuilder(eachLine);
		    vos.add(builder.build(eachLine));
		} catch (Exception e) {
		    // Uma Exception em um arquivo não deve interromper o processamento dos demais
		    System.err.println("ERRO - Não foi processar a linha " + (i + 1) + "(Conteudo: " + eachLine
			    + "). Mensagem de Erro: " + e.getMessage());
		}
	    }
	}

	return vos;
    }

    /**
     * Constroi o nome do arquivo de saida baseado no nome do arquivo de entrada.
     *
     * É incluido a palavra ".done" antes da extensão do arquivo de entrada.
     *
     * @param fileNameInput nome do arquivo de entrada
     * @return nome do arquivo de saida
     */
    protected String buildOutputFileName(Path fileNameInput) {
	String fileNameOutput = null;
	String fileName = fileNameInput.toString();

	if (fileName.contains(Constants.EXTENSION_CHARACTER)) {
	    String extension = fileName.substring(fileName.lastIndexOf(Constants.EXTENSION_CHARACTER));
	    fileNameOutput = fileName.replace(extension, (Constants.EXTENSION_OUTPUT_PREFIX + extension));
	} else {
	    fileNameOutput = fileName + Constants.EXTENSION_OUTPUT_PREFIX;
	}

	return fileNameOutput;
    }

    /**
     * Cria um relatorio em arquivo de saida com informações obtidas do arquivo de
     * entrada
     *
     * @param vos          objetos criados a partir dos dados obtidos no arquivo de
     *                     entrada
     * @param fullFileName nome do arquivo de saida incluindo o caminho
     * @throws IOException Caso ocorra algum erro ao criar ou escrever o arquivo de
     *                     saida
     */
    private void processOutputFile(Collection<?> vos, String fileName) throws IOException {
	Collection<Client> clientes = new ArrayList<>();
	Collection<Salesman> vendedores = new ArrayList<>();
	ArrayList<Sale> vendas = new ArrayList<>();

	for (Object eachVO : vos) {
	    if (eachVO instanceof Client) {
		clientes.add((Client) eachVO);
	    } else if (eachVO instanceof Salesman) {
		vendedores.add((Salesman) eachVO);
	    } else if (eachVO instanceof Sale) {
		vendas.add((Sale) eachVO);
	    }
	}

	if (!Constants.PATH_OUTPUT.toFile().exists()) {
	    Constants.PATH_OUTPUT.toFile().mkdir();
	}
	Path fullFileName = Constants.PATH_OUTPUT.resolve(fileName);

	try (PrintWriter writer = new PrintWriter(fullFileName.toFile())) {
	    writer.println("Quantidade de Clientes: " + clientes.size());
	    writer.println("Quantidade de Vendedores: " + vendedores.size());

	    Sale biggestSale = null;
	    Sale lowestSale = null;
	    if (!Util.isEmpty(vendas)) {
		vendas.sort(new SaleComparator());
		biggestSale = vendas.get(vendas.size() - 1);
		lowestSale = vendas.get(0);
	    }
	    writer.println("ID da Venda Mais Cara: " + (biggestSale == null ? Constants.EMPTY : biggestSale.getId()));
	    writer.println("Pior Vendedor: " + (lowestSale == null ? Constants.EMPTY : lowestSale.getSalesmanName()));
	}
    }

    /**
     * Monitora a pasta para ler novos arquivos e processá-los.
     *
     * @param directory Pasta a ser monitorada
     * @param modifier  tipo de evento que será monitorado, por exemplo: criação,
     *                  modificação e exclusão
     * @throws IOException Caso ocorra algum erro ao monitorar a pasta
     */
    public void watchDirectory(Path directory, WatchEvent.Kind<Path> modifier) throws IOException {
	WatchService watcher = FileSystems.getDefault().newWatchService();
	directory.register(watcher, modifier);
	System.out.println("Monitorando pasta: " + directory);

	while (true) {
	    WatchKey key;

	    try {
		key = watcher.take();
	    } catch (InterruptedException ie) {
		System.err.println("InterruptedException: " + ie);
		return;
	    }

	    for (WatchEvent<?> event : key.pollEvents()) {
		if (StandardWatchEventKinds.OVERFLOW == event.kind()) {
		    // OVERFLOW event can occur regardless if events are lost or discarded
		    continue;
		}

		try {
		    Path fileName = (Path) event.context();
		    Path fullFileName = directory.resolve(fileName);

		    if (fullFileName.toFile().isDirectory()) {
			System.err.println("Não é possivel monitorar arquivos de subpastas (" + fileName + ")");
		    } else if (validateExtension(fullFileName)) {
			System.out.println("Processando arquivo: " + fileName);
			processFile(fullFileName);
		    } else {
			System.err.println("A extensão do arquivo '" + fileName + "' não é valida "
				+ Constants.ACCEPTED_EXTENSIONS);
		    }
		} catch (IOException ioe) {
		    System.err.println("IOException: " + ioe);
		}
	    }

	    if (!key.reset()) {
		break;
	    }
	}
    }
}