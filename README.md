# SNOMED CT Pattern and Changes Evaluation Toolkit

SNOMED CT Quality Assurance (QA) processes are an integral part of content authoring, maintenance and publishing activities through the lifecycle of each International release.
As part of daily authoring activities, editors follow a formal content management process including revision workflows, computer-assisted real time rule-based enforcement of editorial guidelines and testing for conformance to SNOMED CT design and technical specifications. Daily builds of the development database in RF2 format are tested in batch, and detected anomalies are queued for issue resolution. 
As part of pre-publishing content management activities, SNOMED CT release packages are tested for conformance to the RF2 technical specification before each release delivery. Content adherence to Editorial Guidelines is also tested as part of the publishing process, both by automated means and by manual review of selected content changes.
Before a production release is delivered to members and affiliates, a beta version of the candidate release files is shared with National Release Centers (NRCs) for independent testing and validation, using the internal NRC testing frameworks and change management processes.

## Architecture

The Quality Evaluation toolkit is a piece of software that executes quality evaluation queries over SNOMED CT release files and creates structured outputs that can be integrated into other quality revision processes.
Components:
*Data generator: reads release files, executes queries, creates outputs.
*Command line tool: packages the generator in a format that enables the execution from the command line
*Results viewer: basic visualization tool to browse the output

All these components are included in a single multi-module Maven Java project, that facilitates distribution and deployment.

