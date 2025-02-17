CALL ./mvnw clean package "-Dquarkus.package.type=uber-jar" -Dquarkus.http.port=9000 
CALL java -jar target/pw_tgrupal_g2_api-1.0.0-SNAPSHOT-runner.jar


