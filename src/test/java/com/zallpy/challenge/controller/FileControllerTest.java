package com.zallpy.challenge.controller;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Chesley Braga
 */
public class FileControllerTest {

    private FileController controller = new FileController();

    @Test
    public void getFilesTest() throws Exception {
	Path pathRootDirectory = Mockito.mock(Path.class);
	File fileRootDirectory = Mockito.mock(File.class);
	Mockito.when(pathRootDirectory.toFile()).thenReturn(fileRootDirectory);

	File fileDirectory = Mockito.mock(File.class);
	Path pathDirectory = Mockito.mock(Path.class);
	Mockito.when(fileDirectory.isDirectory()).thenReturn(Boolean.TRUE);
	Mockito.when(fileDirectory.toPath()).thenReturn(pathDirectory);

	File fileAux = Mockito.mock(File.class);
	Mockito.when(pathDirectory.toFile()).thenReturn(fileAux);

	File fileTXT = Mockito.mock(File.class);
	Path pathFileTXT = Mockito.mock(Path.class);
	Mockito.when(fileTXT.toPath()).thenReturn(pathFileTXT);
	Mockito.when(pathFileTXT.toString()).thenReturn("pathFile.txt");

	File[] filesAux = { fileTXT };
	Mockito.when(fileAux.listFiles()).thenReturn(filesAux);

	File fileBAT = Mockito.mock(File.class);
	Path pathFileBAT = Mockito.mock(Path.class);
	Mockito.when(fileBAT.toPath()).thenReturn(pathFileBAT);
	Mockito.when(pathFileBAT.toString()).thenReturn("pathFile.bat");

	File[] files = { fileDirectory, fileBAT };
	Mockito.when(fileRootDirectory.listFiles()).thenReturn(files);

	List<File> actual = controller.getFiles(pathRootDirectory);
	Assert.assertTrue(actual != null);
	Assert.assertTrue(actual.size() == 1);
    }

    @Test
    public void buildOutputFullFileNameTest() {
	Path fileName = Mockito.mock(Path.class);
	Mockito.when(fileName.toString()).thenReturn("fileName.bat");

	String actual = controller.buildOutputFileName(fileName);
	Assert.assertTrue(actual != null);

	String expected = "fileName.done.bat";
	Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void buildOutputFullFileNameWithoutExtensionTest() {
	Path fileName = Mockito.mock(Path.class);
	Mockito.when(fileName.toString()).thenReturn("fileName");

	String actual = controller.buildOutputFileName(fileName);
	Assert.assertTrue(actual != null);

	String expected = "fileName.done";
	Assert.assertEquals(expected, actual.toString());
    }
}