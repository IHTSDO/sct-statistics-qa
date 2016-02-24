# SNOMED CT Pattern and Changes Evaluation Toolkit

SNOMED CT Quality Assurance (QA) processes are an integral part of content authoring, maintenance and publishing activities through the lifecycle of each International release.

As part of daily authoring activities, editors follow a formal content management process including revision workflows, computer-assisted real time rule-based enforcement of editorial guidelines and testing for conformance to SNOMED CT design and technical specifications. Daily builds of the development database in RF2 format are tested in batch, and detected anomalies are queued for issue resolution.

## Architecture

The Quality Evaluation toolkit is a piece of software that executes quality evaluation queries over SNOMED CT release files and creates structured outputs that can be integrated into other quality revision processes.
Components:

* Data generator: reads release files, executes queries, creates outputs.
* Command line tool: packages the generator in a format that enables the execution from the command line
* Results viewer: basic visualization tool to browse the output

All these components are included in a single multi-module Maven Java project, that facilitates distribution and deployment.

## Running the statistics 

### Unpacking the binaries

Download and unpack the binaries from [the releases section of this project](https://github.com/termMed/sct-statistics-qa/releases).

The executable scripts are:

* Windows: Stats.bat, runs the statistics generation using config/intConfig.xml to locate the RF2 sources.
* Mac or Linux: Stats.sh, runs the statistics generation using config/intConfig.xml to locate the RF2 sources.
* Windows: Extension_Stats.bat, runs the statistics generation using config/extConfig.xml to locate the RF2 sources.
* Mac or Linux: Extension_Stats.sh, runs the statistics generation using config/extConfig.xml to locate the RF2 sources.
	
###	Configuring SNOMED CT Release data

The “config” folder in the binaries package contains all the necessary configurations for locating the release files that will be subject of the evaluation in your hard drive.

The configuration files contain several parameters, this guide will focus on the important ones for the execution, the rest are part of the configuration to extend the toolkit with new tasks.

There are 2 configuration files, intConfig.xml is used for single package releases, like the International Release, extConfig.xml is used for releases with dependencies with other packages, like extension releases.

Adding values to the configuration files:

*	In config/intConfig.xml and config/extConfig.xml
  * releaseFullFolder: path to a folder that contains a RF2 Full SNOMED CT Release.
  * releaseDate: effective time for the release that is the subject of the analysis.
  * previousReleaseDate: effective time for the previous release for the purpose of changes reports.
  * Example:
    * <releaseFullFolder>/MyFiles/SnomedCT_RF2Release_INT_20160131/Full</releaseFullFolder>
    * <releaseDate>20160131</releaseDate>
    * <previousReleaseDate>20150731</previousReleaseDate>
  * Advanced parameters: the effective time needs to be replaced in this configurarion line:
    * <param><name>releaseDate</name><value>20160215</value></param>
* Only in config/extConfig.xml
  * releaseDependencies: includes a list of “releaseFullFolder” elements that direct to the location of other RF2 Full packages that represent dependencies of the focus release.
  * Example:
```
    <releaseDependencies>
      <releaseFullFolder/MyData/RF2_Core_Release_20150731_Beta_version_4/Full</releaseFullFolder>
      <releaseFullFolder>/MyData/ES_Release/RF2/SnomedCT_SpanishRelease-es_INT_20151031/RF2Release/Full</releaseFullFolder>
    </releaseDependencies>
```
###	Running queries and creating output files

After the configuration file was edited to include valid path to the source RF2 Full data, the execution script should be launched. In some environments permissions for execution should be granted before, for example in Mac OSX or Linux:

```
chmod Stats.sh 777
./Stats.sh
```

This will launch the process, details from intermediate tasks will be displayed in the output, time taken will depend on the release size and available resources.

After the process finishes the following folders will contain the results:

* toolkit/stats_output: contains the statistics results, as CSV files.
* toolkit/patterns_output: contains the output of the patterns results, as JSON files.

When the files creation finishes, the process automatically launches a results server that be accessed from a web browser, this tool is explained in the next chapter. To exit the server press Enter in the command line window.

###	Browsing results

When all the statics and patterns queries have ended, point a web browser to this URL:

```
http://localhost:8080
```

The site displays the resulting information, organized in a friendly way and with sorting and filtering utilities.
The site can be copied into a web server and published to external consumption, all the site is contained in the folder /site. The site uses Ajax to dynamically load content, and it requires a web server to run, the index.html won’t load content properly if opened directly as a file from a web browser.
The Statistics reports home page contains a few dynamic graphs generated form the results data, and options to visualize each individual report.
