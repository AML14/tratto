## Tool name: TRATTO

This is a completely new project which comprises multiple sub-projects, and we are not building anything on top of Toradocu, so it may be a good idea to give the project a new name. A possible name could be _TRATTO – **TRA**nsformer-based **T**oken-by-**T**oken **O**racle generation_ (an Italian word which means “line” or “way”). I think it's important to emphasize the fact that the approach generates oracles token by token, because: 1) it's one the main distinctive features, and 2) this allows the approach to generate variations of the oracles very easily, by replacing a token at any position with the 2nd ranked token and continuing the oracle generation process from there.

## Directory structure

1. `oracle-grammar/` is the folder containing the Xtext project for the grammar of the oracles generated by Jdoctor. This is the only program completed so far (although we may need to modify the grammar in the future).
2. `dataset1-creation/` should contain the program that creates the first dataset (i.e., the one that @beyzaeken is working on). Remember: this folder must also contain all necessary input data that the program takes to create the first dataset, may it be whole Java projects, the dataset from the ISSTA’18 paper, etc. We need to do this to make the approach replicable.
3. `dataset2-creation/` should contain the program that transforms the 1st dataset into the 2nd one (i.e., where outputs are true/false labels associated to a specific token). This is what @alberto.martin is working on. Most likely, this program also uses as input whole Java projects for which oracles are being generated, since we need to know the types of tokens to discern where they could go at a certain position or not. Therefore this data should also be included in this folder (or simply in a common folder that all projects can access).
4. `ml-model/` should contain all code related to the ML part of this project. This could include many different things, so @davide.molinelli and @elliott.zackrone may consider creating subprojects. For example, a program for pretraining a transformer on English language and source code (although  we’ll most likely use an already pretrained transformer), a program for fine-tuning the model with the 2nd dataset, a program for predicting an output label given an input, etc.
5. `oracle-generation/` should contain the program that generates oracles token-by-token. Nobody is working on this program yet, but if you take a look at the list of tasks that I shared on the email thread, it will reuse a lot of functionality from the previous programs.

## Conventions

- JDK version: 11.
- Build tool: Maven.
- Conventions related to naming and versioning:
    - `star.tratto` is the parent package (tratto.org is an actual website, so better not to use `org` as the package prefix).
    - `star.tratto` is also the name of the groupId used in Maven.
    - `tratto` is the name of the artifactId used in Maven.
    - The artifactId of each subproject should be `tratto-` followed by the name of each folder, e.g., `star.tratto:tratto-oracle-grammar`.
    - The initial version of each artifact is `0.0.1-SNAPSHOT`.
