export QUARKUS_HTTP_PORT=9000
./mvnw clean package "-Dquarkus.package.type=uber-jar" -Dquarkus.http.port=9000 && java -jar target/pw_tgrupal_g2_api-1.0.0-SNAPSHOT-runner.jar


