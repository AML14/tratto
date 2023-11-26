import fnmatch
import os.path
import xml.etree.ElementTree as ET
import csv
import sys

def generate_pom(project_dir, path_to_dependency_file, path_to_output_file, main_class=None):
    # Create the root element with namespaces and schema location
    root = ET.Element(
        "project",
        attrib={
            "xmlns": "http://maven.apache.org/POM/4.0.0",
            "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xsi:schemaLocation": "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd",
        },
    )
    # Create child elements and add data
    model_version = ET.SubElement(root, "modelVersion")
    model_version.text = "4.0.0"

    group_id = ET.SubElement(root, "groupId")
    group_id.text = "com.example"

    artifact_id = ET.SubElement(root, "artifactId")
    artifact_id.text = "your-project-name"

    version = ET.SubElement(root, "version")
    version.text = "1.0-SNAPSHOT"

    # Build element
    build = ET.SubElement(root, "build")

    plugins = ET.SubElement(build, "plugins")

    # Maven Compiler Plugin
    compiler_plugin = ET.SubElement(plugins, "plugin")

    compiler_group_id = ET.SubElement(compiler_plugin, "groupId")
    compiler_group_id.text = "org.apache.maven.plugins"

    compiler_artifact_id = ET.SubElement(compiler_plugin, "artifactId")
    compiler_artifact_id.text = "maven-compiler-plugin"

    compiler_version = ET.SubElement(compiler_plugin, "version")
    compiler_version.text = "3.8.1"

    compiler_configuration = ET.SubElement(compiler_plugin, "configuration")

    compiler_source = ET.SubElement(compiler_configuration, "source")
    compiler_source.text = "1.8"

    compiler_target = ET.SubElement(compiler_configuration, "target")
    compiler_target.text = "1.8"

    # Maven Assembly Plugin
    assembly_plugin = ET.SubElement(plugins, "plugin")

    assembly_group_id = ET.SubElement(assembly_plugin, "groupId")
    assembly_group_id.text = "org.apache.maven.plugins"

    assembly_artifact_id = ET.SubElement(assembly_plugin, "artifactId")
    assembly_artifact_id.text = "maven-assembly-plugin"

    assembly_version = ET.SubElement(assembly_plugin, "version")
    assembly_version.text = "3.3.0"

    assembly_configuration = ET.SubElement(assembly_plugin, "configuration")

    archive = ET.SubElement(assembly_configuration, "archive")

    manifest = ET.SubElement(archive, "manifest")

    add_classpath = ET.SubElement(manifest, "addClasspath")
    add_classpath.text = "true"

    if main_class is not None:
        main_class = ET.SubElement(manifest, "mainClass")
        main_class.text = "com.example.MainClass"

    descriptor_refs = ET.SubElement(assembly_configuration, "descriptorRefs")

    descriptor_ref = ET.SubElement(descriptor_refs, "descriptorRef")
    descriptor_ref.text = "jar-with-dependencies"

    assembly_executions = ET.SubElement(assembly_plugin, "executions")

    assembly_execution = ET.SubElement(assembly_executions, "execution")

    assembly_execution_id = ET.SubElement(assembly_execution, "id")
    assembly_execution_id.text = "make-assembly"

    assembly_phase = ET.SubElement(assembly_execution, "phase")
    assembly_phase.text = "package"

    assembly_goals = ET.SubElement(assembly_execution, "goals")

    assembly_goal = ET.SubElement(assembly_goals, "goal")
    assembly_goal.text = "single"

    # Dependencies element
    dependencies = ET.SubElement(root, "dependencies")

    if os.path.exists(path_to_dependency_file):
        with open(path_to_dependency_file, newline='') as csvfile:
            csv_reader = csv.reader(csvfile)

            for row in csv_reader:
                group_id, artifact_id, version = row
                dependency = ET.SubElement(dependencies, "dependency")
                dependency_group_id = ET.SubElement(dependency, "groupId")
                dependency_group_id.text = group_id.replace("/", ".")
                dependency_artifact_id = ET.SubElement(dependency, "artifactId")
                dependency_artifact_id.text = artifact_id
                dependency_version = ET.SubElement(dependency, "version")
                dependency_version.text = version
    else:
        jar_files_paths = []
        for root_dir, dirs, files in os.walk(f"{project_dir}/lib"):
            for file in fnmatch.filter(files, '*.jar'):
                jar_files_paths.append(os.path.join(root_dir, file))

        for idx, jar_file_path in enumerate(jar_files_paths):
            dependency = ET.SubElement(dependencies, "dependency")
            dependency_group_id = ET.SubElement(dependency, "groupId")
            dependency_group_id.text = "local"
            dependency_artifact_id = ET.SubElement(dependency, "artifactId")
            dependency_artifact_id.text = f"{jar_file_path.replace('.jar','').split('/')[-1]}"
            dependency_version = ET.SubElement(dependency, "version")
            dependency_version.text = "1.0"
            dependency_scope = ET.SubElement(dependency, "scope")
            dependency_scope.text = "system"
            dependency_system_path = ET.SubElement(dependency, "systemPath")
            dependency_system_path.text = jar_file_path

    with open(path_to_output_file, 'wb') as output_file:
        ET.ElementTree(root).write(output_file, encoding="utf-8")
        #tree.write(output_file, encoding="utf-8", xml_declaration=True)

if __name__ == "__main__":
    project_dir = sys.argv[1]
    path_to_dependency_file = sys.argv[2]
    path_to_output_file = sys.argv[3]
    main_class = sys.argv[4] if len(sys.argv) > 4 and not sys.argv[4] == "" else None
    generate_pom(project_dir, path_to_dependency_file, path_to_output_file, main_class)



