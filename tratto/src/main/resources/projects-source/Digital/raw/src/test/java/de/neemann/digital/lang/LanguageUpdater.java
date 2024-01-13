/*
 * Copyright (c) 2019 Helmut Neemann.
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digital.lang;

import de.neemann.digital.integration.Resources;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class LanguageUpdater {
    public static final Format FORMAT = Format.getPrettyFormat()
            .setIndent("    ")
            .setTextMode(Format.TextMode.PRESERVE);

    private static final String COMMENT_REF = "  IMPORTANT\nDo not edit this file directly!\n" +
            "This makes it very difficult for me to continue to maintain the translation.\n" +
            "Pull requests in which this file is modified cannot be accepted.\n" +
            "In the file howTo.md you can find more details about translations.\n";
    private static final String COMMENT_LANG = "  IMPORTANT\nDo not edit this file directly!\n" +
            "This makes it very difficult for me to continue to maintain the translation.\n" +
            "The only exception are simple typos.\n" +
            "Pull requests in which this file is modified beyond simple typos cannot be accepted.\n" +
            "In the file howTo.md you can find more details about translations.\n";
    private final Document lang;
    private final Document ref;
    private final File langFileName;
    private final File refFileName;
    private final TypoFixer typoFixer;
    private int modified;

    private LanguageUpdater(File sourceFilename) throws JDOMException, IOException {
        Element dif = loadDif(sourceFilename);
        String langName = dif.getAttributeValue("name");

        File langPath = new File(Resources.getRoot(), "../../main/resources/lang");
        if (!langPath.exists())
            throw new IOException("lang folder not found!");

        typoFixer = new TypoFixer(langPath, "lang_" + langName + "_ref.xml");

        langFileName = new File(langPath, "lang_" + langName + ".xml");
        refFileName = new File(langPath, "lang_" + langName + "_ref.xml");
        lang = new SAXBuilder().build(langFileName);
        ref = new SAXBuilder().build(refFileName);
        checkComment(lang, COMMENT_LANG);
        checkComment(ref, COMMENT_REF);

        cleanup(lang, ref);

        for (Element e : dif.getChildren()) {
            String key = e.getAttributeValue("name");
            String type = e.getAttributeValue("type");
            System.out.println(key + ": " + type);
            final String transText = e.getChild(langName).getText().trim();
            if (!transText.isEmpty() && !transText.equals("-")) {
                String enText = e.getChild("en").getText();
                if (isCopyAndPaste(enText.trim(), transText)) {
                    System.out.println("ignored copy&paste at: " + key);
                } else {
                    switch (type) {
                        case "new":
                            add(ref, key, enText);
                            add(lang, key, transText);
                            modified++;
                            break;
                        case "typo":
                            typoFixer.fix(key, enText);
                            if (!contains(lang, key)) {
                                add(ref, key, enText);
                                add(lang, key, transText);
                                modified++;
                                break;
                            }
                        case "modified":
                            if (replace(lang, key, transText)) {
                                replace(ref, key, enText);
                                modified++;
                            } else {
                                System.out.println("ignored unchanged key: " + key);
                            }
                            break;
                        default:
                            throw new IOException("Unknown key type: " + type);
                    }
                }
            } else {
                System.out.println("ignored empty key: " + key);
            }
        }
    }

    private boolean isCopyAndPaste(String a, String b) {
        return a.equals(b) && a.indexOf(' ') > 0;
    }

    private void cleanup(Document lang, Document ref) {
        HashMap<String, String> langKeys = new HashMap<>();
        for (Element e : lang.getRootElement().getChildren()) {
            String key = e.getAttributeValue("name");
            langKeys.put(key, e.getText().trim());
        }
        HashSet<String> removeAlsoFromLang = new HashSet<>();
        ref.getRootElement().getChildren().removeIf(e -> {
            String key = e.getAttributeValue("name");
            if (!langKeys.containsKey(key)) {
                System.out.println("removed non translated ref key: " + key);
                return true;
            }
            String transText = e.getText().trim();
            if (isCopyAndPaste(transText, langKeys.get(key))) {
                System.out.println("removed copy&pasted key '" + key + "' which is '" + transText + "'");
                removeAlsoFromLang.add(key);
                return true;
            }
            return false;
        });
        lang.getRootElement().getChildren().removeIf(e -> removeAlsoFromLang.contains(e.getAttributeValue("name")));
    }

    private Element loadDif(File sourceFilename) throws JDOMException, IOException {
        if (sourceFilename.getName().toLowerCase().endsWith(".zip")) {
            ZipFile zip = new ZipFile(sourceFilename);
            Enumeration<? extends ZipEntry> e = zip.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = e.nextElement();
                if (entry.getName().toLowerCase().endsWith(".xml"))
                    return new SAXBuilder().build(zip.getInputStream(entry)).getRootElement();
            }
            throw new IOException("no diff file found in zip");
        } else
            return new SAXBuilder().build(sourceFilename).getRootElement();
    }

    private void checkComment(Document doc, String comment) {
        List<Content> content = doc.getContent();
        content.removeIf(c -> c instanceof Comment);
        content.add(0, new Comment(comment));
    }

    private void add(Document xml, String key, String text) throws IOException {
        for (Element e : xml.getRootElement().getChildren()) {
            String k = e.getAttributeValue("name");
            if (k.equals(key)) {
                throw new IOException("key " + key + " is already present in " + xml);
            }
        }
        xml.getRootElement().addContent("    ");
        xml.getRootElement().addContent(new Element("string").setAttribute("name", key).setText(text));
        xml.getRootElement().addContent("\n");
    }

    private boolean replace(Document xml, String key, String text) throws IOException {
        for (Element e : xml.getRootElement().getChildren()) {
            String k = e.getAttributeValue("name");
            if (k.equals(key)) {
                if (e.getText().trim().equals(text.trim())) {
                    return false;
                } else {
                    e.setText(text);
                    return true;
                }
            }
        }
        throw new IOException("key " + key + " not found in " + xml);
    }

    private boolean contains(Document xml, String key) {
        for (Element e : xml.getRootElement().getChildren()) {
            String k = e.getAttributeValue("name");
            if (k.equals(key))
                return true;
        }
        return false;
    }

    private void update() throws IOException {
        typoFixer.update();
        if (modified > 0) {
            new XMLOutputter(FORMAT).output(ref, new FileOutputStream(refFileName));
            new XMLOutputter(FORMAT).output(lang, new FileOutputStream(langFileName));
            System.out.println(modified + " keys updated!");
        } else {
            System.out.println("no modification found!");
        }
    }

    public static void main(String[] args) throws JDOMException, IOException {
        if (args.length == 1) {
            new LanguageUpdater(new File(args[0])).update();
        } else {
            File src = new File("/home/hneemann/Dokumente/Java/digital/src");
            if (!src.exists()) {
                JFileChooser dc = new JFileChooser();
                dc.setDialogTitle("Select the Digital \"src\" Folder");
                dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (dc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    src = dc.getSelectedFile();
                }
            }
            System.setProperty("testdata", new File(src, "test/resources").getPath());

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Select the updated diff File");
            fc.addChoosableFileFilter(new FileNameExtensionFilter("xml", "xml"));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("zip", "zip"));
            if (src != null) {
                final File s = new File(src.getParentFile(), "target/lang_diff_pt.xml");
                fc.setSelectedFile(s);
            }
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                new LanguageUpdater(fc.getSelectedFile()).update();
        }
    }

}
