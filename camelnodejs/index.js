const execa = require('execa');

const PARAMS = ['route.yaml'];

const startApacheCamel = async function(runnerPath = undefined) {
  var path = runnerPath;
  if (runnerPath === undefined)
    path = './node_modules/camelnodejs/binary/camel-quarkus-runner.exe'
	const subprocess = execa(path, PARAMS).stdout.pipe(process.stdout);

	//setTimeout(() => {
	//	subprocess.cancel();
	//}, 1000);

	try {
		await subprocess;
	} catch (error) {
		console.log(subprocess.killed); // true
		console.log(error.isCanceled); // true
	}
}

exports.startApacheCamel = startApacheCamel;

  