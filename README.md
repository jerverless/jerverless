<div align="center">
   <img src="media/jerverless_logo.png">
</div>
<br/>

[![GitHub license](https://img.shields.io/github/license/jerverless/jerverless.svg)](https://github.com/jerverless/jerverless/blob/master/LICENSE)
 [![GitHub (pre-)release](https://img.shields.io/github/release/jerverless/jerverless/all.svg)](https://github.com/jerverless/jerverless/releases)
  [![GitHub last commit](https://img.shields.io/github/last-commit/jerverless/jerverless.svg)](https://github.com/jerverless/jerverless/commits/master)
[![HitCount](http://hits.dwyl.io/jerverless/jerverless.svg)](http://hits.dwyl.io/jerverless/jerverless)

Turn anything into a serverless function.. Docker ready!

jerverless is a serverless runner which will execute anything (binaries, commands or your scripts) as a serverless function. It simply pipes http POST data into STDIN of any executable vice versa.

## How it works!

<div  align="center">
  <img src="media/jerverless.png"/>
</div>


## Example functions

- [Bash](https://github.com/jerverless/jerverless/tree/master/examples/bash)
- [Python](https://github.com/jerverless/jerverless/tree/master/examples/python)
- [Java](https://github.com/jerverless/jerverless/tree/master/examples/java)
- [Ballerina](https://github.com/jerverless/jerverless/tree/master/examples/ballerina)
- C
- C++
- Node
- Go
- C#

## How to create functions?

### On Machine (or VM)

1. Download **jar**
```
$ url --ssl -L https://github.com/jerverless/jerverless/releases/download/v0.0.1/org.jerverless-0.0.1.jar > jerverless.jar
```

2. Create `jerverless.properties`
```
exec = python helloworld.py
port = 8080
cors = true
```
3. Create your program (eg:- `helloworld.py`)

```python
name = raw_input()
print "Hello %s!" % name
```
4. Start server!

```
 $ java -jar org.jerverless-0.0.1.jar 
```

5. Test it!

```
$ curl -d Jerverless http://localhost:8080/function
```

Or simply use [template](https://github.com/jerverless/jerverless/examples) and jump to last step! 

### Docker

1. On your local machine, clone this repo and go to an examples directory of choice (eg: python): 

```
  git clone https://github.com/jerverless/jerverless.git
  cd jerverless/examples/python
```

2. Create the docker image:

```
  docker build --no-cache -t jerverless-py .
```

3. Run the app:

```
  docker run -it -p 8080:8080 jerverless-py
```

4. Navigate to 'https://localhost:8080/function' in your browser.

### Kubernetes

1. Create docker image for your function and push to dockerhub

2. Add docker image name to `.yml` deployment



## Project Status

- [x] Initial work (Structure, basic server with runner)
- [x] Multithreaded server mode
- [ ] Unit cases
- [x] CLI commands
- [x] Basic Samples
- [x] Docs
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

