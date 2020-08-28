# card-scheme
Card Scheme Spring Boot

## Setting up the project

Clone the repository 

```bash
git clone https://github.com/Thecarisma/card-scheme.git
```

The app was created with intelliJ but since it a maven project any IDE conscious of maven should be able to load it just fine. On first open install the dependencies. 

The folder contains the folders for the two tasks. The first folder contain the project to verify a card and publish the payload to kafka and count the number of time the card was hit.

The second project contains the spring project that consumes the kafka stream in real time and has a simple UI that shows the payload that is being consumed from kafka. 

After opening the project in intelliJ the two project should be ready to run. The first project listen on port 8081 and the second listen on port 8082. The kafka consuming project does not require a database.

## Database Settings

The first project uses mysqly database to cache the response from binlist.net to prevent making the API call every time a card is to be verified. create a database name `card_schema` in your mysql database 

```sql
CREATE SCHEMA card_schema;
```

The proceed to set the database credential in the application.properties or better set the following environment variable with no need to modify the application.properties files

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/card_scheme?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=root
```

When the app is run for the first time Flyway run the migration file `V1__Initialize.sql` and create the needed tables.

## Setting up Kafka

Visit this page on how to setup kafka https://dzone.com/articles/kafka-setup

## Payloads

### First App (Running on port 8081)

#### Verify Card

```
GET /card-scheme/verify/{cardNumber}
```

Sample Request

```
GET /card-scheme/verify/45717360
```

Sample Response 

```
{
    "success": true,
    "payload": {
        "scheme": "visa",
        "type": "debit",
        "bank": "Jyske Bank"
    }
}
```

#### Get Number of hit

```
GET /card-scheme/stats?start=1&limit=3
```

Sample Request

```
GET /card-scheme/stats?start=1&limit=3
```

Sample Response 

```
{
    "success": true,
    "start": 1,
    "limit": 3,
    "size": 2,
    "payload": [
        {
            "cardNumber": "45717360",
            "hitCounter": 46
        },
        {
            "cardNumber": "4571736",
            "hitCounter": 7
        }
    ]
}
```

### Second App (Running on port 8082)

This is a UI app so it should be visited on the browser at http://127.0.0.1:8082/

Anytime the first app verify card endppoint /card-scheme/verify/{cardNumber} is called the payload published to kafka is consumed then printed to the console and displayed on the page http://127.0.0.1:8082/

