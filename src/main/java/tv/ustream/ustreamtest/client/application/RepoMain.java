/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.ustream.ustreamtest.client.application;

import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tv.ustream.ustreamtest.client.model.MyOptions;
import tv.ustream.ustreamtest.client.model.OptionBuilder;
import tv.ustream.ustreamtest.client.model.Repository;
import tv.ustream.ustreamtest.client.service.RepositoryService;

/**
 *
 * @author pfeiferlaszlo
 */
@Component
public class RepoMain implements CommandLineRunner {
    
    private static final String APP_NAME = "ustream-client-xxx.jar";
    
    @Autowired
    private OptionBuilder optionBuilder;
    
    @Autowired
    private CommandLineParser parser;
    
    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void run(String... args) throws Exception {
        Options options = optionBuilder.getOptions();
        CommandLine line;
        try {
            line = parser.parse( options, args);
        } catch(ParseException exception) {
            printHelp(options);
            return;
        }
        if (line.hasOption(MyOptions.ADD.getCharacter())) {
            add(line);
        } else if (line.hasOption(MyOptions.GET.getCharacter())) {
            get(line);
        } else if (line.hasOption(MyOptions.DELETE.getCharacter())) {
            delete(line);
        } else if (line.hasOption(MyOptions.LIST.getCharacter())) {
            list(line);
        } else {
            printHelp(options);
        }
    }

    private void list(CommandLine line) throws NumberFormatException {
        String[] searchArgs = line.getOptionValues(MyOptions.LIST.getCharacter());
        final int threshold = Integer.valueOf(searchArgs[0]);
        List<Repository> repositories = repositoryService.getRepositories(threshold);
        repositories.forEach((Repository r) -> {
            System.out.println(r);
        });
    }

    private void delete(CommandLine line) {
        String[] searchArgs = line.getOptionValues(MyOptions.DELETE.getCharacter());
        final String name = searchArgs[0];
        repositoryService.deleteRepository(name);
    }

    private void get(CommandLine line) {
        String[] searchArgs = line.getOptionValues(MyOptions.GET.getCharacter());
        final String name = searchArgs[0];
        Repository repository = repositoryService.getRepository(name);
        System.out.println(repository);
    }

    private void add(CommandLine line) {
        String[] searchArgs = line.getOptionValues(MyOptions.ADD.getCharacter());
        final String name = searchArgs[0];
        final String author = searchArgs[1];
        repositoryService.createRepository(name, author);
    }

    private void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(APP_NAME, options);
    }
    
}
