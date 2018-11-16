# jerverless
Turn anything into a serverless function.. Docker ready!
<br/>
<div  align="center">
  <img src="media/jerverless.png"/>
 </div>
<br/>
jerverless is a serverless runner which will execute anything (binaries, commands or your scripts) as a serverless function. It simply pipes http POST data into STDIN of any executable.

## Example functions

- [Bash](https://github.com/shalithasuranga/jerverless/tree/master/examples/bash)
- [Python](https://github.com/shalithasuranga/jerverless/tree/master/examples/bash)

## Project Status

- [x] Initial work (Structure, basic server with runner)
- [x] Multithreaded server mode
- [ ] Unit cases
- [ ] CLI commands
- [x] Basic Samples
- [ ] Docs
- [x] Beta Release!


# Developer Guide

```bash
 git clone <forked_url>
```

```bash
 mvn clean package
```

```
 java -jar org.jerverless-0.0.1-SNAPSHOT.jar 
```
