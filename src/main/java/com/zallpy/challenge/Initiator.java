package com.zallpy.challenge;

/**
 * @author Chesley Braga
 */
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;

import com.zallpy.challenge.controller.FileController;
import com.zallpy.challenge.util.Constants;

public class Initiator {

    public static void main(String[] args) throws Exception {
	initiate();
    }

    public static void initiate() throws Exception {
	FileController controller = new FileController();
	Path inputPath = Constants.PATH_INPUT;

	// processa os arquivos que já estão em uma pasta e suas subpastas
	controller.processDirectory(inputPath);

	// monitora uma pasta e processa os arquivos ao serem adicionados
	controller.watchDirectory(inputPath, StandardWatchEventKinds.ENTRY_CREATE);
    }
}