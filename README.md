# Project : Elysian Travels

## API Documentation

### Signup

1. Role = user
   - Endpoint POST : http://localhost:8080/api/auth/signup
   - Request Body
```json
{
  "username": "test",
  "email" : "test@test.com",
  "password" : "12345678"
}
```

2. Role = admin
   - Endpoint POST : http://localhost:8080/api/auth/signup
   - Request Body
```json
{
  "username": "ManjotKaur",
  "email" : "manjot@test.com",
  "password" : "12345678",
  "role": ["admin"]
}
```
- Response Body
```json
{
  "message": "User registered successfully!"
}
```

### Signin
- Endpoint POST : http://localhost:8080/api/auth/signin
  - Request Body
```json
{
  "username": "test",
  "password" : "12345678"
}
```
- Response Body
```json
{
  "id": 1,
  "username": "test",
  "email": "test@test.com",
  "roles": [
    "ROLE_USER"
  ],
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.....",
  "tokenType": "Bearer"
}
```

### Create Travel Package [Admin role]
- Endpoint POST : http://localhost:8080/api/travelPackage
  - Request Header : Authorization "Bearer {accessToken}"
  - Request Body
```json
{
  "name": "Titanic",
  "passengerCapacity": "1000"
}
```
- Response 201 Created

### Print Itinerary [Admin/User role]
- Endpoint GET : http://localhost:8080/api/travelPackage
  - Request Header : Authorization "Bearer {accessToken}"
  - Response Body
```json
[
  {
    "id": 1,
    "name": "Titanic",
    "passengerCapacity": 1000,
    "destinationList": []
  },
  {
    "id": 2,
    "name": "test",
    "passengerCapacity": 3,
    "destinationList": [
      {
        "id": 1,
        "name": "Mars",
        "activityList": [
          {
            "id": 1,
            "name": "Singing",
            "description": "learn and enjoy to sing",
            "cost": 700.0,
            "capacity": 2
          },
          {
            "id": 2,
            "name": "Dance",
            "description": "learn and enjoy to dance",
            "cost": 200.0,
            "capacity": 1
          }
        ]
      },
      {
        "id": 2,
        "name": "Sun",
        "activityList": [
          {
            "id": 3,
            "name": "Swim",
            "description": "learn and enjoy to swim",
            "cost": 200.0,
            "capacity": 1
          }
        ]
      }
    ]
  }
]
```

### Create Destination for Travel Package [Admin role]
- Endpoint POST : http://localhost:8080/api/destination/{travelPackageId}
  - Request Header : Authorization "Bearer {accessToken}"
  - Request Body
```json
{
  "name": "DestinationName"
}
```
- Response 201 Created

### Print Available Activities [Admin/User role]
- Endpoint GET : http://localhost:8080/api/activity/availableSpaces
  - Request Header : Authorization "Bearer {accessToken}"
  - Response Body
```json
[
{
"id": 1,
"name": "Singing",
"description": "learn and enjoy to sing",
"cost": 700.0,
"capacity": 2
},
{
"id": 2,
"name": "Dance",
"description": "learn and enjoy to dance",
"cost": 200.0,
"capacity": 1
},
{
"id": 3,
"name": "Swim",
"description": "learn and enjoy to swim",
"cost": 200.0,
"capacity": 1
}
]
```

### Subscribe to a Travel Package [Admin/User role]
- Endpoint POST : http://localhost:8080/api/passenger/subscribe/{travelPackageId}
  - Request Header : Authorization "Bearer {accessToken}"
  - Request Body
```json
{
  "id" : "2",
  "passengerType": "GOLD",
  "balance": "1000"
}
```
- Response 200 OK

### Register for an Activity [Admin/User role]
- Endpoint POST : http://localhost:8080/api/activity/register/{passengerId}/{activityId}
  - Request Header : Authorization "Bearer {accessToken}"
  - Response 200 OK

### Print Passenger List [Admin role]
- Endpoint GET : http://localhost:8080/api/passenger/list/{travelPackageId}
  - Request Header : Authorization "Bearer {accessToken}"
  - Response Body
```json
{
  "travelPackageName": "test",
  "passengerCapacity": 3,
  "passengerCount": 1,
  "passengers": [
    {
      "id": 2,
      "passengerName": "test",
      "passengerNumber": 2,
      "passengerType": "GOLD",
      "balance": 0.0,
      "activities": []
    }
  ]
}
```

### Print Passenger Details [Admin role]
- Endpoint GET : http://localhost:8080/api/passenger/{userId}
  - Request Header : Authorization "Bearer {accessToken}"
  - Response Body
```json
{
  "id": 2,
  "passengerName": "test",
  "passengerNumber": 2,
  "passengerType": "GOLD",
  "balance": 10.0,
  "activities": [
    {
      "id": 2,
      "name": "Dance",
      "description": "learn and enjoy to dance",
      "cost": 180.0,
      "capacity": 0,
      "destination": "Mars"
    },
    {
      "id": 1,
      "name": "Singing",
      "description": "learn and enjoy to sing",
      "cost": 630.0,
      "capacity": 0,
      "destination": "Mars"
    },
    {
      "id": 3,
      "name": "Swim",
      "description": "learn and enjoy to swim",
      "cost": 180.0,
      "capacity": 0,
      "destination": "Sun"
    }
  ]
}
```



