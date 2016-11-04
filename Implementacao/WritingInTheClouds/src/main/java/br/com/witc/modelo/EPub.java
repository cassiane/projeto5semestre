/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.zeroturnaround.zip.ZipUtil;

/**
 *
 * @author marcelo.lima
 */
public class EPub {

    private String pathEpub;
    private Livro livro;

    /**
     * @return the pathEpub
     */
    public String getPathEpub() {
        return pathEpub;
    }

    /**
     * @param pathEpub the pathEpub to set
     */
    public void setPathEpub(String pathEpub) {
        this.pathEpub = pathEpub;
    }

    /**
     * @return the livro
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    private byte[] gerarEpub() throws FileNotFoundException, IOException {
        String pathBook = this.pathEpub + this.livro.getId() + "\\";
        String fileNameEpub = "livro_" + this.livro.getId() + ".epub";
        String fileNameZip = "livro_" + this.livro.getId() + ".zip";        
        if (!Files.exists(Paths.get(this.pathEpub + fileNameEpub))) {
            new File(this.pathEpub + this.livro.getId()).mkdirs();
        } else {
            // epub jah criado
            InputStream is = new FileInputStream(this.pathEpub + fileNameEpub);
            return IOUtils.toByteArray(is);
            //return this.pathEpub + fileNameEpub;
        }

        // Mimetype
        List<String> lines = new ArrayList();
        lines.add("application/epub+zip");
        Path file = Paths.get(pathBook + "mimetype");
        Files.write(file, lines, Charset.forName("UTF-8"));

        //META_INF
        String meta = pathBook + "META-INF\\";
        new File(meta).mkdir();

        // Container.xml
        lines = new ArrayList();
        lines.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        lines.add("<container version=\"1.0\" xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\">");
        lines.add("<rootfiles>");
        lines.add("<rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/>");
        lines.add("</rootfiles></container>");
        file = Paths.get(meta + "container.xml");
        Files.write(file, lines, Charset.forName("UTF-8"));

        //OEBPS
        String oebpsPath = pathBook + "OEBPS\\";
        new File(oebpsPath).mkdir();

        // content.opf
        lines = new ArrayList();
        lines.add("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
        lines.add("<package unique-identifier=\"p9789892052366\" version=\"2.0\" xmlns=\"http://www.idpf.org/2007/opf\">");

        // metadata        
        lines.add("<metadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:opf=\"http://www.idpf.org/2007/opf\">");
        lines.add("<dc:identifier id=\"" + livro.getId() + "\">" + livro.getId() + "</dc:identifier>");
        lines.add("<dc:title>" + this.livro.getTitulo() + "</dc:title>");
        for (String[] autor : this.livro.getLstNomesCompletosAutores()) {
            lines.add("<dc:creator opf:role=\"aut\" opf:file-as=\"" + autor[1].toUpperCase()
                    + ", " + autor[0] + "\">" + autor[0].substring(0, 1).toUpperCase()
                    + autor[0].substring(1).toLowerCase() + " " + autor[1] + "</dc:creator>");
        }        
        lines.add("<dc:language>pt-br</dc:language>");

        // manifest
        lines.add("<manifest>");
        lines.add("<item href=\"toc.ncx\" id=\"ncx\" media-type=\"application/x-dtbncx+xml\"/>");
        // for each capitulo ...        
        lines.add("<item href=\"epub.html\" id=\"conteudo\" media-type=\"application/xhtml+xml\"/>");        
        // <!– CSS Style Sheets –>
        // <item href="Styles/stylesheet.css" id="css" media-type="text/css"/>        
        lines.add("</manifest>");

        // spine
        lines.add("<spine toc=\"ncx\">");
        // em idref usar o id do manifesto
        lines.add("<itemref idref=\"conteudo\" linear=\"yes\"/>");
        lines.add("</spine>");
        
        // guide
        /*
        lines.add("<guide>");
        lines.add("<reference href=\"Text/toc.html\" title=\"Table of Contents\" type=\"toc\"></reference>");
        lines.add("</guide>");
        */
        lines.add("</package>");
        file = Paths.get(oebpsPath + "content.opf");
        Files.write(file, lines, Charset.forName("UTF-8"));

        // toc.ncx
        lines = new ArrayList();

        lines.add("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
        lines.add("<!DOCTYPE ncx PUBLIC \"-//NISO//DTD ncx 2005-1//EN\"\n" +
            " \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\"><ncx version=\"2005-1\" xmlns=\"http://www.daisy.org/z3986/2005/ncx/\">");        
        lines.add("<head>");
        lines.add("<meta content=\"" + livro.getId() + "\" name=\"dtb:uid\"/>");
        // niveis de sumario
        lines.add("<meta content=\"4\" name=\"dtb:depth\"/>");
        lines.add("<meta name=\"dtb:totalPageCount\" content=\"0\"/>");
        lines.add("<meta name=\"dtb:totalPageCount\" content=\"0\"/>");
        lines.add("<meta name=\"dtb:maxPageNumber\" content=\"0\"/>");
        lines.add("</head>");
        lines.add("<docTitle>");
        lines.add("<text>" + livro.getTitulo() + "</text>");
        lines.add("</docTitle>");
        /*
        for (String[] autor : this.livro.getLstNomesCompletosAutores()) {
            lines.add("<docAuthor>");
            lines.add("<text>" + autor[0] + " " + autor[1] + "</text>");
            lines.add("</docAuthor>");
        }
        */
        lines.add("<navMap>");        
        // criar para cada capitulo
        lines.add("<navPoint id=\"navPoint-1\" playOrder=\"1\">");
        lines.add("<navLabel>");
        lines.add("<text>Conteúdo</text>");
        lines.add("</navLabel>");
        lines.add("<content src=\"epub.html\"/>");
        lines.add("</navPoint>");

        lines.add("</navMap>");
        lines.add("</ncx>");

        file = Paths.get(oebpsPath + "toc.ncx");
        Files.write(file, lines, Charset.forName("UTF-8"));

        // capitulos - deve-se criar um arquivo para cada capitulo
        lines = new ArrayList();
        lines.add("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        lines.add("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n" +
                "\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
        lines.add("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        lines.add("<head>");
        lines.add("<meta content=\"width=600, height=1024\" name=\"viewport\"/>");
        lines.add("<title>Conteúdo</title>");
        //lines.add("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />");
        lines.add("</head><body><div><h3>" + livro.getTitulo() + "</h3><br />");
        lines.add(livro.getTexto());
        lines.add("</div></body></html>");

        file = Paths.get(oebpsPath + "epub.html");
        Files.write(file, lines, Charset.forName("UTF-8"));

        ZipUtil.pack(new File(this.pathEpub + this.livro.getId()),
                new File(this.pathEpub + fileNameZip));

        new File(this.pathEpub + fileNameZip)
                .renameTo(new File(this.pathEpub + fileNameEpub));

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
        }

        // deleta o diretorio
        FileUtils.deleteDirectory(new File(pathBook));

        InputStream is = new FileInputStream(this.pathEpub + fileNameEpub);
        return IOUtils.toByteArray(is);
        //return this.pathEpub + fileNameEpub;
    }

    public byte[] downloadEPub() throws IOException {
        return gerarEpub();
    }
}
