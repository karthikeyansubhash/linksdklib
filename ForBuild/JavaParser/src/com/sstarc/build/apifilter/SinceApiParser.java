/*
 * Johnred
 *
 * May 02, 2016
 *
 * Copyright (c) 2016 by Samsung Electronics, Inc All rights reserved.
 *
 * This software is the confidential and proprietary information of Samsung
 * Electronics, Inc. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Samsung.
 */
package com.sstarc.build.apifilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SinceApiParser {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        final FileInputStream file_args = new FileInputStream("..\\javadoc_args.txt");
        final BufferedReader br = new BufferedReader(new InputStreamReader(file_args));

        String line = "";

        try {
            while ((line = br.readLine()) != null && !line.contains("-sourcepath")) {
                try {
                    line = line.replace("/", File.separator);
                    line = line.replace("\"", "");

                    final File file = new File(".." + File.separator + line);

                    if (file.exists()) {
                        parseFile(file.getAbsolutePath());
                    } else {
                        System.out.println(file + " exists :" + file.exists());
                    }
                } catch (FileNotFoundException | ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file_args.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseFile(final String line) throws FileNotFoundException, ParseException {
        final FileInputStream in = new FileInputStream(line);

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // visit and print the methods names
        new MethodVisitor().visit(cu, null);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this
            // CompilationUnit, including inner class methods

            final JavadocComment javadoc = n.getJavaDoc();

            if (javadoc != null) {
                final String content = javadoc.getContent();

                if (content != null && content.contains("@since API 4") && !content.contains("@hide")
                        && (n.getModifiers() & ModifierSet.PRIVATE) != ModifierSet.PRIVATE) {
                    // Parse javadoc and print method if it's from
                    System.out.println((n.isInterface() ? "interface" : "class") + "\n\t" + n.getName() + "\n");
                }
            }
            super.visit(n, arg);
        }

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this
            // CompilationUnit, including inner class methods

            final JavadocComment javadoc = n.getJavaDoc();

            if (javadoc != null) {
                final String content = javadoc.getContent();

                if (content != null && content.contains("@since API 4") && !content.contains("@hide")
                        && (n.getModifiers() & ModifierSet.PRIVATE) != ModifierSet.PRIVATE) {
                    // Parse javadoc and print method if it's from
                    System.out.println(((ClassOrInterfaceDeclaration) n.getParentNode()).getName() + "\n\t" + n.getDeclarationAsString() + "\n");
                }
            }
            super.visit(n, arg);
        }

        @Override
        public void visit(final FieldDeclaration n, final Object arg) {

            final JavadocComment javadoc = n.getJavaDoc();

            if (javadoc != null) {
                final String content = javadoc.getContent();

                if (content != null && content.contains("@since API 4") && !content.contains("@hide")) {
                    // Parse javadoc and print method if it's from
                    System.out.println(((ClassOrInterfaceDeclaration) n.getParentNode()).getName() + "\n\t" + n.getVariables() + "\n");
                }
            }

            super.visit(n, arg);
        }

        @Override
        public void visit(final EnumDeclaration n, final Object arg) {

            final JavadocComment javadoc = n.getJavaDoc();

            if (javadoc != null) {
                final String content = javadoc.getContent();

                if (content != null && content.contains("@since API 4") && !content.contains("@hide")) {
                    // Parse javadoc and print method if it's from
                    System.out.println("enum\n\t" + n.getName() + "\n");
                }
            }

            super.visit(n, arg);
        }

        @Override
        public void visit(final EnumConstantDeclaration n, final Object arg) {

            final JavadocComment javadoc = n.getJavaDoc();

            if (javadoc != null) {
                final String content = javadoc.getContent();

                if (content != null && content.contains("@since API 4") && !content.contains("@hide")) {
                    // Parse javadoc and print method if it's from
                    System.out.println(((EnumDeclaration) n.getParentNode()).getName() + "\n\t" + n.getName() + "\n");
                }
            }

            super.visit(n, arg);
        }
    }
}
