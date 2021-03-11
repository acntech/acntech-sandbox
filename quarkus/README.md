# Quarkus
Examples using the Quarkus Framework

## Build
Read more information about building with Maven at the Quarkus home page
[https://quarkus.io/guides/maven-tooling](https://quarkus.io/guides/maven-tooling)

### Development mode
This build option enables live hot swap of source code and resource.

Run with the following command:
```shell
mvn compile quarkus:dev
```

### Build fast-jar
This is the default build option. Execute build using the following command:
```shell
mvn clean package
```
This will create a folder `./target/quarkus-app` with the build output. All the folder content is needed in order to run the application.

Run the application with the following command:
```shell
java -jar ./target/quarkus-app/quarkus-run.jar
```

## Build Über JAR
Execute build using the following command:
```shell
mvn clean package -Puser-jar
```
This will create a self-contained and runnable Über JAR `./target/${artifactId}-${version}-runner.jar`. The original JAR will be renamed to `./target/${artifactId}-${version}.jar.original`.

Run the application with the following command:
```shell
java -jar ./target/${artifactId}-${version}-runner.jar
```

## Build native image
This will require GraalVM to be installed on the build system, and that the `GRAALVM_HOME` environment variable points to the installation directory.

System specific build tools and libraries must also be installed.
* Debian based system: `sudo apt-get install build-essential libz-dev zlib1g-dev`
* RedHat based system: `sudo dnf install gcc glibc-devel zlib-devel libstdc++-static`

Execute build using the following command:
```shell
mvn clean package -Pnative
```
This will create an executable that is compiled to work with the build system `./target/${artifactId}-${version}-runner`.

It is possible to force the creation of an executable that can run in a Linux system, like in a Docker container.
Execute build using the following command:
```shell
mvn clean package -Pnative-container
```

Run the application with the following command:
```shell
./target/${artifactId}-${version}-runner
```
