/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpma.messageprocessing;

import com.jpma.controller.ProcessFileMessageController;
import com.jpma.exception.APIException;

/**
 *
 * @author Flavio
 */
public class MessageProcessing {

    /**
     * The main class.
     * @param args the command line arguments
     * @throws com.jpma.exception.APIException
     */
    public static void main(String[] args) throws APIException {
        
        String filePath;
        if (args.length != 0) {
            filePath = args[0];
        } else {
            System.out.println("*** Loading the Case Test File...");
            filePath = "caseTest/messages.txt";
        }

        ProcessFileMessageController pfmc = new ProcessFileMessageController();
        pfmc.readFile(filePath);
    }
}
