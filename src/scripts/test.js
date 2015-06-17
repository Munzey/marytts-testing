var wiki = require('./txtwiki.js');
var fs = require('fs');
console.log(process.argv[2]);
var file = fs.readFileSync(process.argv[2], "utf8");
console.log(wiki.parseWikitext(file));