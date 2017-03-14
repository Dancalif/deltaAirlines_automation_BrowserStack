

java -jar /Users/dan/dropbox/Portfolio_workspace/selenium-server-standalone-2.53.0.jar -role hub -port 4445

java -jar /Users/dan/dropbox/Portfolio_workspace/selenium-server-standalone-2.53.0.jar -role node -hub http://localhost:4445/grid/register

/Users/dan/dropbox/Portfolio_workspace/java -jar selenium-server-standalone-2.53.0.jar -role node -hub http://localhost:4444/grid/register > selenium.out &
