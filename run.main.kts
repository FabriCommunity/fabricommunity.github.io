@file:Repository("https://dl.bintray.com/jakubriegel/kotlin-shell")
@file:DependsOn("eu.jrie.jetbrains:kotlin-shell-core:0.2.1")

var buildCommand = "./build.sh"
var assetsCommand = "./build-assets.sh"
var serveCommand = "./serve.sh"

if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
    buildCommand = "cmd /c start \"\" build.bat"
    assetsCommand = "cmd /c start \"\" build-assets.bat"
    serveCommand = "cmd /c start \"\" serve.bat"
}

if (!args.contains("skip-pages")) {
    println("Generating pages...")
    Runtime.getRuntime().exec(buildCommand).waitFor()
} else {
    println("Skipped page generation.")
}

if (!args.contains("skip-assets")) {
    println("Generating assets...")
    Runtime.getRuntime().exec(assetsCommand).waitFor()
} else {
    println("Skipped asset generation.")
}

println("Running server...")
val serve: Process = Runtime.getRuntime().exec(serveCommand)
println("Server Online at http://localhost:8080/")
serve.waitFor()

// Prevents printing the exit code
System.out.close()