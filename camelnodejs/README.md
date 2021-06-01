
# Apache Camel for NodeJS

Run Camel routes from NodeJS.

If you want to run Apache Camel routes from Kubernetes then consider [Apache Camel K](https://camel.apache.org/camel-k/latest/).

## Notes

This is in early development, works on Windows only. (at this moment).

## Description

[Apache Camel](https://camel.apache.org/) allows for data integration between services through the use of pipelines.
 Apache Camel is on the JVM, the problem is that when using NodeJS it is unattractive to run a Java program or JVM from NodeJS.
 Especially when on cloud such as AWS Lambda, the memory limits are low and installing or running a JVM is very much not a good idea.

## Solution

To avoid use of the JVM, Java code using Apache Camel as a library is compiled with Quarkus to a binary executable, this executable can then run the Camel route. NodeJS executes this executable. In a sample run the binary executable `camel-quarkus-runner.exe` file size was 55.6 MB and when running used 32.0 MB of memory which is reasonable.

## Restrictions

* Not all [Apache Camel components](https://camel.apache.org/camel-quarkus/latest/reference/index.html) will be added, the addition of more components increases the file size of the executable so only common components are added.

## Future directions and tasks

* Produce multiple binaries, Windows, Linux and OSX
* It would be better to move this to a library (DLL on Windows) rather than an executable, but [how to compile Quarkus to a DLL?](https://stackoverflow.com/questions/67782111/compile-quarkus-application-to-a-dll-library) can it even be done? 
* Handle application crashes and errors
* Add logging, don't output to stdout as it currently does
* Support Javascript routing, currently only supports YAML file routing
* Add example Apache Camel routes and test cases
* Tighter integration with NodeJS
* Support multiple YAML files and/or any number of YAML files within a directory
* Support YAML file name as a parameter rather than default of route.yaml
## Usage

`npm install camelnodejs`

Given a route.yaml

```yaml
- route:
    from: "timer:yaml?period=3s"
    steps:
      - set-body:
          simple: "Timer fired ${header.CamelTimerCounter} times"
      - to:
          uri: "log:yaml"
```

Then the corrisponding `index.js` javascript

```javascript
const camelnodejs = require('camelnodejs');

(async () => {
    await camelnodejs.startApacheCamel();
})()
```

Run with node, `node index.js` then it executes the Apache Camel route.
