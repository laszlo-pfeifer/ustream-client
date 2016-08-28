/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.ustream.ustreamtest.client.model;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 *
 * @author pfeiferlaszlo
 */
public class OptionBuilder {
    
    public Options getOptions() {
        Options options = new Options();
        options.addOption(MyOptions.HELP.getCharacter(), "help", false, "print this help");
        options.addOption(Option.builder(MyOptions.LIST.getCharacter())
                .longOpt("list")
                .desc("gives back a list of repositories")
                .hasArg()
                .argName("threshold")
                .build());
        options.addOption(Option.builder(MyOptions.GET.getCharacter())
                .longOpt("get")
                .desc("gives back a repository by name")
                .hasArg()
                .argName("name")
                .build());
        options.addOption(Option.builder(MyOptions.ADD.getCharacter())
                .longOpt("add")
                .desc("create a new repository")
                .hasArgs()
                .numberOfArgs(2)
                .argName("name> <author")
                .build());
        options.addOption(Option.builder(MyOptions.DELETE.getCharacter())
                .longOpt("delete")
                .desc("delete a repository")
                .hasArg()
                .argName("name")
                .build());
        return options;
    }
}
