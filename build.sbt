enablePlugins(ScalaJSPlugin)

name := "app"

scalaVersion := "2.12.8"

libraryDependencies += "me.shadaj" %%% "slinky-native" % "0.6.2"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.2"
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"

scalacOptions += "-P:scalajs:sjsDefinedByDefault"

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)

scalaJSModuleKind := ModuleKind.CommonJSModule
