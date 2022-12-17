Docker / packageName := "paweljpl/monitoring-test-app"
Docker / maintainer := "Pawel <inne.poczta@gmail.com>"

dockerBaseImage := "openjdk:11-jre-slim"
dockerExposedPorts ++= Seq(8080, 8080)

dockerUpdateLatest := true
