```
   _                          _               
  (_)                        | |              
   _  ___ _ ____   _____ _ __| | ___  ___ ___ 
  | |/ _ \ '__\ \ / / _ \ '__| |/ _ \/ __/ __|
  | |  __/ |   \ V /  __/ |  | |  __/\__ \__ \
  | |\___|_|    \_/ \___|_|  |_|\___||___/___/
 _/ |                                         
|__/                                          
```
Turn anything into a serverless function.. Docker ready!
<br/>
<div  align="center">
  <img src="media/jerverless.png"/>
 </div>
<br/>
jerverless is a serverless runner which will execute anything (binaries, commands or your scripts) as a serverless function. It simply pipes http POST data into STDIN of any executable vice versa.

## Example functions

- [Bash](https://github.com/shalithasuranga/jerverless/tree/master/examples/bash)
- [Python](https://github.com/shalithasuranga/jerverless/tree/master/examples/python)
- Java
- C
- C++
- Node
- Go
- C#

## Project Status

- [x] Initial work (Structure, basic server with runner)
- [x] Multithreaded server mode
- [ ] Unit cases
- [x] CLI commands
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
 java -jar target/org.jerverless-0.0.1.jar 
```

# Docker

1. On your local machine, clone this repo and go to an examples directory of choice (eg: python): 

```
  git clone https://github.com/shalithasuranga/jerverless.git
  cd jerverless/examples/python
```

2. Create the docker image:

```
  docker build -t jerverless-py .
```

3. Run the app:

```
  docker run -p 8080:8080 jerverless-py
```

4. Navigate to 'https://localhost:8080/function' in your browser.

