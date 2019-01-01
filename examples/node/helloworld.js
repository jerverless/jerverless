const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
  
rl.question('', (name) => {
    console.log(`Hello: ${name}`);
    rl.close();
});