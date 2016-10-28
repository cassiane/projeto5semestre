/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.witc.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private void gerarEpub() throws FileNotFoundException, IOException  {
        Path pathDir = Paths.get(this.pathEpub);
        if (!Files.exists(pathDir)) {
            new File(this.pathEpub).mkdirs();
        } else {
            // estrutura epub jah criada
            return;
        }
                         
        // Mimetype
        List<String> lines = new ArrayList();
        lines.add("application/epub+zip");
        Path file = Paths.get(this.pathEpub + "/mimetype");
        Files.write(file, lines, Charset.forName("UTF-8"));
        
        //META_INF
        String meta = this.pathEpub + "/META-INF";
        new File(meta).mkdir();
        
        // Container.xml
        lines = new ArrayList();
        lines.add("[cc escaped=\"true\"  lang=\"xml\"]");
        lines.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        lines.add("<container xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\" version=\"1.0\">");
        lines.add("<rootfiles>");
        lines.add("<rootfile full-path=\"OEBPS/content.opf\" media-type=\"application/oebps-package+xml\"/>");
        lines.add("</rootfiles></container>");
        lines.add("[/cc]");
        file = Paths.get(meta + "/container.xml");
        Files.write(file, lines, Charset.forName("UTF-8"));
        
        //OEBPS
        String oebpsPath = this.pathEpub + "/OEBPS";
        new File(oebpsPath).mkdir();
        
        // content.opf
        lines = new ArrayList();
        lines.add("[cc escaped=\"true\"  lang=\"xml\"]");
        lines.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        lines.add("package xmlns=\"http://www.idpf.org/2007/opf\" unique-identifier=\"EPB-UUID\" version=\"2.0\"");
        
        // metadata        
        lines.add("<metadata xmlns:opf=\"http://www.idpf.org/2007/opf\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\">");
        lines.add("<dc:title>" + this.livro.getTitulo() + "</dc:title>");        
        for (String[] autor : this.livro.getLstNomesCompletosAutores()) {
            lines.add("<dc:creator opf:role=\"aut\" opf:file-as=\"" + autor[1].toUpperCase() 
                    + ", " + autor[0] + "\">" + autor[0].substring(0,1).toUpperCase() 
                    + autor[0].substring(1).toLowerCase() + " " + autor[1] + "</dc:creator>");
        }
        lines.add("<dc:identifier id=\"EPB-UUID\">" + livro.getId() + "</dc:identifier>");
        lines.add("<dc:language>pt-br</dc:language>");
        
        // manifest
        lines.add("<manifest>");
        // for each capitulo ...
        lines.add("<item id=\"conteudo\" href=\"epub.html\" media-type=\"application/xhtml+xml\" />");
        // <!– CSS Style Sheets –>
        // <item id=”main-css” href=”style.css” media-type=”text/css”/>
        lines.add("<item id=\"ncx\" href=\"toc.ncx\" media-type=\"application/x-dtbncx+xml\"/>");
        
        // spine
        lines.add("<spine toc=\"ncx\">");
        // em idref usar o id do manifesto
        lines.add("<itemref idref=\"conteudo\" linear=\"yes\"/>");
        lines.add("</spine>");
        
        lines.add("</package>");
        lines.add("[/cc]");
        file = Paths.get(oebpsPath + "/content.opf");
        Files.write(file, lines, Charset.forName("UTF-8"));
        
        // toc.ncx
        lines = new ArrayList();
        
        lines.add("[cc escaped=\"true\"  lang=\"xml\"]");
        lines.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        lines.add("<!DOCTYPE ncx\n PUBLIC \"-//NISO//DTD ncx 2005-1//EN\" \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\">");
        lines.add("<ncx xmlns=\"http://www.daisy.org/z3986/2005/ncx/\" version=\"2005-1\">");
        lines.add("<head>");
        lines.add("<meta name=\"dtb:uid\" content=\"" + livro.getId() + "\"/>");
        // niveis de sumario
        lines.add("<meta name=\"dtb:depth\" content=\"2\"/>");
        lines.add("<meta name=\"dtb:totalPageCount\" content=\"0\"/>");
        lines.add("<meta name=\"dtb:totalPageCount\" content=\"0\"/>");
        lines.add("<meta name=\"dtb:maxPageNumber\" content=\"0\"/>");
        lines.add("</head>");
        lines.add("<docTitle>");
        lines.add("<text>" + livro.getTitulo() + "</text>");
        lines.add("</docTitle>");
        for (String[] autor : this.livro.getLstNomesCompletosAutores()) {
            lines.add("<docAuthor>");
            lines.add("<text>" + autor[0] + " " + autor[1] + "</text>");
            lines.add("</docAuthor>");
        }
        lines.add("<navMap>");
        // criar para cada capitulo
        lines.add("<navPoint id=\"conteudo\" playOrder=\"1\">");
        lines.add("<navLabel>");
        lines.add("<text>Conteúdo</text>");
        lines.add("</navLabel>");
        lines.add("<content src=\"epub.html\"/>");
        lines.add("</navPoint>");
        
        lines.add("</navMap>");
        lines.add("</ncx>");
        lines.add("[/cc]");        
        
        file = Paths.get(oebpsPath + "/toc.ncx");
        Files.write(file, lines, Charset.forName("UTF-8"));
        
        // capitulos - deve-se criar um arquivo para cada capitulo
        lines = new ArrayList();
        lines.add("[cc escaped=\"true\"  lang=\"xml\"]");
        lines.add("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"pt\">");
        lines.add("<head>");
        lines.add("<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\" />");
        lines.add("<title>Conteúdo</title>");
        //lines.add("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />");
        lines.add("</head><body><div><h3>" + livro.getTitulo() + "</h3>");        
        lines.add(livro.getTexto());
        lines.add("</div></body></html>[/cc]");
        
        file = Paths.get(oebpsPath + "/epub.html");
        Files.write(file, lines, Charset.forName("UTF-8"));
        
        
        /*http://tableless.com.br/epub-aprenda-a-criar-um-livro-digital/
        // cria o arquivo html para o EPub        
        BufferedWriter output = null;         
        File tmpFile = new File(this.pathEpub + "/epub.html");                
        
        try {
            output = new BufferedWriter(new FileWriter(tmpFile));
            output.write("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body>");
            output.write(this.livro.getTexto());
            output.write("</body></html>");
        } catch (IOException ex) {
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                }
            }
        }
        */
    }
    
    public void downloadEPub() {
        try {
            gerarEpub();
        } catch (IOException ex) {
            Logger.getLogger(EPub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
