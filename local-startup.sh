mvn clean package -Dspring.profiles.active=test
docker build -t quizzard-api .
docker run -p 5000:5000 quizzard-api:latest
